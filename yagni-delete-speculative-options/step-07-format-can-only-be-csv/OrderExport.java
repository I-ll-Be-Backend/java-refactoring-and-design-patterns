import java.util.*;

record Order(String id, double total, String postalCode) {}
interface OrderRepo { List<Order> today(); }
interface Warehouse { void uploadCsv(String csv); }
/* application.yml
export:
  delimiter: ","
  header: true
*/
record ExportConfig(String delimiter, boolean header) {}

class OrderExport {
    private final String delimiter;
    private final boolean header;

    OrderExport(ExportConfig cfg) {
        delimiter = cfg.delimiter();
        header = cfg.header();
    }

    String export(List<Order> orders) {
        StringBuilder out = new StringBuilder();
        if (header) {
            out.append("id" + delimiter + "total\n");
        }
        for (Order o : orders) {
            String row = o.id() + delimiter + o.total();
            out.append(row).append("\n");
        }
        return out.toString();
    }
}

class NightlyJob {
    ExportConfig config;
    OrderRepo orders;
    Warehouse warehouse;

    void run() {
        String csv = new OrderExport(config).export(orders.today());
        warehouse.uploadCsv(csv);
    }
}

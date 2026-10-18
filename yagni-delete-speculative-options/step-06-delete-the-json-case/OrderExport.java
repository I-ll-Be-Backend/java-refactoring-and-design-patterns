import java.util.*;

record Order(String id, double total, String postalCode) {}
interface OrderRepo { List<Order> today(); }
interface Warehouse { void uploadCsv(String csv); }
/* application.yml
export:
  format: csv
  delimiter: ","
  header: true
*/
record ExportConfig(String format, String delimiter, boolean header) {}

class OrderExport {
    private final String format;
    private final String delimiter;
    private final boolean header;

    OrderExport(ExportConfig cfg) {
        format = cfg.format();
        delimiter = cfg.delimiter();
        header = cfg.header();
    }

    String export(List<Order> orders) {
        StringBuilder out = new StringBuilder();
        if (header && format.equals("csv")) {
            out.append("id" + delimiter + "total\n");
        }
        for (Order o : orders) {
            String row = switch (format) {
                case "csv" -> o.id() + delimiter + o.total();
                default -> throw new IllegalArgumentException(format);
            };
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

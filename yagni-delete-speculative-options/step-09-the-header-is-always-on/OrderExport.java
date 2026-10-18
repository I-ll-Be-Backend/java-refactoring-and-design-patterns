import java.util.*;

record Order(String id, double total, String postalCode) {}
interface OrderRepo { List<Order> today(); }
interface Warehouse { void uploadCsv(String csv); }
/* application.yml
export:
*/
record ExportConfig() {}

class OrderExport {
    OrderExport(ExportConfig cfg) {
    }

    String export(List<Order> orders) {
        StringBuilder out = new StringBuilder();
        out.append("id,total\n");
        for (Order o : orders) {
            String row = o.id() + "," + o.total();
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

import java.util.*;

record Order(String id, double total, String postalCode) {}
interface OrderRepo { List<Order> today(); }
interface Warehouse { void uploadCsv(String csv); }
/* application.yml
*/

class OrderExport {
    String export(List<Order> orders) {
        StringBuilder out = new StringBuilder();
        out.append("id,total,postalCode\n");
        for (Order o : orders) {
            String row = o.id() + "," + o.total() + "," + o.postalCode();
            out.append(row).append("\n");
        }
        return out.toString();
    }
}

class NightlyJob {
    OrderRepo orders;
    Warehouse warehouse;

    void run() {
        String csv = new OrderExport().export(orders.today());
        warehouse.uploadCsv(csv);
    }
}

import java.util.List;

record Item(String name, double price, int qty) {}
record Order(List<Item> items, boolean express, boolean member) {}
class Checkout {
    String confirm(Order order) {
        requireItems(order);
        double total = 0;
        for (Item item : order.items()) {
            total += item.price() * item.qty();
        }
        total += total < 50.0 ? 4.99 : 0;
        total += order.express() ? 7.50 : 0;
        return order.items().size() + " items, $"
            + String.format("%.2f", total);
    }

    private void requireItems(Order order) {
        if (order.items().isEmpty()) {
            throw new IllegalArgumentException("No items");
        }
    }
}

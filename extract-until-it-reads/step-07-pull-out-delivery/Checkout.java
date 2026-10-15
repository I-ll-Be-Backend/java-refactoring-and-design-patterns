import java.util.List;

record Item(String name, double price, int qty) {}
record Order(List<Item> items, boolean express, boolean member) {}
class Checkout {
    String confirm(Order order) {
        requireItems(order);
        double goods = subtotal(order);
        double total = goods + deliveryFee(order, goods);
        return order.items().size() + " items, $"
            + String.format("%.2f", total);
    }

    private void requireItems(Order order) {
        if (order.items().isEmpty()) {
            throw new IllegalArgumentException("No items");
        }
    }

    private double subtotal(Order order) {
        double sum = 0;
        for (Item item : order.items()) {
            sum += item.price() * item.qty();
        }
        return sum;
    }

    private double deliveryFee(Order order, double goods) {
        double fee = 0;
        fee += goods < 50.0 ? 4.99 : 0;
        fee += order.express() ? 7.50 : 0;
        return fee;
    }
}

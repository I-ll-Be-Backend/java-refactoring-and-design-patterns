record Cart(double subtotal, boolean member) {}
class Checkout {
    double shippingFee(Cart cart) {
        if (cart.subtotal() >= 50.0) {
            return 0.0;
        }
        return 4.99;
    }
}

class CartBanner {
    String message(Cart cart) {
        if (cart.subtotal() < 50.0) {
            double missing = 50.0 - cart.subtotal();
            return "Add $" + missing + " to ship free";
        }
        return "Your order ships free";
    }
}

class OrderEmail {
    String shippingLine(Cart cart) {
        boolean free = cart.subtotal() >= 50.0;
        return "Shipping: " + (free ? "free" : "$4.99");
    }
}

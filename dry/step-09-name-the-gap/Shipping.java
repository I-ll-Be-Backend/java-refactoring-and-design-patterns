record Cart(double subtotal, boolean member) {}
final class ShippingPolicy {
    static final double FREE_FROM = 50.0;
    static final double FEE = 4.99;

    static boolean shipsFree(Cart cart) {
        return cart.subtotal() >= FREE_FROM;
    }

    static double amountToFree(Cart cart) {
        return FREE_FROM - cart.subtotal();
    }
}

class Checkout {
    double shippingFee(Cart cart) {
        if (ShippingPolicy.shipsFree(cart)) {
            return 0.0;
        }
        return ShippingPolicy.FEE;
    }
}

class CartBanner {
    String message(Cart cart) {
        if (!ShippingPolicy.shipsFree(cart)) {
            double missing = ShippingPolicy.amountToFree(cart);
            return "Add $" + missing + " to ship free";
        }
        return "Your order ships free";
    }
}

class OrderEmail {
    String shippingLine(Cart cart) {
        boolean free = ShippingPolicy.shipsFree(cart);
        return "Shipping: " + (free ? "free" : "$" + ShippingPolicy.FEE);
    }
}

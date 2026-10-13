record Cart(double subtotal, boolean member) {}
final class ShippingPolicy {
    private static final double FREE_FROM = 50.0;
    private static final double MEMBER_FREE_FROM = 25.0;
    static final double FEE = 4.99;

    static boolean shipsFree(Cart cart) {
        return cart.subtotal() >= freeFrom(cart);
    }

    static double amountToFree(Cart cart) {
        return freeFrom(cart) - cart.subtotal();
    }

    private static double freeFrom(Cart cart) {
        return cart.member() ? MEMBER_FREE_FROM : FREE_FROM;
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

interface RateTable { double perKg(String fromCountry, String toCountry); }
record Quote(String fromPostal, String toPostal, double price) {}
record Cart(double weightKg, double lengthCm, double widthCm, double heightCm,
            boolean express, boolean fragile) {}
record Customer(String postalCode, String country) {}
record Address(String postal, String country) {}

record Parcel(double weightKg, double lengthCm,
              double widthCm, double heightCm) {
    double billableKg() {
        double volumetricKg = lengthCm * widthCm * heightCm / 5000;
        return Math.max(weightKg, volumetricKg);
    }
}

class ShippingService {
    private final RateTable rates;

    ShippingService(RateTable rates) { this.rates = rates; }

    Quote quote(Address from, Address to, Parcel parcel, boolean express) {
        double price = price(from, to, parcel, express);
        return new Quote(from.postal(), to.postal(), price);
    }

    private double price(Address from, Address to, Parcel parcel,
                         boolean express) {
        double cost = rates.perKg(from.country(), to.country())
                * parcel.billableKg();
        return express ? cost * 1.5 : cost;
    }
}

class Checkout {
    static final String WAREHOUSE_POSTAL = "10115";
    static final String WAREHOUSE_COUNTRY = "DE";
    private final ShippingService shipping;

    Checkout(ShippingService shipping) { this.shipping = shipping; }

    Quote shippingFor(Cart cart, Customer customer) {
        Address from = new Address(WAREHOUSE_POSTAL, WAREHOUSE_COUNTRY);
        Address to = new Address(customer.postalCode(), customer.country());
        Parcel parcel = new Parcel(cart.weightKg(), cart.lengthCm(),
                cart.widthCm(), cart.heightCm());
        return shipping.quote(from, to, parcel, cart.express());
    }
}

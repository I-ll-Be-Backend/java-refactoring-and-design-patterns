interface RateTable { double perKg(String fromCountry, String toCountry); }
record Quote(String fromPostal, String toPostal, double price) {}
record Cart(double weightKg, double lengthCm, double widthCm, double heightCm,
            boolean express, boolean fragile) {}
record Customer(String postalCode, String country) {}
class ShippingService {
    private final RateTable rates;

    ShippingService(RateTable rates) { this.rates = rates; }

    Quote quote(String fromPostal, String fromCountry,
                String toPostal, String toCountry, double weightKg,
                double lengthCm, double widthCm, double heightCm,
                boolean express) {
        double price = price(fromCountry, toCountry, weightKg,
                             lengthCm, widthCm, heightCm, express);
        return new Quote(fromPostal, toPostal, price);
    }

    private double price(String fromCountry, String toCountry,
                         double weightKg, double lengthCm, double widthCm,
                         double heightCm, boolean express) {
        double volumetricKg = lengthCm * widthCm * heightCm / 5000;
        double cost = rates.perKg(fromCountry, toCountry)
                * Math.max(weightKg, volumetricKg);
        return express ? cost * 1.5 : cost;
    }
}

class Checkout {
    static final String WAREHOUSE_POSTAL = "10115";
    static final String WAREHOUSE_COUNTRY = "DE";
    private final ShippingService shipping;

    Checkout(ShippingService shipping) { this.shipping = shipping; }

    Quote shippingFor(Cart cart, Customer customer) {
        return shipping.quote(WAREHOUSE_POSTAL, WAREHOUSE_COUNTRY,
                customer.postalCode(), customer.country(), cart.weightKg(),
                cart.lengthCm(), cart.widthCm(), cart.heightCm(),
                cart.express());
    }
}

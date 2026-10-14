
record Loan(int daysOut, boolean student) {}

class Fees {
    double calc(Loan l) {
        int x = l.daysOut() - 14;
        if (x <= 0) {
            return 0;
        }
        double r = l.student() ? 0.10 : 0.25;
        return Math.min(x * r, 10.0);
    }
}

class ReturnDesk {
    private final Fees fees = new Fees();

    String receipt(Loan l) {
        return "Late fine: $" + fees.calc(l);
    }
}

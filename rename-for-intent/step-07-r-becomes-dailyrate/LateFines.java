
record Loan(int daysOut, boolean student) {}

class Fees {
    double calc(Loan l) {
        int daysLate = l.daysOut() - 14;
        if (daysLate <= 0) {
            return 0;
        }
        double dailyRate = l.student() ? 0.10 : 0.25;
        return Math.min(daysLate * dailyRate, 10.0);
    }
}

class ReturnDesk {
    private final Fees fees = new Fees();

    String receipt(Loan l) {
        return "Late fine: $" + fees.calc(l);
    }
}

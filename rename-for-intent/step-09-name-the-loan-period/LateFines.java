
record Loan(int daysOut, boolean student) {}

class Fees {
    private static final int LOAN_DAYS = 14;

    double calc(Loan loan) {
        int daysLate = loan.daysOut() - LOAN_DAYS;
        if (daysLate <= 0) {
            return 0;
        }
        double dailyRate = loan.student() ? 0.10 : 0.25;
        return Math.min(daysLate * dailyRate, 10.0);
    }
}

class ReturnDesk {
    private final Fees fees = new Fees();

    String receipt(Loan loan) {
        return "Late fine: $" + fees.calc(loan);
    }
}

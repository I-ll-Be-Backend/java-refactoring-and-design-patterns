
record Loan(int daysOut, boolean student) {}

class Fees {
    private static final int LOAN_DAYS = 14;
    private static final double STUDENT_RATE = 0.10;
    private static final double STANDARD_RATE = 0.25;
    private static final double MAX_FINE = 10.0;

    double calc(Loan loan) {
        int daysLate = loan.daysOut() - LOAN_DAYS;
        if (daysLate <= 0) {
            return 0;
        }
        double dailyRate = loan.student() ? STUDENT_RATE : STANDARD_RATE;
        return Math.min(daysLate * dailyRate, MAX_FINE);
    }
}

class ReturnDesk {
    private final Fees fees = new Fees();

    String receipt(Loan loan) {
        return "Late fine: $" + fees.calc(loan);
    }
}

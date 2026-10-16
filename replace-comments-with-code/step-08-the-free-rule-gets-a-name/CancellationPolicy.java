import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;

record Booking(LocalDate checkIn, double total, int nights, char rate, boolean loyal) {}
class CancellationPolicy {
    private static final int FREE_CANCELLATION_DAYS = 14;
    private static final int LAST_MINUTE_DAYS = 2;
    private static final char NON_REFUNDABLE = 'N';

    double fee(Booking b, LocalDate today) {
        long daysBeforeCheckIn = DAYS.between(today, b.checkIn());
        if (isFreeCancellation(daysBeforeCheckIn)) return 0;
        // non-refundable or under 2 days out: pay in full
        if (b.rate() == NON_REFUNDABLE
            || daysBeforeCheckIn < LAST_MINUTE_DAYS) return b.total();
        // otherwise half the total
        double f = b.total() * 0.5;
        // partner contracts require a one-night minimum
        return Math.max(f, b.total() / b.nights());
    }

    private boolean isFreeCancellation(long days) {
        return days >= FREE_CANCELLATION_DAYS;
    }
}

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
        if (paysInFull(b, daysBeforeCheckIn)) return b.total();
        double halfTotal = b.total() * 0.5;
        // partner contracts require a one-night minimum
        return Math.max(halfTotal, nightlyPrice(b));
    }

    private boolean isFreeCancellation(long days) {
        return days >= FREE_CANCELLATION_DAYS;
    }

    private boolean paysInFull(Booking b, long days) {
        return b.rate() == NON_REFUNDABLE || days < LAST_MINUTE_DAYS;
    }

    private double nightlyPrice(Booking b) {
        return b.total() / b.nights();
    }
}

import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;

record Booking(LocalDate checkIn, double total, int nights, char rate, boolean loyal) {}
class CancellationPolicy {
    double fee(Booking b, LocalDate today) {
        long daysBeforeCheckIn = DAYS.between(today, b.checkIn());
        // free cancellation 14 days or more before check-in
        if (daysBeforeCheckIn >= 14) return 0;
        // non-refundable or under 2 days out: pay in full
        if (b.rate() == 'N' || daysBeforeCheckIn < 2) return b.total();
        // otherwise half the total
        double f = b.total() * 0.5;
        // partner contracts require a one-night minimum
        return Math.max(f, b.total() / b.nights());
    }
}

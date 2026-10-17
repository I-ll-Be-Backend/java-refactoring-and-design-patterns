import java.util.*;

record Ticket(int minutes, boolean electric) {
    int hours() { return (minutes + 59) / 60; }
}
class ParkingFee {
    private static final double HOURLY_RATE = 3.0;
    private static final double DAILY_CAP = 20.0;
    private static final int GRACE_MINUTES = 15;

    double charge(Ticket t) {
        if (t.minutes() <= GRACE_MINUTES) return 0;
        return Math.min(t.hours() * HOURLY_RATE, DAILY_CAP);
    }
}

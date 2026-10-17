import java.util.*;

record Ticket(int minutes, boolean electric) {
    int hours() { return (minutes + 59) / 60; }
}
class ParkingFee {
    private static final double HOURLY_RATE = 3.0;
    private static final double DAILY_CAP = 20.0;
    private static final int GRACE_MINUTES = 15;
    private static final int EV_FREE_HOURS = 2;

    double charge(Ticket t) {
        if (t.minutes() <= GRACE_MINUTES) return 0;
        int hours = t.hours();
        if (t.electric()) hours = Math.max(0, hours - EV_FREE_HOURS);
        return Math.min(hours * HOURLY_RATE, DAILY_CAP);
    }
}

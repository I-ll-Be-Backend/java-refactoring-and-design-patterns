import java.util.*;

record Ticket(int minutes, boolean electric) {
    int hours() { return (minutes + 59) / 60; }
}
class ParkingFee {
    double charge(Ticket t) {
        if (t.minutes() <= 15) return 0;
        return Math.min(t.hours() * 3.0, 20.0);
    }
}

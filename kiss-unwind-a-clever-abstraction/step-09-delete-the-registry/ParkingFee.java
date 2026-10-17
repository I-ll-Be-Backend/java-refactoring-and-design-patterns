import java.util.*;

record Ticket(int minutes, boolean electric) {
    int hours() { return (minutes + 59) / 60; }
}
class ParkingFee {
    double fee(Ticket t) {
        double f = 0;
        f = t.hours() * 3.0;
        f = Math.min(f, 20.0);
        f = t.minutes() <= 15 ? 0 : f;
        return f;
    }

    double charge(Ticket t) {
        return fee(t);
    }
}

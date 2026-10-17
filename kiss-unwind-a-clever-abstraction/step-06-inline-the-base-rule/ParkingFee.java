import java.util.*;

record Ticket(int minutes, boolean electric) {
    int hours() { return (minutes + 59) / 60; }
}
class ParkingFee {
    interface Rule { double apply(Ticket t, double fee); }
    static final Map<String, Rule> RULES = new HashMap<>();
    static {
        RULES.put("base", (t, f) -> t.hours() * 3.0);
        RULES.put("cap", (t, f) -> Math.min(f, 20.0));
        RULES.put("grace", (t, f) -> t.minutes() <= 15 ? 0 : f);
    }

    double fee(Ticket t) {
        double f = 0;
        f = t.hours() * 3.0;
        f = RULES.get("cap").apply(t, f);
        f = RULES.get("grace").apply(t, f);
        return f;
    }

    double charge(Ticket t) {
        return fee(t);
    }
}

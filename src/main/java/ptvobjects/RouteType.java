package ptvobjects;

import java.util.Arrays;

/** Mode of transport serviced  by the stop. */
public enum RouteType {
    Train(0), Tram(1), Bus(2), VLine(3), NightBus(4);

    /** Integer representation in the JSON object */
    private final int id;

    RouteType(final int id) {
        this.id = id;
    }

    /** Integer representation in the JSON object */
    public int getId() {
        return id;
    }

    public static RouteType valueOfInt(final int i) {
        return Arrays.stream(RouteType.values()).filter(x -> x.id == i).findFirst().get();
    }
}

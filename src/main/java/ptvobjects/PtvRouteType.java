package ptvobjects;

/** Mode of transport serviced  by the stop. */
public enum PtvRouteType {
    Train(0), Tram(1), Bus(2), VLine(3), NightBus(4);

    /** Integer representation in the JSON object */
    private final int id;

    PtvRouteType(final int id) {
        this.id = id;
    }

    /** Integer representation in the JSON object */
    public int getId() {
        return this.id;
    }
}

package ptvobjects.v3;

import java.util.StringJoiner;

public class Departure {
    private int stopId;
    private int routeId;
    private int runId;
    private int directionId;

    @Override
    public String toString() {
        return new StringJoiner(", ", Departure.class.getSimpleName() + "[", "]")
                .add("stopId=" + stopId)
                .add("routeId=" + routeId)
                .add("runId=" + runId)
                .add("directionId=" + directionId)
                .toString();
    }
}

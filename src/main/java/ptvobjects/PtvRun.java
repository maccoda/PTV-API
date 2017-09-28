package ptvobjects;

public class PtvRun implements PtvBasicObject {

    private PtvRouteType routeType;
    private int runId;
    private int numSkipped;
    private int destinationId;
    private String destinationName;


    public PtvRouteType getRouteType() {
        return routeType;
    }

    public int getRunId() {
        return runId;
    }

    public int getNumSkipped() {
        return numSkipped;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public String getDestinationName() {
        return destinationName;
    }

}

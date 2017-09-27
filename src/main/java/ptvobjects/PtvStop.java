package ptvobjects;


public class PtvStop implements PtvObject {

    private float distance;
    private String suburb;
    private PtvRouteType routeType;
    private int stopId;
    private String locationName;
    private float lat;
    private float lon;


    public float getDistance() {
        return distance;
    }

    public String getSuburb() {
        return suburb;
    }

    public PtvRouteType getRouteType() {
        return routeType;
    }

    public int getStopId() {
        return stopId;
    }

    public String getLocationName() {
        return locationName;
    }

    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }

}

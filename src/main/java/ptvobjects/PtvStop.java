package ptvobjects;


public class PtvStop implements PtvObject {

    private float distance;
    private String suburb;
    private int route_type;
    private int stop_id;
    private String location_name;
    private float lat;
    private float lon;


    public float getDistance() {
        return distance;
    }

    public String getSuburb() {
        return suburb;
    }

    public PtvRouteType getRouteType() {
        return PtvRouteType.values()[route_type];
    }

    public int getStopId() {
        return stop_id;
    }

    public String getLocationName() {
        return location_name;
    }

    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }

}

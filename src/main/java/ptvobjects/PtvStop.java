package ptvobjects;


public class PtvStop implements PtvBasicObject {

    private float distance;
    private String suburb;
    private int route_type;
    private int stop_id;
    private String location_name;
    private float lat;
    private float lon;


    /**
     * The distance returned depends on the query that is sent. If the request contains geographic positions this will
     * be non-zero.
     *
     * @return distance from query
     */
    public float getDistance() {
        return distance;
    }

    /**
     * @return the suburb name
     */
    public String getSuburb() {
        return suburb;
    }

    /**
     * @return mode of transport at stop
     */
    public PtvRouteType getRouteType() {
        return PtvRouteType.values()[route_type];
    }

    /**
     * @return the unique identifier of each stop
     */
    public int getStopId() {
        return stop_id;
    }

    /**
     * @return the name of the stop based on a concise geographic description
     */
    public String getLocationName() {
        return location_name;
    }

    /**
     * @return geographic coordinate of latitude
     */
    public float getLat() {
        return lat;
    }

    /**
     * @return geographic coordinate of longitude
     */
    public float getLon() {
        return lon;
    }

}

package ptvobjects;

/**
 * PTV Run object.
 */
public class PtvRun implements PtvBasicObject {

    private int route_type;
    private int run_id;
    private int num_skipped;
    private int destination_id;
    private String destination_name;


    /**
     * @return mode of transport servied by the stop
     */
    public PtvRouteType getRouteType() {
        return PtvRouteType.values()[route_type];
    }

    /**
     * @return unique identifier of each run
     */
    public int getRunId() {
        return run_id;
    }

    /**
     * @return the number of stops skipped for the run, applicable to train; a number greater than zero indicates either
     * a limited express or express service
     */
    public int getNumSkipped() {
        return num_skipped;
    }

    /**
     * @return the stop_id of the destination, i.e. the last stop for the run
     */
    public int getDestinationId() {
        return destination_id;
    }

    /**
     * @return the location_name of the destination, i.e. the last stop for the run
     */
    public String getDestinationName() {
        return destination_name;
    }

}

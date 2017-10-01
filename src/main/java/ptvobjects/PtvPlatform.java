package ptvobjects;

public class PtvPlatform implements PtvBasicObject {

    private int realtime_id;
    private PtvStop stop;
    private PtvDirection direction;


    /**
     * @return a place holder for the stop’s real-time feed system ID where this exists (if there is no real-time ID for
     * the stop, this attribute will return “0”)
     */
    public int getRealtimeId() {
        return realtime_id;
    }

    /**
     * @return PtvStop Object
     */
    public PtvStop getStop() {
        return stop;
    }

    /**
     * @return PtvDirection Object
     */
    public PtvDirection getDirection() {
        return direction;
    }

}

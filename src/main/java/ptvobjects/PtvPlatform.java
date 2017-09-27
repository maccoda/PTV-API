package ptvobjects;

public class PtvPlatform implements PtvObject {

    private int realtime_id;
    private PtvStop stop;
    private PtvDirection direction;


    public int getRealtimeId() {
        return realtime_id;
    }

    public PtvStop getStop() {
        return stop;
    }

    public PtvDirection getDirection() {
        return direction;
    }

}

package ptvobjects;

public class PtvPlatform implements PtvObject {

    private int realtimeId;
    private PtvStop stop;
    private PtvDirection direction;


    public int getRealtimeId() {
        return realtimeId;
    }

    public PtvStop getStop() {
        return stop;
    }

    public PtvDirection getDirection() {
        return direction;
    }

}

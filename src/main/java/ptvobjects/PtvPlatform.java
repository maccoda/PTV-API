package main.java.ptvobjects;

import org.json.simple.JSONObject;

import main.java.util.JSONHelper;

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

  @Override
  public void populateFields(JSONObject object) {
    realtimeId = JSONHelper.parseIntegerValue(object, "realtime_id");
    stop = new PtvStop();
    stop.populateFields((JSONObject) object.get("stop"));
    direction = new PtvDirection();
    direction.populateFields((JSONObject) object.get("direction"));

  }

}

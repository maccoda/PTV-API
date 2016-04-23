package main.ptvobjects;

import org.json.simple.JSONObject;

import main.JSONHelper;

public class PtvPlatform extends PtvObject {
  private String realtimeId;
  private PtvStop stop;
  private PtvDirection direction;

  public String getRealtimeId() {
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
    realtimeId = JSONHelper.parseStringValue(object, "realtime_id");
    stop = new PtvStop();
    stop.populateFields((JSONObject) object.get("stop"));
    direction = new PtvDirection();
    direction.populateFields((JSONObject) object.get("direction"));

  }

}

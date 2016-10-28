package ptvobjects;

import org.json.simple.JSONObject;

import util.JSONHelper;

public class PtvPlatform implements PtvObject {

  private int realtimeId;
  private PtvStop stop;
  private PtvDirection direction;

  PtvPlatform(JSONObject object) {
    populateFields(object);
  }

  public int getRealtimeId() {
    return realtimeId;
  }

  public PtvStop getStop() {
    return stop;
  }

  public PtvDirection getDirection() {
    return direction;
  }

  private void populateFields(JSONObject object) {
    realtimeId = JSONHelper.parseIntegerValue(object, "realtime_id");
    stop = new PtvStop((JSONObject) object.get("stop"));
    direction = new PtvDirection((JSONObject) object.get("direction"));
  }

}

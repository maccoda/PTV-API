package ptvobjects;

import org.json.simple.JSONObject;

import util.JSONHelper;

public class PtvDirection implements PtvObject {

  private int lineDirId;
  private int directionId;
  private String directionName;
  private PtvLine line;

  PtvDirection(JSONObject object) {
    populateFields(object);
  }

  public int getLineDirId() {
    return lineDirId;
  }

  public int getDirectionId() {
    return directionId;
  }

  public String getDirectionName() {
    return directionName;
  }

  public PtvLine getLine() {
    return line;
  }

  private void populateFields(JSONObject object) {
    lineDirId = JSONHelper.parseIntegerValue(object, "linedir_id");
    directionId = JSONHelper.parseIntegerValue(object, "direction_id");
    directionName = object.get("direction_name").toString();
    line = new PtvLine((JSONObject) object.get("line"));
  }
}

package main.ptvapi.ptvobjects;

import org.json.simple.JSONObject;

import main.ptvapi.util.JSONHelper;

public class PtvDirection implements PtvObject {

  private int     lineDirId;
  private int     directionId;
  private String  directionName;
  private PtvLine line;

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

  @Override
  public void populateFields(JSONObject object) {
    lineDirId = JSONHelper.parseIntegerValue(object, "linedir_id");
    directionId = JSONHelper.parseIntegerValue(object, "direction_id");
    directionName = object.get("direction_name").toString();
    line = new PtvLine();
    line.populateFields((JSONObject) object.get("line"));

  }
}

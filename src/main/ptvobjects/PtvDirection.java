package main.ptvobjects;

import org.json.simple.JSONObject;

public class PtvDirection extends PtvObject {

  private String lineDirId;
  private String directionId;
  private String directionName;
  private PtvLine line;

  public String getLineDirId() {
    return lineDirId;
  }

  public String getDirectionId() {
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
    lineDirId = object.get("linedir_id").toString();
    directionId = object.get("direction_id").toString();
    directionName = object.get("direction_name").toString();
    line = new PtvLine();
    line.populateFields((JSONObject) object.get("line"));

  }
}

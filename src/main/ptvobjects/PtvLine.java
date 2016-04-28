package main.ptvobjects;

import org.json.simple.JSONObject;

public class PtvLine implements PtvObject {
  private PtvRouteType routeType;
  private int lineId;
  private String lineName;
  private String lineNumber;
  private String lineNameShort;
  private String lineNumberLong;

  public PtvRouteType getRouteType() {
    return routeType;
  }

  public int getLineId() {
    return lineId;
  }

  public String getLineName() {
    return lineName;
  }

  public String getLineNumber() {
    return lineNumber;
  }

  public String getLineNameShort() {
    return lineNameShort;
  }

  public String getLineNumberLong() {
    return lineNumberLong;
  }

  @Override
  public void populateFields(JSONObject object) {
    routeType = JSONHelper.getRouteTypeFromObject(object);
    lineId = JSONHelper.parseIntegerValue(object, "line_id");
    lineName = JSONHelper.parseStringValue(object, "line_name");
    lineNumber = JSONHelper.parseStringValue(object, "line_number");
    lineNameShort = JSONHelper.parseStringValue(object, "line_name_short");
    lineNumberLong = JSONHelper.parseStringValue(object, "line_number_long");

  }

}

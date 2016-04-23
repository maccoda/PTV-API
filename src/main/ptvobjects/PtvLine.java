package main.ptvobjects;

import org.json.simple.JSONObject;

public class PtvLine extends PtvObject {
  private PtvRouteType routeType;
  private String lineId;
  private String lineName;
  private String lineNumber;
  private String lineNameShort;
  private String lineNumberLong;

  public PtvRouteType getRouteType() {
    return routeType;
  }

  public String getLineId() {
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
    int routeTypeId = Integer.parseInt((object.get("route_type").toString()));
    for (PtvRouteType type : PtvRouteType.values()) {
      if (routeTypeId == type.getId()) {
        routeType = type;
        break;
      }
    }

    lineId = object.get("line_id").toString();
    lineName = object.get("line_name").toString();
    lineNumber = object.get("line_number").toString();
    lineNameShort = object.get("line_name_short").toString();
    lineNumberLong = object.get("line_number_long").toString();

  }

}

package model.ptvobjects;

import org.json.simple.JSONObject;

import model.JSONHelper;

public class PtvRun extends PtvObject {
  private PtvRouteType routeType;
  private String runId;
  private int numSkipped;
  private String destinationId;
  private String destinationName;

  public PtvRouteType getRouteType() {
    return routeType;
  }

  public String getRunId() {
    return runId;
  }

  public int getNumSkipped() {
    return numSkipped;
  }

  public String getDestinationId() {
    return destinationId;
  }

  public String getDestinationName() {
    return destinationName;
  }

  @Override
  public void populateFields(JSONObject object) {
    routeType = getRouteTypeFromObject(object);
    runId = object.get("run_id").toString();
    numSkipped = JSONHelper.parseIntegerValue(object, "num_skipped");
    destinationId = object.get("desitination_id").toString();
    destinationName = object.get("destination_name").toString();

  }

}

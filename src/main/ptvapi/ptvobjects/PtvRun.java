package main.ptvapi.ptvobjects;

import org.json.simple.JSONObject;

import main.ptvapi.util.JSONHelper;

public class PtvRun implements PtvObject {
  private PtvRouteType routeType;
  private int          runId;
  private int          numSkipped;
  private int          destinationId;
  private String       destinationName;

  public PtvRouteType getRouteType() {
    return routeType;
  }

  public int getRunId() {
    return runId;
  }

  public int getNumSkipped() {
    return numSkipped;
  }

  public int getDestinationId() {
    return destinationId;
  }

  public String getDestinationName() {
    return destinationName;
  }

  @Override
  public void populateFields(JSONObject object) {
    routeType = JSONHelper.getRouteTypeFromObject(object);
    runId = JSONHelper.parseIntegerValue(object, "run_id");
    numSkipped = JSONHelper.parseIntegerValue(object, "num_skipped");
    destinationId = JSONHelper.parseIntegerValue(object, "destination_id");
    destinationName = JSONHelper.parseStringValue(object, "destination_name");

  }

}

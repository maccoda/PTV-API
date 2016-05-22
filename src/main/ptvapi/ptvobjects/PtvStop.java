package main.ptvapi.ptvobjects;

import org.json.simple.JSONObject;

import main.ptvapi.JSONHelper;

public class PtvStop implements PtvObject {
  private float distance;
  private String suburb;
  private PtvRouteType routeType;
  private int stopId;
  private String locationName;
  private float lat;
  private float lon;

  public float getDistance() {
    return distance;
  }

  public String getSuburb() {
    return suburb;
  }

  public PtvRouteType getRouteType() {
    return routeType;
  }

  public int getStopId() {
    return stopId;
  }

  public String getLocationName() {
    return locationName;
  }

  public float getLat() {
    return lat;
  }

  public float getLon() {
    return lon;
  }

  @Override
  public void populateFields(JSONObject object) {
    distance = JSONHelper.parseFloatValue(object, "distance");
    suburb = JSONHelper.parseStringValue(object, "suburb");
    routeType = JSONHelper.getRouteTypeFromObject(object);
    stopId = JSONHelper.parseIntegerValue(object, "stop_id");
    locationName = JSONHelper.parseStringValue(object, "location_name");
    lat = JSONHelper.parseFloatValue(object, "lat");
    lon = JSONHelper.parseFloatValue(object, "lon");

  }

}

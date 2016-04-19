package model.ptvobjects;

import org.json.simple.JSONObject;

import model.JSONHelper;

public class PtvStop extends PtvObject {
  private float distance;
  private String suburb;
  private PtvRouteType routeType;
  private String stopId;
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

  public String getStopId() {
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
    routeType = getRouteTypeFromObject(object);
    stopId = JSONHelper.parseStringValue(object, "stop_id");
    locationName = JSONHelper.parseStringValue(object, "location_name");
    lat = JSONHelper.parseFloatValue(object, "lat");
    lon = JSONHelper.parseFloatValue(object, "lon");

  }

}

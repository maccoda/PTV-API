package main.ptvobjects;

import org.json.simple.JSONObject;

import main.JSONHelper;

public abstract class PtvObject {

  public abstract void populateFields(JSONObject object);

  protected PtvRouteType getRouteTypeFromObject(JSONObject object) {
    int routeTypeId = JSONHelper.parseIntegerValue(object, "route_type");
    for (PtvRouteType type : PtvRouteType.values()) {
      if (routeTypeId == type.getId()) {
        return type;
      }
    }
    return null;
  }

}

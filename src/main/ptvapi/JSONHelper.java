package main.ptvapi;

import org.json.simple.JSONObject;

import main.ptvapi.ptvobjects.PtvRouteType;

public class JSONHelper {

  /*
   * IDEA Add in a constructor to parse the object or something to put all the
   * JSON handling code in here, then the methods will have to not be static and
   * can use the provided JSON object, thus one less argument
   */

  public static boolean parseBooleanValue(JSONObject object, String key) {
    return Boolean.parseBoolean(object.get(key).toString());
  }

  public static int parseIntegerValue(JSONObject object, String key) {
    return Integer.parseInt(object.get(key).toString());
  }

  public static String parseStringValue(JSONObject object, String key) {
    return object.get(key).toString();
  }

  public static float parseFloatValue(JSONObject object, String key) {
    return Float.parseFloat(object.get(key).toString());
  }

  public static PtvRouteType getRouteTypeFromObject(JSONObject object) {
    int routeTypeId = JSONHelper.parseIntegerValue(object, "route_type");
    for (PtvRouteType type : PtvRouteType.values()) {
      if (routeTypeId == type.getId()) {
        return type;
      }
    }
    return null;
  }

}

package util;

import org.json.simple.JSONObject;
import ptvobjects.PtvRouteType;

public class JSONHelper {

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

    /**
     * Converts route type from integer value as received from PTV to enum type.
     *
     * @param object - JSON object containing the route type.
     * @return Enum value of that integer
     */
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

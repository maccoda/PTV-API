package ptvobjects.v3;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import ptvobjects.PtvRouteType;

import java.lang.reflect.Type;

public class RouteTypeDeserializer implements JsonDeserializer<PtvRouteType> {
    @Override
    public PtvRouteType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return PtvRouteType.valueOfInt(json.getAsInt());
    }
}

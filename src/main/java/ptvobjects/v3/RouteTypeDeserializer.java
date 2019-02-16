package ptvobjects.v3;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import ptvobjects.RouteType;

import java.lang.reflect.Type;

public class RouteTypeDeserializer implements JsonDeserializer<RouteType> {
    @Override
    public RouteType deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
        return RouteType.valueOfInt(json.getAsInt());
    }
}

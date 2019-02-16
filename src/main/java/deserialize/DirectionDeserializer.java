package deserialize;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import core.url.v3.DirectionsOnRouteRequest;
import factory.ResponseDeserializerFactory;
import ptvobjects.v3.Direction;

import java.lang.reflect.Type;
import java.util.List;

public class DirectionDeserializer extends AbstractResponseDeserializer<List<Direction>> {

    static {
        ResponseDeserializerFactory.instance().registerDeserializer(DirectionsOnRouteRequest.class, DirectionDeserializer.class);
    }

    public DirectionDeserializer(final Gson gson) {
        super(gson);
    }

    @Override
    JsonElement topLevelElement(final String response) {
        return gson.fromJson(response, JsonObject.class).get("directions");
    }

    @Override
    Type deserializeType() {
        return new TypeToken<List<Direction>>() {
        }.getType();
    }
}

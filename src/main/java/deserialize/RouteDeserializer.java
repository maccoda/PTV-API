package deserialize;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import core.url.v3.RoutesRequest;
import factory.ResponseDeserializerFactory;
import ptvobjects.v3.Route;

import java.lang.reflect.Type;
import java.util.Collection;

public class RouteDeserializer extends AbstractResponseDeserializer<Collection<Route>> {

    static {
        ResponseDeserializerFactory.instance().registerDeserializer(RoutesRequest.class, RouteDeserializer.class);
    }

    public RouteDeserializer(final Gson gson) {
        super(gson);
    }

    @Override
    JsonElement topLevelElement(final String response) {
        return gson.fromJson(response, JsonObject.class).get("routes");
    }

    @Override
    Type deserializeType() {
        return new TypeToken<Collection<Route>>() {
        }.getType();
    }
}

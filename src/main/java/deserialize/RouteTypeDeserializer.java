package deserialize;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import core.url.v3.RouteTypesRequest;
import factory.ResponseDeserializerFactory;
import ptvobjects.v3.RouteTypeResponseModel;

import java.lang.reflect.Type;
import java.util.List;

public class RouteTypeDeserializer extends AbstractResponseDeserializer<RouteTypeResponseModel> {
    static {
        ResponseDeserializerFactory.instance().registerDeserializer(RouteTypesRequest.class, RouteTypeDeserializer.class);
    }

    public RouteTypeDeserializer(final Gson gson) {
        super(gson);
    }

    @Override
    JsonElement topLevelElement(final String response) {
        return gson.fromJson(response, JsonObject.class).get("route_types");
    }

    @Override
    Type deserializeType() {
        return new TypeToken<List<RouteTypeResponseModel>>() {
        }.getType();
    }
}

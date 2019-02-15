package deserialize;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import ptvobjects.v3.Direction;

import java.lang.reflect.Type;
import java.util.Collection;

public class DirectionDeserializer extends AbstractResponseDeserializer<Collection<Direction>> {
    public DirectionDeserializer(Gson gson) {
        super(gson);
    }

    @Override
    JsonElement topLevelElement(String response) {
        return gson.fromJson(response, JsonObject.class).get("directions");
    }

    @Override
    Type deserializeType() {
        return new TypeToken<Collection<Direction>>() {
        }.getType();
    }
}

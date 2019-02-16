package deserialize;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import core.url.v3.DeparturesRequest;
import factory.ResponseDeserializerFactory;
import ptvobjects.v3.Departure;

import java.lang.reflect.Type;
import java.util.Collection;

public class DepartureDeserializer extends AbstractResponseDeserializer<Collection<Departure>> {

    static {
        ResponseDeserializerFactory.instance().registerDeserializer(DeparturesRequest.class, DepartureDeserializer.class);
    }

    public DepartureDeserializer(final Gson gson) {
        super(gson);
    }

    @Override
    JsonElement topLevelElement(final String response) {
        return gson.fromJson(response, JsonObject.class).get("departures");
    }

    @Override
    Type deserializeType() {
        return new TypeToken<Collection<Departure>>() {
        }.getType();
    }
}

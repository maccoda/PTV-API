package factory;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ptvobjects.RouteType;
import ptvobjects.v3.RouteTypeDeserializer;

public class GsonFactory {
    private GsonFactory() {
    }

    public static Gson gson() {
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(RouteType.class, new RouteTypeDeserializer()).create();
    }
}

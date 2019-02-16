package factory;

import com.google.gson.Gson;
import deserialize.DepartureDeserializer;
import deserialize.DirectionDeserializer;
import deserialize.RouteDeserializer;
import deserialize.StringDeserializer;

public class ResponseDeserializerFactory {
    private static final Gson gson = GsonFactory.gson();

    private ResponseDeserializerFactory() {
    }

    public static DepartureDeserializer departureDeserializer() {
        return new DepartureDeserializer(gson);
    }

    public static RouteDeserializer routeDeserializer() {
        return new RouteDeserializer(gson);
    }

    public static DirectionDeserializer directionsDeserializer() {
        return new DirectionDeserializer(gson);
    }

    public static StringDeserializer stringDeserializer() {
        return new StringDeserializer();
    }
}

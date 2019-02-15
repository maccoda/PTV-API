package factory;

import com.google.gson.Gson;
import deserialize.DepartureDeserializer;
import deserialize.DirectionDeserializer;
import deserialize.ResponseDeserializerWrapper;
import deserialize.RouteDeserializer;
import deserialize.StringDeserializer;

public class DeserializerFactory {
    private static Gson gson = GsonFactory.gson();

    private DeserializerFactory() {
    }

    public static ResponseDeserializerWrapper responseDeserializerWrapper() {
        return new ResponseDeserializerWrapper(gson);
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

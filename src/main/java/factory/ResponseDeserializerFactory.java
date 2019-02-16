package factory;

import com.google.gson.Gson;
import core.url.v3.Request;
import deserialize.ResponseDeserializer;
import deserialize.StringDeserializer;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class ResponseDeserializerFactory {
    private static final Gson gson = GsonFactory.gson();

    private final Map<Class<Request>, Class<ResponseDeserializer>> registeredDeserializers;
    private static ResponseDeserializerFactory instance;

    private ResponseDeserializerFactory() {
        registeredDeserializers = new HashMap<>();
    }

    public static ResponseDeserializerFactory instance() {
        if (instance == null) {
            instance = new ResponseDeserializerFactory();
        }
        return instance;
    }

    public <T> ResponseDeserializer<T> obtainDeserializer(final Class<? extends Request> request) {
        try {
            final Class deserializerClass = instance.registeredDeserializers.get(request);
            if (deserializerClass == null) {
                throw new RuntimeException("Deserializer for " + request.getSimpleName() + " has not been registered. The deserializer must be explicitly provided in function call.");
            }
            final Constructor constructor = deserializerClass.getDeclaredConstructor(Gson.class);
            return (ResponseDeserializer<T>) constructor.newInstance(gson);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void registerDeserializer(final Class requestClass, final Class deserializerClass) {
        if (requestClass.getInterfaces()[0] == Request.class && deserializerClass.getSuperclass().getInterfaces()[0] == ResponseDeserializer.class) {
            registeredDeserializers.put(requestClass, deserializerClass);
        }
    }

    public static StringDeserializer stringDeserializer() {
        return new StringDeserializer();
    }
}

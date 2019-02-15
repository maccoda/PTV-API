package deserialize;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;

public abstract class AbstractResponseDeserializer<T> implements ResponseDeserializer<T> {
    protected Gson gson;

    AbstractResponseDeserializer(Gson gson) {
        this.gson = gson;
    }

    @Override
    public T deserialize(String response) {
        JsonElement rootElement = topLevelElement(response);
        Type resultType = deserializeType();
        return gson.fromJson(rootElement, resultType);
    }

    abstract JsonElement topLevelElement(String response);

    abstract Type deserializeType();
}

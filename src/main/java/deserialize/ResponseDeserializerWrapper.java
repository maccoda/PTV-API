package deserialize;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ResponseDeserializerWrapper {
    private final Gson gson;

    public ResponseDeserializerWrapper(Gson gson) {
        this.gson = gson;
    }

    public <T> T deserialize(String response, ResponseDeserializer<T> deserializer) {
        validateStatus(response);
        return deserializer.deserialize(response);
    }

    private void validateStatus(String response) {
        JsonObject obj = gson.fromJson(response, JsonObject.class);
        System.out.println(obj.toString());
        if (obj.get("status").getAsJsonObject().get("health").getAsInt() != 1) {
            throw new RuntimeException("Status is not OK");
        }
    }
}

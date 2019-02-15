package deserialize;

public interface ResponseDeserializer<T> {
    T deserialize(String response);
}

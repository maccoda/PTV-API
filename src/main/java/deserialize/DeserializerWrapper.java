package deserialize;

public interface DeserializerWrapper {
    <T> T deserialize(final String response, final ResponseDeserializer<T> deserializer);
}

package deserialize;

public class StringDeserializer implements ResponseDeserializer<String> {
    @Override
    public String deserialize(String response) {
        return response;
    }
}

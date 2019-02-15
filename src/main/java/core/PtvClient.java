package core;

import deserialize.ResponseDeserializer;
import deserialize.ResponseDeserializerWrapper;
import factory.DeserializerFactory;
import core.url.v3.Request;

public class PtvClient {
    private final String privateKey;
    private final int developerId;
    private final UrlSignatureDecorator.ApiVersion version = UrlSignatureDecorator.ApiVersion.V3;
    private final ApiClient client;

    public PtvClient(String privateKey, int developerId) {
        this.privateKey = privateKey;
        this.developerId = developerId;
        client = new ApiClient();
    }

    public <T> T executeRequest(Request request, ResponseDeserializer<T> deserializer) {
        String url = new UrlSignatureDecorator(version, privateKey, developerId).generateCompleteURLWithSignature(request.toUrl());
        String result = client.send(url);
        ResponseDeserializerWrapper wrapper = DeserializerFactory.responseDeserializerWrapper();
        return wrapper.deserialize(result, deserializer);
    }

    public String executeRequest(Request request) {
        return executeRequest(request, DeserializerFactory.stringDeserializer());
    }
}

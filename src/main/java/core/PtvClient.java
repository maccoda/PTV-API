package core;

import auth.Authentication;
import core.url.v3.Request;
import deserialize.DeserializerWrapper;
import deserialize.ResponseDeserializer;
import factory.ApiClientFactory;
import factory.ResponseDeserializerFactory;
import factory.ResponseWrapperFactory;

public class PtvClient {
    private final Sender apiClient;
    private final DeserializerWrapper wrapper;

    PtvClient(final Sender apiClient, final DeserializerWrapper wrapper) {
        this.apiClient = apiClient;
        this.wrapper = wrapper;
    }

    // Public library entry point - hence have factories here not in main
    public PtvClient(final Authentication auth) {
        this(ApiClientFactory.v3ApiClient(auth), ResponseWrapperFactory.responseDeserializerWrapper());
    }

    public <T> T executeRequest(final Request request, final ResponseDeserializer<T> deserializer) {
        final String result = apiClient.send(request.toUrl());
        return wrapper.deserialize(result, deserializer);
    }

    String executeRequest(final Request request) {
        return executeRequest(request, ResponseDeserializerFactory.stringDeserializer());
    }
}

package core;

import core.url.v3.RoutesRequest;
import deserialize.DeserializerWrapper;
import deserialize.ResponseDeserializer;
import factory.DeserializerRegister;
import org.junit.Before;
import org.junit.Test;
import ptvobjects.v3.Route;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PtvClientTest {
    private PtvClient client;
    private ApiClientTest.SenderMock apiClientMock;
    private WrapperMock wrapperMock;

    @Before
    public void setUp() {
        apiClientMock = new ApiClientTest.SenderMock("My Response");
        wrapperMock = new WrapperMock();
        client = new PtvClient(apiClientMock, wrapperMock);
    }

    @Test
    public void shouldReturnStringResponse() {
        final String result = client.executeRequestWithRawResponse(() -> "myUrl");

        assertTrue(apiClientMock.isCalled);
        assertEquals("myUrl", apiClientMock.calledValue);
        assertTrue(wrapperMock.isCalled);
        assertEquals("My Response", wrapperMock.sentResponse);
        assertEquals("My Response", result);
    }

    @Test
    public void shouldDeserializeUsingGivenDeserializer() {
        final String result = client.executeRequest(() -> "myUrl", response -> "Hello");

        assertTrue(apiClientMock.isCalled);
        assertEquals("myUrl", apiClientMock.calledValue);
        assertTrue(wrapperMock.isCalled);
        assertEquals("My Response", wrapperMock.sentResponse);
        assertEquals("Hello", result);
    }

    @Test
    public void shouldObtainDeserializerFromFactoryWhenNoDeserializerGiven() {
        apiClientMock = new ApiClientTest.SenderMock("{\"routes\":[]}");
        client = new PtvClient(apiClientMock, wrapperMock);
        DeserializerRegister.loadDeserializers();
        final RoutesRequest request = RoutesRequest.builder().build();

        final Collection<Route> result = client.executeRequest(request);

        assertTrue(apiClientMock.isCalled);
        assertEquals(request.toUrl(), apiClientMock.calledValue);
        assertTrue(wrapperMock.isCalled);
        assertEquals("{\"routes\":[]}", wrapperMock.sentResponse);
        assertEquals(0, result.size());
    }

    private static class WrapperMock implements DeserializerWrapper {

        boolean isCalled = false;
        String sentResponse;

        @Override
        public <T> T deserialize(final String response, final ResponseDeserializer<T> deserializer) {
            isCalled = true;
            sentResponse = response;
            return deserializer.deserialize(response);
        }
    }

}

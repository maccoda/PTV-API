package core;

import deserialize.DeserializerWrapper;
import deserialize.ResponseDeserializer;
import org.junit.Before;
import org.junit.Test;

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
    public void shouldReturnStringResponseWhenNoDeserializerGiven() {
        final String result = client.executeRequest(() -> "myUrl");

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

package deserialize;

import factory.GsonFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ResponseDeserializerWrapperTest {

    private ResponseDeserializerWrapper deserializer;

    @Before
    public void setUp() throws Exception {
        deserializer = new ResponseDeserializerWrapper(GsonFactory.gson());
    }

    @Test
    public void shouldDeserializeWhenGoodStatus() {
        final ResponseMock mock = new ResponseMock();
        deserializer.deserialize(goodStatus(), mock);

        assertTrue(mock.isCalled());
    }

    @Test(expected = RuntimeException.class)
    public void shouldPanicWhenBadStatus() {
        deserializer.deserialize(badStatus(), new ResponseMock());
    }

    private static class ResponseMock implements ResponseDeserializer<Void> {
        boolean isCalled = false;

        @Override
        public Void deserialize(final String response) {
            isCalled = true;
            return null;
        }

        boolean isCalled() {
            return isCalled;
        }
    }

    private String goodStatus() {
        return "{\"status\":{\"version\": \"3.0\",\"health\": 1}}";
    }

    private String badStatus() {
        return "{\"status\":{\"version\": \"3.0\",\"health\": 100}}";
    }
}

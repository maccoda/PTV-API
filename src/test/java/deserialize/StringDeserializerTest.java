package deserialize;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringDeserializerTest {

    @Test
    public void shouldReturnResponse() {
        final String response = new StringDeserializer().deserialize("response");
        assertEquals("response", response);
    }
}

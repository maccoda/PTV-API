package auth;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PrivateKeyTest {
    @Test
    public void shouldParseAndReturn() {
        final PrivateKey key = PrivateKey.from("key");
        assertEquals("key", key.asString());
    }
}

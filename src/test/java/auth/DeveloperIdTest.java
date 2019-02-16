package auth;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeveloperIdTest {
    @Test
    public void shouldParseAndReturn() {
        final DeveloperId id = DeveloperId.from(5);
        assertEquals(5, id.asInteger());
    }
}

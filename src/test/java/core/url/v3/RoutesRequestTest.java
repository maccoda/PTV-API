package core.url.v3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RoutesRequestTest {

    @Test
    public void shouldBuildCorrectUrl() {
        RoutesRequest request = new RoutesRequest();

        String url = request.toUrl();

        assertEquals("/routes", url);
    }
}

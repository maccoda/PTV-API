package core.url.v3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RoutesRequestTest {

    @Test
    public void shouldBuildCorrectUrl() {
        final RoutesRequest request = new RoutesRequest();

        final String url = request.toUrl();

        assertEquals("/v3/routes", url);
    }
}

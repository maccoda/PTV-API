package core.url.v3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DirectionsOnRouteRequestTest {
    @Test
    public void shouldBuildCorrectUrl() {
        final DirectionsOnRouteRequest request = new DirectionsOnRouteRequest(34);

        final String url = request.toUrl();

        assertEquals("/v3/directions/route/34", url);
    }
}

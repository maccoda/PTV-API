package core.url.v3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RoutesRequestTest {

    @Test
    public void shouldBuildCorrectUrl() {
        final RoutesRequest request = RoutesRequest.builder().build();

        final String url = request.toUrl();

        assertEquals("/v3/routes", url);
    }

    @Test
    public void shouldBuildUrlWithRouteId() {
        final RoutesRequest request = RoutesRequest.builder().withRouteId(10).build();

        final String url = request.toUrl();

        assertEquals("/v3/routes/10", url);
    }
}

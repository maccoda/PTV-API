package core.url.v3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RouteTypesRequestTest {
    @Test
    public void shouldBuildUrl() {
        final String result = RouteTypesRequest.builder().build().toUrl();

        assertEquals("/v3/route_types", result);
    }
}

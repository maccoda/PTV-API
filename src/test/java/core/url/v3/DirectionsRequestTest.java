package core.url.v3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DirectionsRequestTest {
    @Test
    public void shouldBuildCorrectUrl() {
        final DirectionsRequest request = new DirectionsRequest(34);

        final String url = request.toUrl();

        assertEquals("/v3/directions/route/34", url);
    }
}

package core.url.v3;

import org.junit.Test;
import ptvobjects.RouteType;

import static org.junit.Assert.assertEquals;

public class DeparturesRequestTest {

    @Test
    public void shouldBuildCorrectUrl() {
        final DeparturesRequest request = new DeparturesRequest(RouteType.Tram, 12);

        final String url = request.toUrl();

        assertEquals("/v3/departures/route_type/1/stop/12", url);
    }
}

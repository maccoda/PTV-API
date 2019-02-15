package core.url.v3;

import org.junit.Test;
import ptvobjects.PtvRouteType;

import static org.junit.Assert.assertEquals;

public class DeparturesRequestTest {

    @Test
    public void shouldBuildCorrectUrl() {
        final DeparturesRequest request = new DeparturesRequest(PtvRouteType.Tram, 12);

        final String url = request.toUrl();

        assertEquals("/departures/route_type/1/stop/12", url);
    }
}

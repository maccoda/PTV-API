package core.url.v3;

import org.junit.Test;
import ptvobjects.RouteType;

import static org.junit.Assert.assertEquals;

public class DeparturesRequestTest {

    @Test
    public void shouldUseBuilderCorrectlyWithNoOptional() {
        final DeparturesRequest request = DeparturesRequest.builder().withRouteType(RouteType.Bus).withStopId(45).build();

        final String url = request.toUrl();

        assertEquals("/v3/departures/route_type/2/stop/45", url);
    }

    @Test
    public void shouldUseBuilderCorrectlyWithAllOptional() {
        final DeparturesRequest request = DeparturesRequest.builder()
                .withRouteType(RouteType.Bus).withStopId(45).withRouteId(23).build();

        final String url = request.toUrl();

        assertEquals("/v3/departures/route_type/2/stop/45/route/23", url);
    }
}

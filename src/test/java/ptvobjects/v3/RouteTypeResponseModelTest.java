package ptvobjects.v3;

import org.junit.Test;
import ptvobjects.RouteType;

import static org.junit.Assert.assertEquals;

public class RouteTypeResponseModelTest {
    @Test
    public void shouldConvertToEnum() {
        final RouteTypeResponseModel type = new RouteTypeResponseModel("Tram", 1);

        assertEquals(RouteType.Tram, type.toRouteType());
    }
}

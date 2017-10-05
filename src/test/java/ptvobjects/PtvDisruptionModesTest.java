package ptvobjects;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.Test;
import util.TestUtils;

import java.io.FileReader;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class PtvDisruptionModesTest {

    @Test
    public void testPopulate() throws Exception {
        final PtvDisruptionModes all = new PtvDisruptionModes(new Gson().fromJson(new FileReader(TestUtils.getResourcePath("testDisruptionModes.json")), JsonObject.class));
        assertTrue(all.getGeneral().isEmpty());
        final PtvDisruptionInformation result = all.getMetroTram().get(0);
        assertEquals(53619, result.getDistuptionId());
        assertEquals("Service disruption for Route 35 (City Circle) tram: Wednesday 16 March to Sunday, 20 March 2016", result.getTitle());
        assertEquals("http://ptv.vic.gov.au/live-travel-updates/article/service-disruption-for-route-35-city-circle-tram-wednesday-16-march-to-sunday-20-march-2016", result.getUrl());
        assertEquals("Due to the Melbourne International Flower and Garden Show at the Royal Exhibition Building, Route 35 (City Circle) trams will have altered services from Wednesday 16 March to Sunday, 20 March 2016.", result.getDescription());
        assertEquals("Current", result.getStatus());
        assertEquals("Special Event", result.getType());
        assertEquals("2016-03-08T20:11:15Z", result.getPublishedOn());
        assertEquals("2016-03-15T16:00:03Z", result.getLastUpdated());
        assertEquals("2016-03-15T16:00:00Z", result.getFromDate());
        assertEquals("2016-03-20T16:00:00Z", result.getToDate());

        final PtvLine line = result.getLines().get(0);
        assertEquals(PtvRouteType.Tram, line.getRouteType());
        assertEquals(1112, line.getLineId());
        assertEquals("35 - City Circle (Free Tourist Tram)", line.getLineName());
        assertEquals("35", line.getLineNumber());
        assertEquals("City Circle (Free Tourist Tram)", line.getLineNameShort());
        assertEquals("35", line.getLineNumberLong());
        // TODO Disruption objects seem to have a direction in their line object

        assertTrue(all.getMetroBus().isEmpty());
        assertTrue(all.getMetroTrain().isEmpty());
        assertTrue(all.getRegionalBus().isEmpty());
        assertTrue(all.getRegionalTrain().isEmpty());
        assertTrue(all.getRegionalTram().isEmpty());
    }
}

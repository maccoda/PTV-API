package ptvobjects;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.Test;
import util.TestUtils;

import java.io.FileReader;

import static org.junit.Assert.assertEquals;

public class PtvResultTest {

    @Test
    public void testStopType() throws Exception {

        final Gson gson = new Gson();
        final String resourcePath = TestUtils.getResourcePath("testResultStop.json");
        final PtvResult result = new PtvResult(gson.fromJson(new FileReader(resourcePath), JsonObject.class));

        assertEquals(result.getType(), "stop");

        final PtvStop stop = result.getObjectAsStop();
        assertEquals(stop.getDistance(), 0.0, 0.001);
        assertEquals(stop.getSuburb(), "Abbotsford");
        assertEquals(stop.getRouteType(), PtvRouteType.Tram);
        assertEquals(stop.getStopId(), 2470);
        assertEquals(stop.getLocationName(), "North Richmond Railway Station");
        assertEquals(stop.getLat(), -37.809, 0.001);
        assertEquals(stop.getLon(), 144.992, 0.001);

    }

    @Test
    public void testLineType() throws Exception {

        final Gson gson = new Gson();
        final String resourcePath = TestUtils.getResourcePath("testResultLine.json");
        final PtvResult result = new PtvResult(gson.fromJson(new FileReader(resourcePath), JsonObject.class));

        assertEquals(result.getType(), "line");

        final PtvLine line = result.getObjectAsLine();
        assertEquals(line.getRouteType(), PtvRouteType.Tram);
        assertEquals(line.getLineId(), 976);
        assertEquals(line.getLineName(), "Route 78 North Richmond via Balaclava");
        assertEquals(line.getLineNumber(), "Route 78");
        assertEquals(line.getLineNameShort(), "North Richmond");
        assertEquals(line.getLineNumberLong(), "78");

    }

}

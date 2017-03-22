package ptvobjects;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PtvResultTest {

    @Test
    public void testStopType() throws Exception {
        String testString = "{\"result\":{\"distance\":0.0,\"suburb\":\"Abbotsford\",\"transport_type\":\"tram\"," +
                "\"route_type\":1,\"stop_id\":2470,\"location_name\":\"North Richmond Railway Station\"," +
                "\"lat\":-37.809,\"lon\":144.992},\"type\":\"stop\"}";
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(testString);

        PtvResult result = new PtvResult(object);

        assertEquals(result.getType(), "stop");

        PtvStop stop = (PtvStop) result.getObject();
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
        String testString = "{\"result\":{\"transport_type\":\"tram\"," +
                "\"route_type\":1,\"line_name\":\"Route 78 North Richmond via Balaclava\", \"line_id\":976" +
                "\"line_number\":\"Route 78\", \"line_name_short\":\"North Richmond\", \"line_number_long\":\"78\"}," +
                "\"type\":\"line\"}";
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(testString);

        PtvResult result = new PtvResult(object);

        assertEquals(result.getType(), "line");

        PtvLine line = (PtvLine) result.getObject();
        assertEquals(line.getRouteType(), PtvRouteType.Tram);
        assertEquals(line.getLineId(), 976);
        assertEquals(line.getLineName(), "Route 78 North Richmond via Balaclava");
        assertEquals(line.getLineNumber(), "Route 78");
        assertEquals(line.getLineNameShort(), "North Richmond");
        assertEquals(line.getLineNumberLong(), "78");

    }

}

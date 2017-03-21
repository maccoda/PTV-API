package ptvobjects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ptvapi.ptvobjects.PtvResult;
import ptvobjects.PtvRouteType;
import ptvobjects.PtvStop;

public class PtvLineTest {

    @Test
    public void testPopulateFields() throws Exception {
        String testString = "{\"result\":{\"distance\":0.0,\"suburb\":\"Abbotsford\",\"transport_type\":\"tram\",\"route_type\":1,\"stop_id\":2470,\"location_name\":\"North Richmond Railway Station\",\"lat\":-37.809,\"lon\":144.992},\"type\":\"stop\"}";
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(testString);

        PtvResult result = new PtvResult(object);

        assertEquals(result.getType(), "stop");

        PtvStop stop = (PtvStop) result.getObject();
        assertEquals(stop.getDistance(), 0.0, 0.001);
        assertEquals(stop.getSuburb(), "Abbotsford");
        assertEquals(stop.getRouteType(), PtvRouteType.Tram.getId());
        assertEquals(stop.getStopId(), 2470);
        assertEquals(stop.getLocationName(), "North Richmond Railway Station");
        assertEquals(stop.getLat(), -37.809, 0.001);
        assertEquals(stop.getLon(), 144.992, 0.001);

    }

}

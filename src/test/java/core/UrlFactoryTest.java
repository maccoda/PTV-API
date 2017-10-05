package core;

import core.url.BroadNextDepartureUrlBuilder;
import core.url.DisruptionsUrlBuilder;
import core.url.HealthRequestUrlBuilder;
import core.url.LinesByModeUrlBuilder;
import core.url.SearchUrlBuilder;
import core.url.SpecificNextDeparturesUrlBuilder;
import core.url.StoppingPatternUrlBuilder;
import core.url.StopsNearbyUrlBuilder;
import core.url.StopsOnALineUrlBuilder;
import core.url.TransportPoiByMap;
import org.junit.Test;
import ptvobjects.PtvRouteType;
import ptvobjects.input.PtvDisruptionMode;
import ptvobjects.input.PtvPoi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * UrlFactory Tester.
 *
 * @author D. Maccora
 * @version 1.0
 * @since <pre>Apr 8, 2017</pre>
 */
public class UrlFactoryTest {

    UrlFactory tester = new UrlFactory(UrlFactory.ApiVersion.V2, "9c132d31-6a30-4cac-8d8b-8a1970834799", 2);

    /**
     * Method: healthCheck()
     */
    @Test
    public void testHealthCheck() throws Exception {
        final String result = tester.buildUrl(new HealthRequestUrlBuilder());
        // As time dependent can only check start of URL
        final String expected = "http://timetableapi.ptv.vic.gov.au/v2/healthcheck?timestamp=";
        assertTrue(result.startsWith(expected));
    }


    /**
     * Method: broadNextDeparture(final PtvRouteType aMode, final int aStopId, final int aLimit)
     */
    @Test
    public void testBroadNextDeparture() throws Exception {
        final String result = tester.buildUrl(new BroadNextDepartureUrlBuilder(PtvRouteType.Train, 1, 5));
        assertEquals("http://timetableapi.ptv.vic.gov.au/v2/mode/0/stop/1/departures/by-destination/limit/5?devid=2&signature=6C4F3D7A7E8EF714F4420D0CBC4D889D1C443A74", result);
    }

    /**
     * Method: stopsNearby(final double latitude, final double longitude)
     */
    @Test
    public void testStopsNearby() throws Exception {
        final String result = tester.buildUrl(new StopsNearbyUrlBuilder(-37.82, 144.94));
        assertEquals("http://timetableapi.ptv.vic.gov.au/v2/nearme/latitude/-37.82/longitude/144.94?devid=2&signature=8C1DAB4AC928B9951B3140508979BF02E6842136", result);
    }

    /**
     * Method: linesByMode(final PtvRouteType mode, final String name)
     */
    @Test
    public void testLinesByMode() throws Exception {
        final String result = tester.buildUrl(new LinesByModeUrlBuilder(PtvRouteType.Train, "Test"));
        assertEquals("http://timetableapi.ptv.vic.gov.au/v2/lines/mode/0?name=Test&devid=2&signature=35E3BAE53B3F9FDBC301E77127F9AFAAFEEE8123", result);
    }

    /**
     * Method: search(final String searchString)
     */
    @Test
    public void testSearch() throws Exception {
        final String result = tester.buildUrl(new SearchUrlBuilder("Flinders"));
        assertEquals("http://timetableapi.ptv.vic.gov.au/v2/search/Flinders?&devid=2&signature=E3C554966C8FB19EFDC1BEFA24670D028E502088", result);
    }

    /**
     * Method: stopsOnALine(final PtvRouteType mode, final int lineId)
     */
    @Test
    public void testStopsOnALine() throws Exception {
        final String result = tester.buildUrl(new StopsOnALineUrlBuilder(PtvRouteType.Train, 1));
        assertEquals("http://timetableapi.ptv.vic.gov.au/v2/mode/0/line/1/stops-for-line?devid=2&signature=3A0F124E2998BE46AF2FD7254968418561BCE20D", result);
    }

    @Test
    public void testTransportPoi() throws Exception {
        final PtvPoi poi = new PtvPoi().setPoi(PtvPoi.PtvPoiTypes.TRAIN).setPoi(PtvPoi.PtvPoiTypes.TRAM);
        final String result = tester.buildUrl(new TransportPoiByMap(poi, -37.82205143151239, 144.9779160007277, -37.81393456848758, 144.9859159992726, (byte) 3, 6));
        assertEquals("http://timetableapi.ptv.vic.gov.au/v2/poi/0,1/lat1/-37.82205143151239/long1/144.9779160007277/lat2/-37.81393456848758/long2/144.9859159992726/griddepth/3/limit/6?devid=2&signature=8CE68B05E0B3B53D53B37782B4089204FEFB8E61", result);
    }

    @Test
    public void testSpecificDepartures() throws Exception {
        String result = tester.buildUrl(new SpecificNextDeparturesUrlBuilder(PtvRouteType.Train, 8, 1104, 8, 5, ""));
        assertEquals("http://timetableapi.ptv.vic.gov.au/v2/mode/0/line/8/stop/1104/directionid/8/departures/all/limit/5?devid=2&signature=5869CEC44A5EEABBE59D9DC9AD5329F48B08E934", result);
        result = tester.buildUrl(new SpecificNextDeparturesUrlBuilder(PtvRouteType.Train, 8, 1104, 8, 5, "abcd"));
        assertEquals("http://timetableapi.ptv.vic.gov.au/v2/mode/0/line/8/stop/1104/directionid/8/departures/all/limit/5?for_utc=abcd&devid=2&signature=C9B9FEE9EF83E8C5CFA4808AC6E44425DB835A3E", result);
    }

    @Test
    public void testStoppingPattern() throws Exception {
        String result = tester.buildUrl(new StoppingPatternUrlBuilder(PtvRouteType.Train, 21173, 1104, ""));
        assertEquals("http://timetableapi.ptv.vic.gov.au/v2/mode/0/run/21173/stop/1104/stopping-pattern?devid=2&signature=3F471EC643500B7625819AB3EFA78765EDBB5D64", result);
        result = tester.buildUrl(new StoppingPatternUrlBuilder(PtvRouteType.Train, 21173, 1104, "abcd"));
        assertEquals("http://timetableapi.ptv.vic.gov.au/v2/mode/0/run/21173/stop/1104/stopping-pattern?for_utc=abcd&devid=2&signature=23F068474D2E0BA9D03459B2E2C851E424D0A90F", result);
    }

    @Test
    public void testDisruptions() throws Exception {
        final PtvDisruptionMode mode = new PtvDisruptionMode().setMode(PtvDisruptionMode.DisruptionModes.GENERAL).setMode(PtvDisruptionMode.DisruptionModes.METRO_TRAIN);
        final String result = tester.buildUrl(new DisruptionsUrlBuilder(mode));
        assertEquals("http://timetableapi.ptv.vic.gov.au/v2/disruptions/modes/general,metro-train?devid=2&signature=53D313557AF9D912459F5A3261E8E466AD132543", result);
    }
}

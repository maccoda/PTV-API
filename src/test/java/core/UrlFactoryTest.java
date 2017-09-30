package core;

import core.url.BroadNextDepartureUrlBuilder;
import core.url.HealthRequestUrlBuilder;
import core.url.LinesByModeUrlBuilder;
import core.url.SearchUrlBuilder;
import core.url.StopsNearbyUrlBuilder;
import core.url.StopsOnALineUrlBuilder;
import org.junit.Test;
import ptvobjects.PtvRouteType;

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
}

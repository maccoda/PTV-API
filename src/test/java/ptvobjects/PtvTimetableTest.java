package ptvobjects;

import com.google.gson.Gson;
import org.junit.Test;
import util.TestUtils;

import java.io.FileReader;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class PtvTimetableTest {

    @Test
    public void testPopulateFields() throws Exception {

        final Gson gson = new Gson();
        final String resourcePath = TestUtils.getResourcePath("testTimeTable.json");

        final PtvTimetable timetable = gson.fromJson(new FileReader(resourcePath), PtvTimetable.class);

        final PtvPlatform platform = timetable.getPlatform();
        assertEquals(0, platform.getRealtimeId());
        final PtvStop stop = platform.getStop();
        assertEquals(0.0, stop.getDistance(), 0.1);
        assertTrue(stop.getSuburb().equals("East Melbourne"));
        assertTrue(stop.getRouteType().equals(PtvRouteType.Train));
        assertEquals(1104, stop.getStopId());
        assertTrue(stop.getLocationName().equals("Jolimont-MCG"));
        assertEquals(-37.81653, stop.getLat(), 0.1);
        assertEquals(144.9841, stop.getLon(), 0.1);

        final PtvDirection direction = platform.getDirection();
        assertEquals(38, direction.getLineDirId());
        assertEquals(5, direction.getDirectionId());
        assertTrue(direction.getDirectionName().equals("South Morang"));
        final PtvLine line = direction.getLine();
        assertTrue(line.getRouteType().equals(PtvRouteType.Train));
        assertEquals(5, line.getLineId());
        assertTrue(line.getLineName().equals("South Morang"));
        assertTrue(line.getLineNumber().equals("South Morang"));
        assertTrue(line.getLineNameShort().equals("South Morang"));
        assertTrue(line.getLineNumberLong().equals(""));

        assertTrue(timetable.getTimeTimetableUtc().equals(new GregorianCalendar(2016, 3, 16, 1, 51)));
        assertNotNull(timetable.getRealtimeUtc());
        System.out.println(timetable.getRealtimeUtc().getTime());
        assertTrue(timetable.getRealtimeUtc().equals(new GregorianCalendar(2016, 3, 16, 1, 53)));

    }

}

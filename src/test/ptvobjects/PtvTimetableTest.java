package test.ptvobjects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.ptvobjects.PtvDirection;
import main.ptvobjects.PtvLine;
import main.ptvobjects.PtvPlatform;
import main.ptvobjects.PtvRouteType;
import main.ptvobjects.PtvStop;
import main.ptvobjects.PtvTimetable;

public class PtvTimetableTest {

  static String testString;

  /**
   * Create mock JSON object.
   *
   * @throws Exception
   *           fucked up
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    // TODO This string is not correct. It is for the TimetableValues array not
    // Timetable object
    testString = "{" + "\"platform\":{" + "\"realtime_id\":0," + "\"stop\": {" + "\"distance\":0.0,"
        + "\"suburb\": \"East Melbourne\"," + "\"transport_type\": \"train\","
        + "\"route_type\": 0," + "\"stop_id\":1104," + "\"location_name\": \"Jolimont-MCG\","
        + "\"lat\":-37.81653," + "\"lon\":144.9841" + "}," + "\"direction\": {"
        + "\"linedir_id\": 38," + "\"direction_id\": 5," + "\"direction_name\":\"South Morang\","
        + "\"line\": {" + "\"transport_type\":\"train\"," + "\"route_type\": 0," + "\"line_id\":5,"
        + "\"line_name\":\"South Morang\"," + "\"line_number\":\"South Morang\","
        + "\"line_name_short\":\"South Morang\"," + "\"line_number_long\":\"\"" + "}" + "}" + "},"
        + "\"run\":{" + "\"transport_type\":\"train\"," + "\"route_type\":0," + "\"run_id\":15716,"
        + "\"num_skipped\":0," + "\"destination_id\":1041,"
        + "\"destination_name\":\"Clifton Hill\"" + "},"
        + "\"time_timetable_utc\": \"2016-03-16T01:51:00Z\"," + "\"time_realtime_utc\": \"null\","
        + "\"flags\":\"\"," + "\"disruptions\":\"\"" + "}";

  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testPopulateFields() throws Exception {
    PtvTimetable timetable = new PtvTimetable();

    JSONParser parser = new JSONParser();
    JSONObject object = (JSONObject) parser.parse(testString);

    timetable.populateFields(object);

    PtvPlatform platform = timetable.getPlatform();
    assertEquals(0, platform.getRealtimeId());
    PtvStop stop = platform.getStop();
    assertEquals(0.0, stop.getDistance(), 0.1);
    assertTrue(stop.getSuburb().equals("East Melbourne"));
    assertTrue(stop.getRouteType().equals(PtvRouteType.Train));
    assertEquals(1104, stop.getStopId());
    assertTrue(stop.getLocationName().equals("Jolimont-MCG"));
    assertEquals(-37.81653, stop.getLat(), 0.1);
    assertEquals(144.9841, stop.getLon(), 0.1);

    PtvDirection direction = platform.getDirection();
    assertEquals(38, direction.getLineDirId());
    assertEquals(5, direction.getDirectionId());
    assertTrue(direction.getDirectionName().equals("South Morang"));
    PtvLine line = direction.getLine();
    assertTrue(line.getRouteType().equals(PtvRouteType.Train));
    assertEquals(5, line.getLineId());
    assertTrue(line.getLineName().equals("South Morang"));
    assertTrue(line.getLineNumber().equals("South Morang"));
    assertTrue(line.getLineNameShort().equals("South Morang"));
    assertTrue(line.getLineNumberLong().equals(""));

  }

}

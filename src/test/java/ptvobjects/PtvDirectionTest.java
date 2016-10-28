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

public class PtvDirectionTest {

  static String testString;

  /**
   * Create mock JSON object.
   *
   * @throws Exception
   *           fucked up
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    testString = "{\"linedir_id\": 0, \"direction_id\": 6, \"direction_name\":\"Frankston\", "
        + "\"line\":{\"route_type\": 0, \"line_id\": 6, "
        + "\"line_name\": \"Frankston\", \"line_number\": \"Frankston\", "
        + "\"line_name_short\": \"Frankston\", \"line_number_long\": \"\"}}";
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

    JSONParser parser = new JSONParser();
    JSONObject object = (JSONObject) parser.parse(testString);
    PtvDirection dir = new PtvDirection(object);

    assertEquals(0, dir.getLineDirId());
    assertEquals(6, dir.getDirectionId());
    assertTrue(dir.getDirectionName().equals("Frankston"));

    PtvLine line = dir.getLine();
    assertTrue(line.getRouteType().equals(PtvRouteType.Train));
    assertEquals(6, line.getLineId());
    assertTrue(line.getLineName().equals("Frankston"));
    assertTrue(line.getLineNumber().equals("Frankston"));
    assertTrue(line.getLineNameShort().equals("Frankston"));
    assertTrue(line.getLineNumberLong().equals(""));

  }

}

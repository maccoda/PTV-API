package test.ptvapi.ptvobjects;

import static org.junit.Assert.assertTrue;

import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.ptvapi.util.QueryHandler;

/**
 * @author D. Maccora
 *
 */
public class QueryHandlerTest {

  final String key = "9c132d31-6a30-4cac-8d8b-8a1970834799";
  final int developerId = 2;
  final String url = "/v2/mode/2/line/787/stops-for-line";

  /**
   * @throws java.lang.Exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {

  }

  /**
   * @throws java.lang.Exception
   */
  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void buildQueryTest() {
    final String correctQuery = "http://timetableapi.ptv.vic.gov.au/v2/mode/2/line/787/stops-for-line?devid=2&signature=1FD3AC2EC7FE0EA39D7D5EF44A23B89AA7974B41";
    final String result = new QueryHandler(key, developerId).buildQuery(url);
    assertTrue(result.equals(correctQuery));
  }

  @Test
  public void parseQueryTest() {
    final String input = "{\"securityTokenOK\":false,\n\"clientClockOK\":false,\n\"memcacheOK\":true,\n\"databaseOK\":true,}";
    final JSONObject result = new QueryHandler(key, developerId).parseQueryResult(input);
    System.out.println(result.get("securityTokenOK"));
    assertTrue(result.get("securityTokenOK").toString().equals("false"));
    assertTrue(result.get("clientClockOK").toString().equals("false"));
    assertTrue(result.get("memcacheOK").toString().equals("true"));
    assertTrue(result.get("databaseOK").toString().equals("true"));
  }

}

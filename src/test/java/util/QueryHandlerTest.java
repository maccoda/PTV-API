package util;

import core.QueryHandler;
import org.json.simple.JSONObject;
import org.junit.*;

import static org.junit.Assert.assertTrue;

/**
 * @author D. Maccora
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
    public void parseQueryTest() {
        final String input = "{\"securityTokenOK\":false,\n\"clientClockOK\":false,\n\"memcacheOK\":true,\n\"databaseOK\":true,}";
        final JSONObject result = QueryHandler.parseQueryResult(input);
        assertTrue(result.get("securityTokenOK").toString().equals("false"));
        assertTrue(result.get("clientClockOK").toString().equals("false"));
        assertTrue(result.get("memcacheOK").toString().equals("true"));
        assertTrue(result.get("databaseOK").toString().equals("true"));
    }

    @Test
    public void sendQueryTest() {
        final String rootDir = System.getProperty("user.dir");
        final String result = QueryHandler.sendQuery("file://" + rootDir + "/src/test/resources/testResponse.json");
        final String input = "{  \"securityTokenOK\": false,  \"clientClockOK\": false,  \"memcacheOK\": true,  \"databaseOK\": true,}";
        assertTrue(input.equals(result));
    }

}

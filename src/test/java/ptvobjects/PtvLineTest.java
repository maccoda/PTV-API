package ptvobjects;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PtvLineTest {

    static String testString;

    /**
     * Create mock JSON object.
     *
     * @throws Exception fucked up
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        testString = "{\"route_type\": 0, \"line_id\": 6, "
                + "\"line_name\": \"Frankston\", \"line_number\": \"Frankston\", "
                + "\"line_name_short\": \"Frankston\", \"line_number_long\": \"\"}";
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

        PtvLine line = new PtvLine(object);

        assertTrue(line.getRouteType().equals(PtvRouteType.Train));
        assertEquals(6, line.getLineId());
        assertTrue(line.getLineName().equals("Frankston"));
        assertTrue(line.getLineNumber().equals("Frankston"));
        assertTrue(line.getLineNameShort().equals("Frankston"));
        assertTrue(line.getLineNumberLong().equals(""));

    }

}

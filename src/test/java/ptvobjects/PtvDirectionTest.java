package ptvobjects;

import com.google.gson.Gson;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PtvDirectionTest {

    static String testString;

    /**
     * Create mock JSON object.
     *
     * @throws Exception
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
        final Gson gson = new Gson();
        final PtvDirection dir = gson.fromJson(testString, PtvDirection.class);

        assertEquals(0, dir.getLineDirId());
        assertEquals(6, dir.getDirectionId());
        assertTrue(dir.getDirectionName().equals("Frankston"));

        final PtvLine line = dir.getLine();
        assertTrue(line.getRouteType().equals(PtvRouteType.Train));
        assertEquals(6, line.getLineId());
        assertTrue(line.getLineName().equals("Frankston"));
        assertTrue(line.getLineNumber().equals("Frankston"));
        assertTrue(line.getLineNameShort().equals("Frankston"));
        assertTrue(line.getLineNumberLong().equals(""));

    }

}

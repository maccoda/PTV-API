package util;

import org.json.simple.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import ptvobjects.PtvRouteType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author D. Maccora
 */
public class JSONHelperTest {

    final static JSONObject input = new JSONObject();

    @SuppressWarnings("unchecked")
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        input.put("booleanValue", new Boolean(true));
        input.put("integerValue", new Integer(10));
        input.put("stringValue", "Hello World");
        input.put("floatValue", new Float(1.0));
        // This one has to match convention set by PTV
        input.put("route_type", new Integer(0));
    }

    @Test
    public void parseBooleanValueTest() {
        boolean result = JSONHelper.parseBooleanValue(input, "booleanValue");
        assertTrue(result);
    }

    @Test
    public void parseIntegerValueTest() {
        int result = JSONHelper.parseIntegerValue(input, "integerValue");
        assertEquals(10, result);
    }

    @Test
    public void parseStringValueTest() {
        String result = JSONHelper.parseStringValue(input, "stringValue");
        assertTrue(result.equals("Hello World"));
    }

    @Test
    public void parseFloatValueTest() {
        float result = JSONHelper.parseFloatValue(input, "floatValue");
        assertEquals(1.0, result, 0.01);
    }

    @Test
    public void parseRouteTypeTest() {
        PtvRouteType result = JSONHelper.getRouteTypeFromObject(input);
        assertTrue(result.equals(PtvRouteType.Train));
    }

}

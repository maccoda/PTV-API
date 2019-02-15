package deserialize;

import com.google.gson.JsonElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class DeserializeTestHelper {
    static String buildJsonArrayWithKey(final String key) {
        return "{\"" + key + "\": [1,2], \"blah\": 2}";
    }

    static void assertOnBuiltArray(final JsonElement element) {
        assertTrue(element.isJsonArray());
        assertEquals(2, element.getAsJsonArray().size());
    }
}

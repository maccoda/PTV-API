package ptvobjects;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author D. Maccora
 */
public class PtvHealthTest {

    private final String testString = "{\"securityTokenOK\":false,\"clientClockOK\":false," + "\"memcacheOK\":true,"
            + "\"databaseOK\":true}";

    private static Gson gson;

    @BeforeClass
    public static void beforeClass() {
        gson = new Gson();
    }

    @Test
    public void populateFieldtest() {

        final PtvHealth health = gson.fromJson(testString, PtvHealth.class);

        assertTrue(!health.isSecurityToken());
        assertTrue(!health.isClientClockOK());
        assertTrue(health.isMemcache());
        assertTrue(health.isDatabase());
        assertTrue(!health.isAllGood());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void isAllGoodBranchesTest() {

        final JsonObject object = new JsonObject();
        object.addProperty("securityTokenOK", true);
        object.addProperty("clientClockOK", true);
        object.addProperty("memcacheOK", true);
        object.addProperty("databaseOK", true);

        PtvHealth health = gson.fromJson(object, PtvHealth.class);

        assertTrue(health.isAllGood());

        object.addProperty("securityTokenOK", false);

        health = gson.fromJson(object, PtvHealth.class);
        assertTrue(!health.isAllGood());

        object.addProperty("securityTokenOK", true);
        object.addProperty("databaseOK", false);

        health = gson.fromJson(object, PtvHealth.class);
        assertTrue(!health.isAllGood());

        object.addProperty("databaseOK", true);
        object.addProperty("clientClockOK", false);

        health = gson.fromJson(object, PtvHealth.class);
        assertTrue(!health.isAllGood());

        object.addProperty("clientClockOK", true);
        object.addProperty("memcacheOK", false);

        health = gson.fromJson(object, PtvHealth.class);
        assertTrue(!health.isAllGood());

    }

}

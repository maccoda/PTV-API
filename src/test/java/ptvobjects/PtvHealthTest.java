package ptvobjects;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

/**
 * @author D. Maccora
 *
 */
public class PtvHealthTest {

  private String testString = "{\"securityTokenOK\":false,\"clientClockOK\":false," + "\"memcacheOK\":true,"
      + "\"databaseOK\":true}";

  @Test
  public void populateFieldtest() {
    PtvHealth health = new PtvHealth();

    JSONParser parser = new JSONParser();
    JSONObject object = new JSONObject();
    try {
      object = (JSONObject) parser.parse(testString);
    } catch (ParseException e) {
      fail("Parse Exception");
    }

    health.populateFields(object);

    assertTrue(!health.isSecurityToken());
    assertTrue(!health.isClientClock());
    assertTrue(health.isMemcache());
    assertTrue(health.isDatabase());
    assertTrue(!health.isAllGood());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void isAllGoodBranchesTest() {

    JSONObject object = new JSONObject();
    object.put("securityTokenOK", true);
    object.put("databaseOK", true);
    object.put("clientClockOK", true);
    object.put("memcacheOK", true);
    PtvHealth health = new PtvHealth();

    health.populateFields(object);
    assertTrue(health.isAllGood());

    object.put("securityTokenOK", false);

    health.populateFields(object);
    assertTrue(!health.isAllGood());

    object.put("securityTokenOK", true);
    object.put("databaseOK", false);

    health.populateFields(object);
    assertTrue(!health.isAllGood());

    object.put("databaseOK", true);
    object.put("clientClockOK", false);

    health.populateFields(object);
    assertTrue(!health.isAllGood());

    object.put("clientClockOK", true);
    object.put("memcacheOK", false);

    health.populateFields(object);
    assertTrue(!health.isAllGood());

  }

}

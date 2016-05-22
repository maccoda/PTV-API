package main.ptvapi.ptvobjects;

import org.json.simple.JSONObject;

import main.ptvapi.JSONHelper;

/**
 * @author dmaccora
 *
 */
public class PtvHealth implements PtvObject {
  private boolean securityToken, clientClock, memcache, database;

  public boolean isSecurityToken() {
    return securityToken;
  }

  public boolean isClientClock() {
    return clientClock;
  }

  public boolean isMemcache() {
    return memcache;
  }

  public boolean isDatabase() {
    return database;
  }

  @Override
  public void populateFields(JSONObject object) {
    securityToken = JSONHelper.parseBooleanValue((JSONObject) object, "securityTokenOK");
    clientClock = JSONHelper.parseBooleanValue((JSONObject) object, "clientClockOK");
    memcache = JSONHelper.parseBooleanValue((JSONObject) object, "memcacheOK");
    database = JSONHelper.parseBooleanValue((JSONObject) object, "databaseOK");

  }

}

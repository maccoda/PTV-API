package main.ptvapi.ptvobjects;

import org.json.simple.JSONObject;

public interface PtvObject {

  public abstract void populateFields(JSONObject object);

}

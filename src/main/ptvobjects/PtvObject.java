package main.ptvobjects;

import org.json.simple.JSONObject;

import main.JSONHelper;

public interface PtvObject {

  public abstract void populateFields(JSONObject object);

}

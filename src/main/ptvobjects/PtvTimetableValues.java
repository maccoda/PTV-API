package main.ptvobjects;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/** This class is just a wrapper of the array that holds each of the Timetable entries */

public class PtvTimetableValues extends PtvObject {
  private List<PtvTimetable> values;

  public PtvTimetableValues() {
    values = new ArrayList<>();
  }

  public PtvTimetable getTimetable(int index) {
    if (index < values.size()) {
      return values.get(index);
    }
    throw new IndexOutOfBoundsException();
  }

  @Override
  public void populateFields(JSONObject object) {
    JSONArray array = (JSONArray) object.get("values");

    for (int i = 0; i < array.size(); i++) {
      // Need to populate fields and add them
      // TODO Check if this is correct
      PtvTimetable timetable = new PtvTimetable();
      timetable.populateFields((JSONObject) array.get(i));
      values.add(timetable);
    }
  }
}

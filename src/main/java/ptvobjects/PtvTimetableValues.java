package ptvobjects;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * This class is just a wrapper of the array that holds each of the Timetable entries
 */

public class PtvTimetableValues implements PtvObject {

  private List<PtvTimetable> values;
  private int currentIndex;

  public PtvTimetableValues() {
    values = new ArrayList<PtvTimetable>();
    currentIndex = 0;
  }

  public PtvTimetable getTimetable(int index) {
    if (index < values.size()) {
      return values.get(index);
    }
    throw new IndexOutOfBoundsException();
  }

  public PtvTimetable next() {
    PtvTimetable result = values.get(currentIndex);
    currentIndex++;
    return result;
  }

  public PtvTimetable first() {
    currentIndex = 0;
    return values.get(currentIndex);
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

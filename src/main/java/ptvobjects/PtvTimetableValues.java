package ptvobjects;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is just a wrapper of the array that holds each of the Timetable entries
 */

public class PtvTimetableValues implements PtvObject {

    private List<PtvTimetable> values;
    private int currentIndex;

    public PtvTimetableValues(JSONObject object) {
        values = new ArrayList<PtvTimetable>();
        currentIndex = 0;
        populateFields(object);
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

    private void populateFields(JSONObject object) {
        JSONArray array = (JSONArray) object.get("values");

        for (int i = 0; i < array.size(); i++) {
            // Need to populate fields and add them
            // TODO Check if this is correct
            PtvTimetable timetable = new PtvTimetable((JSONObject) array.get(i));
            values.add(timetable);
        }
    }
}

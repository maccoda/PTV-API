package ptvobjects;


import java.util.ArrayList;
import java.util.List;

/**
 * This class is just a wrapper of the array that holds each of the Timetable entries
 */

public class PtvTimetableValues implements PtvBasicObject {

    private final List<PtvTimetable> values;
    private int currentIndex;

    public PtvTimetableValues() {
        values = new ArrayList<PtvTimetable>();
    }

//    public PtvTimetableValues(final JSONObject object) {
//        values = new ArrayList<PtvTimetable>();
//        currentIndex = 0;
//        populateFields(object);
//    }

    public PtvTimetable getTimetable(final int index) {
        if (index < values.size()) {
            return values.get(index);
        }
        throw new IndexOutOfBoundsException();
    }

    public PtvTimetable next() {
        final PtvTimetable result = values.get(currentIndex);
        currentIndex++;
        return result;
    }

    public PtvTimetable first() {
        currentIndex = 0;
        return values.get(currentIndex);
    }

}

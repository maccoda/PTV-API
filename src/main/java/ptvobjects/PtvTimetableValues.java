package ptvobjects;


import java.util.ArrayList;
import java.util.List;

/**
 * This class is just a wrapper of the array that holds each of the Timetable entries and holds state for iteration.
 */

public class PtvTimetableValues implements PtvBasicObject {

    private final List<PtvTimetable> values;
    private int currentIndex;

    /**
     * Constructor. Initialize collection.
     */
    public PtvTimetableValues() {
        values = new ArrayList<PtvTimetable>();
        currentIndex = 0;
    }

    /**
     * Return the {@link PtvTimetable} at the specified index
     *
     * @param index
     *         of timetable to get
     * @return as above
     */
    public PtvTimetable getTimetable(final int index) {
        if (index < values.size()) {
            return values.get(index);
        }
        throw new IndexOutOfBoundsException();
    }

    /**
     * @return next object in results
     */
    public PtvTimetable next() {
        final PtvTimetable result = values.get(currentIndex);
        currentIndex++;
        return result;
    }

    /**
     * Reset the internal counter and get the first element.
     *
     * @return as above
     */
    public PtvTimetable first() {
        currentIndex = 0;
        return values.get(currentIndex);
    }

}

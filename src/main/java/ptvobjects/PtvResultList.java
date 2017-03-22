package ptvobjects;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class containing the list of results returned from queries such as <code>search</code> and <code>stopsNearby</code>
 */
public class PtvResultList {
    private List<PtvResult> results;

    public PtvResultList(JSONArray arr) {
        results = new ArrayList<PtvResult>();
        Iterator<JSONObject> iter = arr.iterator();
        while (iter.hasNext()) {
            results.add(new PtvResult(iter.next()));
        }
    }

    /**
     * Returns {@link PtvResult} at index
     *
     * @param index of element to return.
     * @return element at index
     */
    public PtvResult get(int index) {
        return results.get(index);
    }

}

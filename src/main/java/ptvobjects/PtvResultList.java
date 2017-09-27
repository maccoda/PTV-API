package ptvobjects;

import java.util.List;

/**
 * Class containing the list of results returned from queries such as <code>search</code> and <code>stopsNearby</code>
 */
public class PtvResultList {
    private List<PtvResult> results;

    /**
     * Returns {@link PtvResult} at index
     *
     * @param index
     *         of element to return.
     * @return element at index
     */
    public PtvResult get(final int index) {
        return results.get(index);
    }

}

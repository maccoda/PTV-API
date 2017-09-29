package ptvobjects.builders;

import com.google.gson.JsonArray;
import ptvobjects.PtvObject;

import java.util.List;

/**
 * Builder of the list types received from PTV requests.
 */
public interface PtvListObjectBuilder<T extends PtvObject> {
    /**
     * Construct a list of the typed {@link PtvObject} from the provided JSON array.
     *
     * @param arr
     *         - JSON array to build list from
     * @return list of types
     */
    List<T> populateList(JsonArray arr);
}

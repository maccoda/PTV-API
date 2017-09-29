package ptvobjects.builders;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import ptvobjects.PtvResult;

import java.util.ArrayList;
import java.util.List;

public final class ResultListBuilder implements PtvListObjectBuilder<PtvResult> {
    @Override
    public List<PtvResult> populateList(final JsonArray arr) {
        final List<PtvResult> results = new ArrayList<PtvResult>();
        for (final JsonElement elem : arr) {
            results.add(new PtvResult(elem.getAsJsonObject()));
        }
        return results;
    }
}

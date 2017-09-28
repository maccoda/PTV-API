package ptvobjects.builders;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import ptvobjects.PtvLine;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder for {@link PtvLine} collection.
 */
public final class LinesListBuilder implements PtvListObjectBuilder<PtvLine> {
    @Override
    public List<PtvLine> populateList(final JsonArray arr) {
        final List<PtvLine> lines = new ArrayList<PtvLine>();
        for (final JsonElement elem : arr) {
            lines.add(new Gson().fromJson(elem, PtvLine.class));
        }
        return lines;
    }
}

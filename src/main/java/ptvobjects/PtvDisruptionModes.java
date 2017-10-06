package ptvobjects;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class PtvDisruptionModes {
    private final List<PtvDisruptionInformation> general;
    private final List<PtvDisruptionInformation> metroBus;
    private final List<PtvDisruptionInformation> metroTram;
    private final List<PtvDisruptionInformation> metroTrain;
    private final List<PtvDisruptionInformation> regionalBus;
    private final List<PtvDisruptionInformation> regionalTram;
    private final List<PtvDisruptionInformation> regionalTrain;

    /**
     * Constructor. Build object from {@link JsonObject} provided.
     *
     * @param obj
     *         - JSON Object
     */
    public PtvDisruptionModes(final JsonObject obj) {
        general = populateList("general", obj);
        metroBus = populateList("metro-bus", obj);
        metroTrain = populateList("metro-train", obj);
        metroTram = populateList("metro-tram", obj);
        regionalBus = populateList("regional-bus", obj);
        regionalTrain = populateList("regional-train", obj);
        regionalTram = populateList("regional-tram", obj);

    }

    /**
     * Populate and return the JSON array at the key specified
     *
     * @param name
     *         - key name of the array
     * @param obj
     *         - JSON object containing the array
     * @return Converted list
     */
    private List<PtvDisruptionInformation> populateList(final String name, final JsonObject obj) {
        final List<PtvDisruptionInformation> result = new ArrayList<PtvDisruptionInformation>();
        final Gson gson = new Gson();
        final JsonElement elem = obj.get(name);
        if (elem == null) {
            return result;
        }
        final JsonArray arr = gson.fromJson(elem, JsonArray.class);
        for (final JsonElement item : arr) {
            result.add(gson.fromJson(item, PtvDisruptionInformation.class));
        }
        return result;
    }

    public List<PtvDisruptionInformation> getGeneral() {
        return general;
    }

    public List<PtvDisruptionInformation> getMetroBus() {
        return metroBus;
    }

    public List<PtvDisruptionInformation> getMetroTram() {
        return metroTram;
    }

    public List<PtvDisruptionInformation> getMetroTrain() {
        return metroTrain;
    }

    public List<PtvDisruptionInformation> getRegionalBus() {
        return regionalBus;
    }

    public List<PtvDisruptionInformation> getRegionalTram() {
        return regionalTram;
    }

    public List<PtvDisruptionInformation> getRegionalTrain() {
        return regionalTrain;
    }
}

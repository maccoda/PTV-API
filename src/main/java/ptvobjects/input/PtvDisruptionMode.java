package ptvobjects.input;

import java.util.EnumMap;
import java.util.Map;

public class PtvDisruptionMode implements RequestInput {


    public enum DisruptionModes {
        GENERAL, METRO_BUS, METRO_TRAIN, METRO_TRAM, REGIONAL_TRAIN, REGIONAL_TRAM, REGIONAL_BUS
    }

    private final Map<DisruptionModes, Boolean> requestedModes;

    /**
     * Constructor.
     * <p>
     * Initializes the set of {@link DisruptionModes} to not present.
     */
    public PtvDisruptionMode() {
        requestedModes = new EnumMap<DisruptionModes, Boolean>(DisruptionModes.class);
    }

    /**
     * Adds the provided mode the requested set
     *
     * @param mode
     *         - mode to add
     * @return self for building requests
     */
    public PtvDisruptionMode setMode(final DisruptionModes mode) {
        requestedModes.put(mode, true);
        return this;
    }

    /**
     * Returns comma separated list of the set modes
     *
     * @return as above
     */
    @Override
    public String toUriRepresentation() {
        final StringBuilder builder = new StringBuilder();
        for (final DisruptionModes mode : requestedModes.keySet()) {
            if (requestedModes.get(mode)) {
                if (builder.length() > 0) {
                    builder.append(",");
                }
                builder.append(mode.name().toLowerCase().replace("_", "-"));
            }
        }
        return builder.toString();
    }
}

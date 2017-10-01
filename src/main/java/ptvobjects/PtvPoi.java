package ptvobjects;

import java.util.EnumMap;
import java.util.Map;

/**
 * Position of interest types. Can be represented as multiple in the single request. That is the POI does not just need
 * to be Train but can be Train AND Tram.
 */
public final class PtvPoi {
    public enum PtvPoiTypes {
        TRAIN(0), TRAM(1), BUS(2), VLINE(3), NIGHT_BUS(4), TICKET_OUTLET(100);
        /** Integer representation in the API request */
        private final int val;

        /** Constructor. */
        PtvPoiTypes(final int val) {
            this.val = val;
        }
    }

    /** Map of all POI values */
    private final Map<PtvPoiTypes, Boolean> values;

    /**
     * Constructor. Initializes all types to false.
     */
    public PtvPoi() {
        values = new EnumMap<PtvPoiTypes, Boolean>(PtvPoiTypes.class);
        for (final PtvPoiTypes type : PtvPoiTypes.values()) {
            values.put(type, false);
        }
    }

    /**
     * Add the specified POI type to the list that is desired to be returned.
     * <p>
     * Returns self for chaining.
     *
     * @param type
     *         - type to add
     * @return this
     */
    public PtvPoi setPoi(final PtvPoiTypes type) {
        values.put(type, true);
        return this;
    }

    /**
     * Returns a comma separated list of the values for the POI types that have been set to be used as part of the
     * request.
     *
     * @return as above
     */
    public String toUriRepresentation() {
        final StringBuilder builder = new StringBuilder();
        for (final PtvPoiTypes type : values.keySet()) {
            if (values.get(type)) {
                if (builder.length() > 0) {
                    builder.append(',');
                }
                builder.append(type.val);
            }
        }
        return builder.toString();
    }
}

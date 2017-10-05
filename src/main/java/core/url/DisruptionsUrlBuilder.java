package core.url;

import ptvobjects.input.PtvDisruptionMode;

/**
 * Builder for the Disruptions request.
 */
public final class DisruptionsUrlBuilder implements RequestUrlBuilder {
    private final PtvDisruptionMode mode;

    /**
     * Constructor
     *
     * @param aMode
     *         - disruption mode which has been set.
     */
    public DisruptionsUrlBuilder(final PtvDisruptionMode aMode) {
        mode = aMode;
    }

    @Override
    public String buildUrl() {
        // v2/disruptions/modes/%@?devid=%@&signature=%@
        String url = "/disruptions/";
        url += "modes/" + mode.toUriRepresentation();
        return url;
    }
}

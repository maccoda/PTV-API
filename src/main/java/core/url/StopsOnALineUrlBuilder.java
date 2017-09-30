package core.url;

import ptvobjects.PtvRouteType;

public final class StopsOnALineUrlBuilder implements RequestUrlBuilder {

    private final PtvRouteType mode;
    private final int lineId;

    /**
     * Constructor.
     *
     * @param mode
     *         - Mode of line
     * @param lineId
     *         - ID of line
     */
    public StopsOnALineUrlBuilder(final PtvRouteType mode, final int lineId) {
        this.mode = mode;
        this.lineId = lineId;
    }

    @Override
    public String buildUrl() {
        // Request URL = /v2/mode/%@/line/%@/stops-for-line?
        String uri = ("/mode/" + mode.getId());
        uri += ("/line/" + lineId);
        uri += ("/stops-for-line");

        return uri;
    }
}

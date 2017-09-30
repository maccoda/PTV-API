package core.url;

import ptvobjects.PtvRouteType;

/**
 * Builder for the Broad Next Departure request.
 */
public final class BroadNextDepartureUrlBuilder implements RequestUrlBuilder {

    private final PtvRouteType mode;
    private final int stopId;
    private final int limit;

    /**
     * Constructor.
     *
     * @param aMode
     *         - mode of transport
     * @param aStopId
     *         - stop id to get departures from
     * @param aLimit
     *         - limit of services to show
     */
    public BroadNextDepartureUrlBuilder(final PtvRouteType aMode, final int aStopId, final int aLimit) {
        mode = aMode;
        stopId = aStopId;
        limit = aLimit;
    }

    @Override
    public String buildUrl() {
        // Request URL = /v2/mode/%@/stop/%@/departures/by-destination/limit/%
        String uri = "/mode/" + mode.getId();
        uri += "/stop/" + stopId;
        uri += "/departures/by-destination";
        uri += "/limit/" + limit;

        return uri;
    }
}

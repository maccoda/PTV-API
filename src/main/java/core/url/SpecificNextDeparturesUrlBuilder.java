package core.url;

import ptvobjects.PtvRouteType;

public final class SpecificNextDeparturesUrlBuilder implements RequestUrlBuilder {

    private final PtvRouteType type;
    private final int lineId;
    private final int stopId;
    private final int directionId;
    private final int limit;

    private final String forUtc;

    public SpecificNextDeparturesUrlBuilder(final PtvRouteType type, final int lineId, final int stopId, final int directionId, final int limit, final String forUtc) {
        this.type = type;
        this.lineId = lineId;
        this.stopId = stopId;
        this.directionId = directionId;
        this.limit = limit;
        this.forUtc = forUtc;
    }

    @Override
    public String buildUrl() {
        String uri = "/mode/" + type.getId();
        uri += "/line/" + lineId;
        uri += "/stop/" + stopId;
        uri += "/directionid/" + directionId;
        uri += "/departures/all";
        uri += "/limit/" + limit;
        if (forUtc != "") {
            uri += "?for_utc=" + forUtc;
        }
        return uri;
    }
}

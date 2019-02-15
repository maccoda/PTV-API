package core.url.v3;

import ptvobjects.PtvRouteType;

public class DeparturesRequest implements Request {
    private PtvRouteType routeType;
    private int stopId;

    public DeparturesRequest(PtvRouteType routeType, int stopId) {
        this.routeType = routeType;
        this.stopId = stopId;
    }

    @Override
    public String toUrl() {
        UrlPathBuilder builder = new UrlPathBuilder();
        builder.appendPathSegment("departures");
        builder.appendPathSegment("route_type");
        builder.appendPathSegment(Integer.toString(routeType.getId()));
        builder.appendPathSegment("stop");
        builder.appendPathSegment(Integer.toString(stopId));
        return builder.build();
    }
}

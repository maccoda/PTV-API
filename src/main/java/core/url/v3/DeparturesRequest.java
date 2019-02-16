package core.url.v3;

import ptvobjects.RouteType;

public class DeparturesRequest implements Request {
    private final RouteType routeType;
    private final int stopId;

    public DeparturesRequest(final RouteType routeType, final int stopId) {
        this.routeType = routeType;
        this.stopId = stopId;
    }

    @Override
    public String toUrl() {
        final UrlPathBuilder builder = new UrlPathBuilder();
        builder.appendPathSegment("departures");
        builder.appendPathSegment("route_type");
        builder.appendPathSegment(Integer.toString(routeType.getId()));
        builder.appendPathSegment("stop");
        builder.appendPathSegment(Integer.toString(stopId));
        return builder.build();
    }
}

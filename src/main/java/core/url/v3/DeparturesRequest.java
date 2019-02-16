package core.url.v3;

import ptvobjects.RouteType;

public class DeparturesRequest implements Request {
    private final RouteType routeType;
    private final int stopId;
    private final Integer routeId;

    private DeparturesRequest(final RouteType routeType, final int stopId, final Integer routeId) {
        this.routeType = routeType;
        this.stopId = stopId;
        this.routeId = routeId;
    }

    @Override
    public String toUrl() {
        final UrlPathBuilder builder = new UrlPathBuilder();
        builder.appendPathSegment("v3");
        builder.appendPathSegment("departures");
        builder.appendPathSegment("route_type");
        builder.appendPathSegment(routeType.getId());
        builder.appendPathSegment("stop");
        builder.appendPathSegment(stopId);
        if (routeId != null) {
            builder.appendPathSegment("route");
            builder.appendPathSegment(routeId);
        }
        return builder.build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private RouteType routeType;
        private int stopId;
        private Integer routeId;

        public Builder withRouteType(final RouteType type) {
            routeType = type;
            return this;
        }

        public Builder withStopId(final int stopId) {
            this.stopId = stopId;
            return this;
        }

        public Builder withRouteId(final int routeId) {
            this.routeId = routeId;
            return this;
        }

        public DeparturesRequest build() {
            return new DeparturesRequest(routeType, stopId, routeId);
        }
    }
}

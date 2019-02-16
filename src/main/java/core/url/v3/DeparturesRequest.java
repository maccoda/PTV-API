package core.url.v3;

import ptvobjects.RouteType;

import java.util.Optional;

public class DeparturesRequest implements Request {
    private final RouteType routeType;
    private final int stopId;
    private final Optional<Integer> routeId;

    private DeparturesRequest(final RouteType routeType, final int stopId, final Optional<Integer> routeId) {
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
        routeId.ifPresent(id -> {
            builder.appendPathSegment("route");
            builder.appendPathSegment(id);
        });
        return builder.build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private RouteType routeType;
        private int stopId;
        private Optional<Integer> routeId;

        Builder() {
            routeId = Optional.empty();
        }

        public Builder withRouteType(final RouteType type) {
            routeType = type;
            return this;
        }

        public Builder withStopId(final int stopId) {
            this.stopId = stopId;
            return this;
        }

        public Builder withRouteId(final int routeId) {
            this.routeId = Optional.of(routeId);
            return this;
        }

        public DeparturesRequest build() {
            return new DeparturesRequest(routeType, stopId, routeId);
        }
    }
}

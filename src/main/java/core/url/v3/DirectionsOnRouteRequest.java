package core.url.v3;

public class DirectionsOnRouteRequest implements Request {
    private final int routeId;

    private DirectionsOnRouteRequest(final int routeId) {
        this.routeId = routeId;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toUrl() {
        final UrlPathBuilder builder = new UrlPathBuilder()
                .appendPathSegment("v3").appendPathSegment("directions")
                .appendPathSegment("route").appendPathSegment(routeId);
        return builder.build();
    }

    public static class Builder {
        private int routeId;

        public Builder withRouteId(final int routeId) {
            this.routeId = routeId;
            return this;
        }

        public DirectionsOnRouteRequest build() {
            return new DirectionsOnRouteRequest(routeId);
        }
    }
}

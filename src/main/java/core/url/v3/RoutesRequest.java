package core.url.v3;

public class RoutesRequest implements Request {

    private final Integer routeId;

    RoutesRequest(final Integer routeId) {
        this.routeId = routeId;
    }

    @Override
    public String toUrl() {
        final UrlPathBuilder builder = new UrlPathBuilder().appendPathSegment("v3").appendPathSegment("routes");
        if (routeId != null) {
            builder.appendPathSegment(routeId);
        }
        return builder.build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer routeId;

        Builder withRouteId(final Integer id) {
            routeId = id;
            return this;
        }

        public RoutesRequest build() {
            return new RoutesRequest(routeId);
        }
    }
}

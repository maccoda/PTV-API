package core.url.v3;

public class DirectionsRequest implements Request {
    private final int routeId;

    public DirectionsRequest(final int routeId) {
        this.routeId = routeId;
    }

    @Override
    public String toUrl() {
        final UrlPathBuilder builder = new UrlPathBuilder();
        builder.appendPathSegment("v3");
        builder.appendPathSegment("directions");
        builder.appendPathSegment("route");
        builder.appendPathSegment(Integer.toString(routeId));
        return builder.build();
    }
}

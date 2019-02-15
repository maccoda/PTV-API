package core.url.v3;

public class DirectionsRequest implements Request {
    private final int routeId;

    public DirectionsRequest(int routeId) {
        this.routeId = routeId;
    }

    @Override
    public String toUrl() {
        UrlPathBuilder builder = new UrlPathBuilder();
        builder.appendPathSegment("directions");
        builder.appendPathSegment("route");
        builder.appendPathSegment(Integer.toString(routeId));
        return builder.build();
    }
}

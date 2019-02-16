package core.url.v3;

public class RoutesRequest implements Request {
    @Override
    public String toUrl() {
        final UrlPathBuilder builder = new UrlPathBuilder();
        builder.appendPathSegment("v3");
        builder.appendPathSegment("routes");
        return builder.build();
    }
}

package core.url.v3;

public class RoutesRequest implements Request {
    @Override
    public String toUrl() {
        UrlPathBuilder builder = new UrlPathBuilder();
        builder.appendPathSegment("routes");
        return builder.build();
    }
}

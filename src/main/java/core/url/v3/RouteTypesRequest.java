package core.url.v3;

public class RouteTypesRequest implements Request {
    private RouteTypesRequest() {
    }

    @Override
    public String toUrl() {
        return new UrlPathBuilder().appendPathSegment("v3").appendPathSegment("route_types").build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        public RouteTypesRequest build() {
            return new RouteTypesRequest();
        }
    }
}

package core.url.v3;

public class UrlPathBuilder {
    private StringBuilder builder;
    private boolean hasQueryParams;

    public UrlPathBuilder() {
        builder = new StringBuilder();
    }

    public void appendPathSegment(String segment) {
        builder.append("/").append(segment);
    }


    public void appendQueryParam(String key, String value) {
        if (hasQueryParams) {
            builder.append("&");
        } else {
            builder.append("?");
            hasQueryParams = true;
        }
        builder.append(key).append("=").append(value);
    }

    public String build() {
        return builder.toString();
    }

    @Override
    public String toString() {
        return build();
    }
}

package core.url.v3;

public class UrlPathBuilder {
    private final StringBuilder builder;
    private boolean hasQueryParams;

    public UrlPathBuilder() {
        builder = new StringBuilder();
    }

    public void appendPathSegment(final String segment) {
        if (!segment.startsWith("/")) {
            builder.append("/");
        }
        builder.append(segment);
    }


    public void appendQueryParam(final String key, final String value) {
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

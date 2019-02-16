package core.url.v3;

public class UrlPathBuilder {
    private final StringBuilder builder;
    private boolean hasQueryParams;

    public UrlPathBuilder() {
        builder = new StringBuilder();
    }

    public UrlPathBuilder appendPathSegment(final String segment) {
        if (!segment.startsWith("/")) {
            builder.append("/");
        }
        builder.append(segment);
        return this;
    }

    UrlPathBuilder appendPathSegment(final Integer i) {
        return appendPathSegment(Integer.toString(i));
    }


    public UrlPathBuilder appendQueryParam(final String key, final String value) {
        if (hasQueryParams) {
            builder.append("&");
        } else {
            builder.append("?");
            hasQueryParams = true;
        }
        builder.append(key).append("=").append(value);
        return this;
    }

    public String build() {
        return builder.toString();
    }

    @Override
    public String toString() {
        return build();
    }
}

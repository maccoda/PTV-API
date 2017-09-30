package core.url;

public final class SearchUrlBuilder implements RequestUrlBuilder {
    private final String searchString;

    /**
     * Constructor.
     *
     * @param searchString
     *         - search string for request
     */
    public SearchUrlBuilder(final String searchString) {
        this.searchString = searchString;
    }

    @Override
    public String buildUrl() {
        // Request URL = /v2/search/%@?
        final String uri = "/search/" + searchString + "?";

        return uri;
    }
}

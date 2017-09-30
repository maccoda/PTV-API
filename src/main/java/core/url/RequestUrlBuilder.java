package core.url;

/**
 * Builder that produces a valid API URL for a given request.
 */
public interface RequestUrlBuilder {

    /**
     * Build the URL of the request. This does not include the base URL or the signature.
     *
     * @return as above
     */
    String buildUrl();
}

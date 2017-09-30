package core.url;

/**
 * URL build for Stops Nearby request.
 */
public final class StopsNearbyUrlBuilder implements RequestUrlBuilder {

    private final double latitude;
    private final double longitude;

    /**
     * Constructor.
     *
     * @param aLat
     *         - latitude for request
     * @param aLon
     *         - longitude for request
     */
    public StopsNearbyUrlBuilder(final double aLat, final double aLon) {
        latitude = aLat;
        longitude = aLon;
    }

    @Override
    public String buildUrl() {
        String uri = "/nearme";
        uri += "/latitude/" + latitude;
        uri += "/longitude/" + longitude;

        return uri;
    }
}

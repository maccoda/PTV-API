package core.url;

import ptvobjects.PtvPoi;

public final class TransportPoiByMap implements RequestUrlBuilder {
    public static final int GRID_DEPTH_MAX = 20;
    public static final int GRID_DEPTH_MIN = 0;
    private final PtvPoi poi;
    private final double latitude1;
    private final double longitude1;
    private final double latitude2;
    private final double longitude2;
    private final byte gridDepth;
    private final int limit;

    /**
     * Constructor.
     *
     * @param poi
     *         - PTV POI type
     * @param latitude1
     *         - latitude of top left
     * @param longitude1
     *         - longitude of top left
     * @param latitude2
     *         - latitude of bottom right
     * @param longitude2
     *         - longitude of bottom right
     * @param gridDepth
     *         - number of cells per block of cluster grid (0-20)
     * @param limit
     *         - minimum number of POIs to create a cluster
     */
    public TransportPoiByMap(final PtvPoi poi, final double latitude1, final double longitude1, final double latitude2, final double longitude2, final byte gridDepth, final int limit) {
        this.poi = poi;
        this.latitude1 = latitude1;
        this.longitude1 = longitude1;
        this.latitude2 = latitude2;
        this.longitude2 = longitude2;
        if (gridDepth < GRID_DEPTH_MIN || gridDepth > GRID_DEPTH_MAX) {
            throw new IllegalArgumentException("gridDepth must be between " + GRID_DEPTH_MIN + "-" + GRID_DEPTH_MAX + " inclusive");
        }
        this.gridDepth = gridDepth;
        this.limit = limit;
    }

    @Override
    public String buildUrl() {
        // /poi/%@/lat1/%@/long1/%@/lat2/%@/long2/%@/griddepth/%@/limit/%@
        String uri = "/poi/" + poi.toUriRepresentation();
        uri += "/lat1/" + latitude1;
        uri += "/long1/" + longitude1;
        uri += "/lat2/" + latitude2;
        uri += "/long2/" + longitude2;
        uri += "/griddepth/" + gridDepth;
        uri += "/limit/" + limit;
        return uri;
    }
}

package core.url;

import ptvobjects.PtvRouteType;

/**
 * URL builder for the Stop Facilities request
 */
public final class StopFacilitiesUrlBuilder implements RequestUrlBuilder {

    private final int stopId;
    private final PtvRouteType type;
    private final boolean location;
    private final boolean amenity;
    private final boolean accessibility;

    /**
     * Constructor.
     *
     * @param stopId
     *         - ID number of the stop
     * @param type
     *         - Route type. Only valid options are Train and VLine
     * @param location
     *         - Boolean switch that turns the location category filter on or off
     * @param amenity
     *         - Boolean switch that turns the amenity location category filter on or off
     * @param accessibility
     *         - Boolean switch that turns the accessibility category filter on or off
     * @throws IllegalArgumentException
     *         if incorrect type given
     */
    public StopFacilitiesUrlBuilder(final int stopId, final PtvRouteType type, final boolean location, final boolean amenity, final boolean accessibility) {
        this.stopId = stopId;
        if (type != PtvRouteType.Train && type != PtvRouteType.VLine) {
            throw new IllegalArgumentException("Allowed modes are Train and VLine");
        }
        this.type = type;
        this.location = location;
        this.amenity = amenity;
        this.accessibility = accessibility;
    }

    @Override
    public String buildUrl() {
        ///v2/stops/?stop_id=%@&route_type=%@&location=%@&amenity=%@&accessibility=%@&devid=%@&signature=%@
        String uri = "/stops/";
        uri += "?stop_id=" + stopId;
        uri += "&route_type=" + type.getId();
        uri += "&location=" + (location ? 1 : 0);
        uri += "&amenity=" + (amenity ? 1 : 0);
        uri += "&accessibility=" + (accessibility ? 1 : 0);

        // The PTV specification says that the filters can be omitted if not desired but will make it explicit
        // and set the boolean value as 0 which returns the same result
        return uri;
    }
}

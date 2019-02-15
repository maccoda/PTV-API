package core;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import core.url.BroadNextDepartureUrlBuilder;
import core.url.DisruptionsUrlBuilder;
import core.url.HealthRequestUrlBuilder;
import core.url.LinesByModeUrlBuilder;
import core.url.RequestUrlBuilder;
import core.url.SearchUrlBuilder;
import core.url.SpecificNextDeparturesUrlBuilder;
import core.url.StopFacilitiesUrlBuilder;
import core.url.StoppingPatternUrlBuilder;
import core.url.StopsNearbyUrlBuilder;
import core.url.StopsOnALineUrlBuilder;
import core.url.TransportPoiByMap;
import ptvobjects.PtvBasicObject;
import ptvobjects.PtvDisruptionModes;
import ptvobjects.PtvHealth;
import ptvobjects.PtvLine;
import ptvobjects.PtvLocationCluster;
import ptvobjects.PtvObject;
import ptvobjects.PtvResult;
import ptvobjects.PtvRouteType;
import ptvobjects.PtvStopFacilities;
import ptvobjects.PtvTimetableValues;
import ptvobjects.builders.LinesListBuilder;
import ptvobjects.builders.PtvListObjectBuilder;
import ptvobjects.builders.ResultListBuilder;
import ptvobjects.input.PtvDisruptionMode;
import ptvobjects.input.PtvPoi;

import java.util.List;

/**
 * Class to form required data for sending of PTV API request.
 *
 * @author D. Maccora
 */
@Deprecated
public final class PtvRequest {

    /**
     * URL factory for creation of API requests.
     */
    private final UrlSignatureDecorator urls;

    /**
     * Constructor. Builds context from provided data for generation of API requests.
     *
     * @param privateKey
     *         - user private key
     * @param developerId
     *         - developer ID
     */
    public PtvRequest(final String privateKey, final int developerId) {
        // TODO Change this when able to support multiple versions
        urls = new UrlSignatureDecorator(UrlSignatureDecorator.ApiVersion.V2, privateKey, developerId);
    }

    /**
     * Issue 'Health Check' request as per PTV API. This should be called every time a sequence of calls are made to the
     * API to ensure results are as expected.
     *
     * @return Health status report of the PTV services.
     */
    public PtvHealth performHealthCheck() {
        return sendApiRequest(new HealthRequestUrlBuilder(), PtvHealth.class);
    }

    /**
     * Issue "Broad Next Departures" request as per PTV API. This request returns the next departure times at a
     * prescribed stop irrespective of the line and direction of the service.
     *
     * @param mode
     *         - Transportation mode as specified by PTV
     * @param stopId
     *         - ID of the stop for which want to obtain the next stops
     * @param limit
     *         - Upper limit on departures returned
     * @return - List of PTVTimetables for each departure
     */
    public PtvTimetableValues performBroadNextDepartureRequest(final PtvRouteType mode, final int stopId, final int limit) {
        return sendApiRequest(new BroadNextDepartureUrlBuilder(mode, stopId, limit), PtvTimetableValues.class);
    }

    /**
     * Issue "Stops Nearby" request as per PTV API. This request returns stops nearest to the specified location.
     *
     * @param latitude
     *         - latitude of location
     * @param longitude
     *         - longitude of location
     * @return List of {@link PtvResult}
     */
    public List<PtvResult> performStopsNearbyRequest(final double latitude, final double longitude) {
        return sendListApiRequest(new StopsNearbyUrlBuilder(latitude, longitude), new ResultListBuilder());
    }

    /**
     * Issue "Lines by Mode" request as per PTV API. This request returns the lines for a selected mode of transport.
     * The name parameter is optional, allowing the results to be filtered.
     *
     * @param mode
     *         - Transportation mode
     * @param name
     *         - Name to filter on a specific line, empty string if not required
     * @return - List of PtvLine objects for each line matching
     */
    public List<PtvLine> performLinesByModeRequest(final PtvRouteType mode, final String name) {
        return sendListApiRequest(new LinesByModeUrlBuilder(mode, name), new LinesListBuilder());
    }

    /**
     * Issue a <i>Search</i> request. This returns all stops and lines that match the input search test.
     *
     * @param searchString
     *         - String to search
     * @return List of matching results
     */
    public List<PtvResult> performSearchRequest(final String searchString) {
        return sendListApiRequest(new SearchUrlBuilder(searchString), new ResultListBuilder());
    }

    /**
     * Issues a <i>stopsOnALine</i> request. This returns a list of all stops on the requested line. This will be
     * ordered by location name.
     *
     * @param type
     *         - {@link PtvRouteType} for the line
     * @param lineId
     *         - ID of the line to query
     * @return List of all stops on the line
     */
    public List<PtvLine> performStopsOnALineRequest(final PtvRouteType type, final int lineId) {
        return sendListApiRequest(new StopsOnALineUrlBuilder(type, lineId), new LinesListBuilder());
    }

    /**
     * Issues a <i>transportPoiByMap</i> request.
     *
     * @param poi
     *         - list of POIs
     * @param latitude1
     *         - top left latitude
     * @param longitude1
     *         - top left longitude
     * @param latitude2
     *         - bottom right latitude
     * @param longitude2
     *         - bottom right longitude
     * @param gridDepth
     *         - grid depth
     * @param limit
     *         - limit for cluster
     * @return PtvLocationCluster Object
     */
    public PtvLocationCluster performTransportPoiByMap(final PtvPoi poi, final double latitude1, final double longitude1, final double latitude2, final double longitude2, final byte gridDepth, final int limit) {
        return sendApiRequest(new TransportPoiByMap(poi, latitude1, longitude1, latitude2, longitude2, gridDepth, limit), PtvLocationCluster.class);
    }

    public PtvTimetableValues performSpecificNexDeparture(final PtvRouteType type, final int lineId, final int stopId, final int directionId, final int limit, final String forUtc) {
        return sendApiRequest(new SpecificNextDeparturesUrlBuilder(type, lineId, stopId, directionId, limit, forUtc), PtvTimetableValues.class);
    }

    public PtvTimetableValues performStoppingPattern(final PtvRouteType mode, final int runId, final int stopId, final String forUtc) {
        return sendApiRequest(new StoppingPatternUrlBuilder(mode, runId, stopId, forUtc), PtvTimetableValues.class);
    }

    public PtvDisruptionModes performDisruptions(final PtvDisruptionMode mode) {
        // Has keys containing hyphens so need to do some special work
        final String uri = urls.buildUrl(new DisruptionsUrlBuilder(mode));
        final String response = QueryHandler.sendQuery(uri);
        return new PtvDisruptionModes(new Gson().fromJson(response, JsonObject.class));
    }

    public PtvStopFacilities performStopFacilities(final int stopId, final PtvRouteType type, final boolean location, final boolean amenity, final boolean accessibility) {
        return sendApiRequest(new StopFacilitiesUrlBuilder(stopId, type, location, amenity, accessibility), PtvStopFacilities.class);
    }

    /**
     * Build the complete API query by appending the developer ID and signature for any request. It then sends this
     * query to the API and parses the response into the specified type.
     *
     * @param urlBuilder
     *         - Request builder.
     * @param clazz
     *         - Data type to return
     * @return - JSON object response.
     */
    private <T extends PtvBasicObject> T sendApiRequest(final RequestUrlBuilder urlBuilder, final Class<T> clazz) {
        final String uri = urls.buildUrl(urlBuilder);
        final String response = QueryHandler.sendQuery(uri);
        return QueryHandler.parseQueryResult(response, clazz);
    }

    /**
     * Build the complete API query by appending the developer ID and signature for any request that results in a
     * collection being returned. It then sends this query to the API and parses the response into a collection of
     * {@link PtvObject}.
     *
     * @param urlBuilder
     *         - Request builder.
     * @param builder
     *         - list builder for the type
     * @return - JSON object response.
     */
    private <T extends PtvObject> List<T> sendListApiRequest(final RequestUrlBuilder urlBuilder, final PtvListObjectBuilder<T> builder) {
        final String uri = urls.buildUrl(urlBuilder);
        final String response = QueryHandler.sendQuery(uri);
        return builder.populateList(new Gson().fromJson(response, JsonArray.class));
    }

}

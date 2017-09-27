package core;

import exception.RequestException;
import ptvobjects.*;

/**
 * Class to form required data for sending of PTV API request.
 *
 * @author D. Maccora
 */
public final class PtvRequest {

    /**
     * URL factory for creation of API requests
     */
    private final UrlFactory urls;

    public PtvRequest(final String privateKey, final int developerId) {
        // TODO Change this when able to support multiple versions
        urls = new UrlFactory(UrlFactory.ApiVersion.V2, privateKey, developerId);
    }

    /**
     * Issue 'Health Check' request as per PTV API. This should be called every time a sequence of
     * calls are made to the API to ensure results are as expected.
     *
     * @return Health status report of the PTV services.
     */
    public PtvHealth performHealthCheck() throws RequestException {
        final PtvHealth result;
        try {
            return sendApiRequest(urls.healthCheck(), PtvHealth.class);
        } catch (final Exception e) {
            throw new RequestException("performHealthCheck::unable to build and send request");
        }
    }

    /**
     * Issue "Broad Next Departures" request as per PTV API. This request returns the next departure
     * times at a prescribed stop irrespective of the line and direction of the service.
     *
     * @param mode
     *         - Transportation mode as specified by PTV
     * @param stopId
     *         - ID of the stop for which want to obtain the next stops
     * @param limit
     *         - Upper limit on departures returned
     * @return - Array of PTVTimetables for each departure
     */
    public PtvTimetableValues performBroadNextDepartureRequest(final PtvRouteType mode, final int stopId, final int limit)
            throws RequestException {
        try {
            return sendApiRequest(urls.broadNextDeparture(mode, stopId, limit), PtvTimetableValues.class);
        } catch (final Exception e) {
            throw new RequestException("performBroadNextDepartureRequest::Unable to build and send API request");
        }
    }

    public PtvResultList performStopsNearbyRequest(final double latitude, final double longitude) throws RequestException {
        final PtvResultList result;
        try {
            return sendApiRequest(urls.stopsNearby(latitude, longitude), PtvResultList.class);

        } catch (final Exception e) {
            throw new RequestException("performBroadNextDepartureRequest::Unable to build and send API request");
        }
    }

    /**
     * Issue "Lines by Mode" request as per PTV API. This request returns the lines for a selected mode of transport.
     * The name parameter is optional, allowing the results to be filtered.
     *
     * @param mode
     *         - Transportation mode
     * @param name
     *         - Name to filter on a specific line, empty string if not required
     * @return - Collection of PtvLine objects for each line matching
     */
    public PtvLineList performLinesByModeRequest(final PtvRouteType mode, final String name) throws RequestException {
        try {
            // TODO Make a list wrapper class
            return sendApiRequest(urls.linesByMode(mode, name), PtvLineList.class);
        } catch (final Exception e) {
            throw new RequestException("performLinesByModeRequest::Unable to build and send API request");
        }
    }

    /**
     * Issue a <i>Search</i> request. This returns all stops and lines that match the input search test.
     *
     * @param searchString
     *         - String to search
     * @return List of matching results
     *
     * @throws RequestException
     *         if unable to perform request
     */
    public PtvResultList performSearchRequest(final String searchString) throws RequestException {
        try {
            return sendApiRequest(urls.search(searchString), PtvResultList.class);
        } catch (final Exception e) {
            throw new RequestException("performSearchRequest::Unable to build and send API request");
        }
    }

    /**
     * Issues a <i>stopsOnALine</i> request. This returns a list of all stops on the requested line.
     * This will be ordered by location name.
     *
     * @param type
     *         - {@link PtvRouteType} for the line
     * @param lineId
     *         - ID of the line to query
     * @return List of all stops on the line
     *
     * @throws RequestException
     *         if unable to perform request
     */
    public PtvLineList performStopsOnALineRequest(final PtvRouteType type, final int lineId) throws RequestException {
        try {
            return sendApiRequest(urls.stopsOnALine(type, lineId), PtvLineList.class);
        } catch (final Exception e) {
            throw new RequestException("performLinesByModeRequest::Unable to build and send API request");
        }

    }

    /**
     * Build the complete API query by appending the developer ID and signature for any request. It
     * then sends this query to the API and parses the response into a JSON object. In the case of a
     * failed parsing it will return an empty JSON object.
     *
     * @param uri
     *         - Request to be sent.
     * @param clazz
     *         - Data type to return
     * @return - JSON object response.
     */
    private <T> T sendApiRequest(final String uri, final Class<T> clazz) {
        final String response = QueryHandler.sendQuery(uri);
        return QueryHandler.parseQueryResult(response, clazz);
    }

}

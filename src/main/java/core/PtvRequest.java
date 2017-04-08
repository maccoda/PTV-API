package core;

import exception.RequestException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ptvobjects.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            final JSONObject healthObject = sendApiRequest(urls.healthCheck());
            result = new PtvHealth(healthObject);
            return result;
        } catch (final Exception e) {
            throw new RequestException("performHealthCheck::unable to build and send request");
        }
    }

    /**
     * Issue "Broad Next Departures" request as per PTV API. This request returns the next departure
     * times at a prescribed stop irrespective of the line and direction of the service.
     *
     * @param mode   - Transportation mode as specified by PTV
     * @param stopId - ID of the stop for which want to obtain the next stops
     * @param limit  - Upper limit on departures returned
     * @return - Array of PTVTimetables for each departure
     */
    public PtvTimetableValues performBroadNextDepartureRequest(final PtvRouteType mode, final int stopId, final int limit)
            throws RequestException {
        final PtvTimetableValues result;
        try {
            final JSONObject object = sendApiRequest(urls.broadNextDeparture(mode, stopId, limit));

            // Decode the object returned
            Logger.getLogger(this.getClass().getSimpleName()).log(Level.INFO, "Populating fields");
            result = new PtvTimetableValues(object);

        } catch (final Exception e) {
            throw new RequestException("performBroadNextDepartureRequest::Unable to build and send API request");
        }
        return result;
    }

    public PtvResultList performStopsNearbyRequest(final double latitude, final double longitude) throws RequestException {
        final PtvResultList result;
        try {
            final JSONArray object = sendApiRequest(urls.stopsNearby(latitude, longitude));

            Logger.getLogger(this.getClass().getSimpleName()).log(Level.INFO, "Populating fields for PtvResultList");
            result = new PtvResultList(object);

        } catch (final Exception e) {
            throw new RequestException("performBroadNextDepartureRequest::Unable to build and send API request");
        }
        return result;
    }

    /**
     * Issue "Lines by Mode" request as per PTV API. This request returns the lines for a selected mode of transport.
     * The name parameter is optional, allowing the results to be filtered.
     *
     * @param mode - Transportation mode
     * @param name - Name to filter on a specific line, empty string if not required
     * @return - Collection of PtvLine objects for each line matching
     */
    public List<PtvLine> performLinesByModeRequest(final PtvRouteType mode, final String name) throws RequestException {
        final List<PtvLine> results = new ArrayList<PtvLine>();
        try {
            final JSONArray object = sendApiRequest(urls.linesByMode(mode, name));
            final Iterator<JSONObject> iter = object.iterator();
            while (iter.hasNext()) {
                results.add(new PtvLine(iter.next()));
            }
        } catch (final Exception e) {
            throw new RequestException("performLinesByModeRequest::Unable to build and send API request");
        }

        return results;
    }

    /**
     * Issue a <i>Search</i> request. This returns all stops and lines that match the input search test.
     *
     * @param searchString - String to search
     * @return List of matching results
     * @throws RequestException if unable to perform request
     */
    public List<PtvResult> performSearchRequest(final String searchString) throws RequestException {
        final List<PtvResult> results = new ArrayList<PtvResult>();
        try {
            final JSONArray object = sendApiRequest(urls.search(searchString));
            final Iterator<JSONObject> iter = object.iterator();
            while (iter.hasNext()) {
                results.add(new PtvResult(iter.next()));
            }

        } catch (final Exception e) {
            throw new RequestException("performSearchRequest::Unable to build and send API request");
        }

        return results;
    }

    /**
     * Issues a <i>stopsOnALine</i> request. This returns a list of all stops on the requested line.
     * This will be ordered by location name.
     *
     * @param type   - {@link PtvRouteType} for the line
     * @param lineId - ID of the line to query
     * @return List of all stops on the line
     * @throws RequestException if unable to perform request
     */
    public List<PtvLine> performStopsOnALineRequest(final PtvRouteType type, final int lineId) throws RequestException {
        final List<PtvLine> result = new ArrayList<PtvLine>();
        try {
            final JSONArray object = sendApiRequest(urls.stopsOnALine(type, lineId));
            final Iterator<JSONObject> iter = object.iterator();
            while (iter.hasNext()) {
                result.add(new PtvLine(iter.next()));
            }
        } catch (final Exception e) {
            throw new RequestException("performLinesByModeRequest::Unable to build and send API request");
        }

        return result;
    }

    /**
     * Build the complete API query by appending the developer ID and signature for any request. It
     * then sends this query to the API and parses the response into a JSON object. In the case of a
     * failed parsing it will return an empty JSON object.
     *
     * @param uri - Request to be sent.
     * @return - JSON object response.
     */
    private <T> T sendApiRequest(final String uri) {
        final String response = QueryHandler.sendQuery(uri);
        return QueryHandler.parseQueryResult(response);
    }

}

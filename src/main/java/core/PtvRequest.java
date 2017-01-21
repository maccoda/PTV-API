package core;

import exception.RequestException;
import org.json.simple.JSONObject;
import ptvobjects.PtvHealth;
import ptvobjects.PtvLine;
import ptvobjects.PtvRouteType;
import ptvobjects.PtvTimetableValues;
import util.QueryHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to form required data for sending of PTV API request.
 *
 * @author D. Maccora
 */
public class PtvRequest {

  private final QueryHandler queryHandler;

  public PtvRequest(String privateKey, int developerId) {
    queryHandler = new QueryHandler(privateKey, developerId);
  }

  /**
   * Issue 'Health Check' request as per PTV API. This should be called every time a sequence of
   * calls are made to the API to ensure results are as expected.
   *
   * @return Health status report of the PTV services.
   */
  public PtvHealth performHealthCheck() throws RequestException {
    final String uri = "/v2/healthcheck";

    PtvHealth result;
    StringBuilder builder = new StringBuilder().append(uri);
    builder.append("?timestamp=");
    builder.append(getCurrentTimeIso8061Utc());
    try {
      JSONObject healthObject = buildAndSendApiRequest(builder.toString());
      result = new PtvHealth(healthObject);
      return result;
    } catch (Exception e) {
      throw new RequestException("performHealthCheck::unable to build and send request");
    }

  }

  /**
   * Method to format the current UTC time into the expected format for API which is ISO-8061.
   *
   * @return Current time in ISO-8061 format.
   */
  private String getCurrentTimeIso8061Utc() {
    TimeZone tz = TimeZone.getTimeZone("UTC");
    DateFormat format = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss'Z'");
    format.setTimeZone(tz);
    String nowAsIso = format.format(new Date());
    return nowAsIso;
  }

  /**
   * Issue "Broad Next Departures" request as per PTV API. This request returns the next departure
   * times at a prescribed stop irrespective of the line and direction of the service.
   *
   * @param mode   - Transportation mode as specified by PTV
   * @param stopId - ID of the stop for which want to obtain the next stops
   * @param limit  - Upper limit on departures returned
   *
   * @return - Array of PTVTimetables for each departure
   */
  public PtvTimetableValues performBroadNextDepartureRequest(PtvRouteType mode, int stopId, int limit)
          throws RequestException {
    // Request URL = /v2/mode/%@/stop/%@/departures/by-destination/limit/%
    StringBuilder uri = new StringBuilder().append("/v2");
    // Add the transport mode as the index value
    uri.append("/mode/" + mode.getId());
    // Add the stop id
    uri.append("/stop/" + stopId);
    // Add the other part
    uri.append("/departures/by-destination");
    // Add the limit of services to show
    uri.append("/limit/" + limit);

    PtvTimetableValues result;
    try {
      JSONObject object = buildAndSendApiRequest(uri.toString());

      // Decode the object returned
      Logger.getLogger(this.getClass().getSimpleName()).log(Level.INFO, "Populating fields");
      result = new PtvTimetableValues(object);

    } catch (Exception e) {
      throw new RequestException("performBroadNextDepartureRequest::Unable to build and send API request");
    }
    return result;
  }

  /**
   * Issue "Lines by Mode" request as per PTV API. This request returns the lines for a selected mode of transport.
   * The name parameter is optional, allowing the results to be filtered.
   *
   * @param mode - Transportation mode
   * @param name - Name to filter on a specific line
   *
   * @return - Collection of PtvLine objects for each line matching
   */
  public PtvLine performLinesByModeRequest(PtvRouteType mode, String name) throws RequestException {
    // Request URL = /v2/lines/mode/%@?name=%@
    StringBuilder uri = new StringBuilder().append("/v2");
    uri.append("/lines/mode/" + mode.getId() + "?");
    uri.append("name=" + name.trim());

    PtvLine result;
    try {
      JSONObject object = buildAndSendApiRequest(uri.toString());

      // Decode the object returned
      Logger.getLogger(this.getClass().getSimpleName()).log(Level.INFO, "Populating fields");
      result = new PtvLine(object);

    } catch (Exception e) {
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
   *
   * @return - JSON object response.
   */
  private JSONObject buildAndSendApiRequest(String uri) {
    String finalUri = queryHandler.buildQuery(uri);
    String response = queryHandler.sendQuery(finalUri);
    return queryHandler.parseQueryResult(response);
  }

}

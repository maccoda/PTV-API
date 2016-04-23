package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.Key;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import main.ptvobjects.PtvHealth;
import main.ptvobjects.PtvRouteType;
import main.ptvobjects.PtvTimetable;

/**
 * Class to form required data for sending of PTV API request.
 *
 * @author dmaccora
 *
 */
public class PtvRequest {
  private final String baseUrl = "http://timetableapi.ptv.vic.gov.au";
  private final String privateKey;
  private final int developerId;

  public PtvRequest(String privateKey, int developerId) {
    this.privateKey = privateKey;
    this.developerId = developerId;
  }

  /**
   * Issue 'Health Check' request as per PTV API. This should be called every
   * time a sequence of calls are made to the API to ensure results are as
   * expected.
   *
   * @return Health status report of the PTV services.
   */
  public PtvHealth performHealthCheck() {
    final String uri = "/v2/healthcheck";
    final String securityKey = "securityTokenOK";
    final String clientKey = "clientClockOK";
    final String memcacheKey = "memcacheOK";
    final String databaseKey = "databaseOK";

    PtvHealth result = null;

    try {
      String apiCall = buildApiUrl(uri);
      String response = sendQueryToApi(apiCall);

      // Decode the JSON element
      JSONParser parser = new JSONParser();
      JSONObject healthObject = (JSONObject) parser.parse(response);

      result = new PtvHealth();
      result.populateFields(healthObject);

    } catch (Exception e) {
      Logger.getLogger(this.getClass().getSimpleName()).log(Level.SEVERE,
          "Error in building API query");
    }
    return result;

  }

  public PtvTimetable performBroadNextDestinationRequest(PtvRouteType mode, int stopId, int limit) {
    // Request URL = /v2/mode/%@/stop/%@/departures/by-destination/limit/%
    StringBuilder uri = new StringBuilder().append("/v2");
    // Add the transport mode as the index value
    uri.append("/mode/" + mode.getId());
    // Add the stop id
    uri.append("/stop/" + stopId);
    // Add the other part
    uri.append("/departures/by-destination");
    // Add the limit of services to show
    uri.append("/limt/" + limit);

    PtvTimetable result = null;
    try {
      JSONObject object = buildAndSendApiRequest(uri.toString());

      // Decode the object returned
      result = new PtvTimetable();
      result.populateFields(object);

    } catch (Exception e) {
      throw new RuntimeException();
    }
    return result;
  }

  /**
   * Perform a 'Search' request as per PTV API.
   *
   * @param searchTerm
   *          - Search term for the request
   * @return Search results
   *
   */
  public JSONArray performSearchRequest(String searchTerm) {
    // Request URL /v2/search/%@?&devid=%@&signature=%@
    StringBuilder uri = new StringBuilder().append("/v2");
    // Add search term
    uri.append("/search/" + searchTerm);
    try {
      String apiCall = buildApiUrl(uri.toString());
      System.out.println(apiCall);
      String response = sendQueryToApi(apiCall);

      JSONParser parser = new JSONParser();
      JSONArray result = (JSONArray) parser.parse(response);
      return result;
    } catch (Exception e) {
      Logger.getLogger(this.getClass().getSimpleName()).log(Level.SEVERE,
          "Error in building API query");
      return null;
    }
  }

  private JSONObject buildAndSendApiRequest(String uri) throws Exception {
    try {
      String apiCall = buildApiUrl(uri);
      System.out.println(apiCall);
      String response = sendQueryToApi(apiCall);

      JSONParser parser = new JSONParser();
      JSONObject result = (JSONObject) parser.parse(response);
      return result;
    } catch (Exception e) {
      Logger.getLogger(this.getClass().getSimpleName()).log(Level.SEVERE,
          "Error in building API query");
      throw new Exception(e.getMessage());
    }
  }

  /**
   * Helper method to perform the HTTP GET request and write JSON object to
   * String.
   *
   * @param apiCall
   *          - formed API request
   * @return JSON response
   */
  private String sendQueryToApi(String apiCall) {
    String line;
    StringBuilder jsonResponse = new StringBuilder();

    try {
      URL url = new URL(apiCall);
      BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

      while ((line = reader.readLine()) != null) {
        jsonResponse.append(line);
      }
    } catch (IOException e) {
      Logger.getLogger(this.getClass().getSimpleName()).log(Level.SEVERE,
          "Error obtaining API response");
    }

    return jsonResponse.toString();
  }

  /**
   * Method to demonstrate building of Timetable API URL.
   *
   * @param privateKey
   *          - Developer Key supplied by PTV (((Example :"9c132d31-6a30-4cac-
   *          8d8b-8a1970834799")
   * @param uri
   *          - Request URI with parameters(Example
   *          :/v2/mode/0/line/8/stop/1104/directionid/0/departures/all/limit/5?
   *          for_utc=2014-08-15T06:18:08Z)
   * @param developerId
   *          - Developer ID supplied by PTV
   * @return Complete URL with signature
   * @throws Exception
   *
   */
  private String buildApiUrl(final String uri) throws Exception {
    final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
    StringBuffer uriWithDeveloperId = new StringBuffer().append(uri)
        .append(uri.contains("?") ? "&" : "?").append("devid=" + developerId);
    byte[] keyBytes = privateKey.getBytes();
    byte[] uriBytes = uriWithDeveloperId.toString().getBytes();
    Key signingKey = new SecretKeySpec(keyBytes, HMAC_SHA1_ALGORITHM);
    Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
    mac.init(signingKey);
    byte[] signatureBytes = mac.doFinal(uriBytes);
    StringBuffer signature = new StringBuffer(signatureBytes.length * 2);
    for (byte signatureByte : signatureBytes) {
      int intVal = signatureByte & 0xff;
      if (intVal < 0x10) {
        signature.append("0");
      }
      signature.append(Integer.toHexString(intVal));
    }
    StringBuffer url = new StringBuffer(baseUrl).append(uri).append(uri.contains("?") ? "&" : "?")
        .append("devid=" + developerId).append("&signature=" + signature.toString().toUpperCase());
    return url.toString();
  }

}

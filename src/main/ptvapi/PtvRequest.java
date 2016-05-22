package main.ptvapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import main.ptvapi.ptvobjects.PtvHealth;
import main.ptvapi.ptvobjects.PtvRouteType;
import main.ptvapi.ptvobjects.PtvTimetableValues;

/**
 * Class to form required data for sending of PTV API request.
 *
 * @author dmaccora
 *
 */
public class PtvRequest {
  private final String baseUrl = "http://timetableapi.ptv.vic.gov.au";
  private final String privateKey;
  private final int    developerId;

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

    PtvHealth result = null;

    try {
      StringBuilder builder = new StringBuilder().append(uri);
      builder.append("?timestamp=");
      builder.append(getCurrentTimeIso8061Utc());
      JSONObject healthObject = buildAndSendApiRequest(builder.toString());

      result = new PtvHealth();
      result.populateFields(healthObject);

    } catch (Exception e) {
      Logger.getLogger(this.getClass().getSimpleName()).log(Level.SEVERE,
          "Error in building API query");
      e.printStackTrace();
    }
    return result;

  }

  private String getCurrentTimeIso8061Utc() {
    TimeZone tz = TimeZone.getTimeZone("UTC");
    DateFormat format = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss'Z'");
    format.setTimeZone(tz);
    String nowAsISO = format.format(new Date());
    return nowAsISO;
  }

  public PtvTimetableValues performBroadNextDestinationRequest(PtvRouteType mode, int stopId,
      int limit) {
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

    PtvTimetableValues result = null;
    try {
      JSONObject object = buildAndSendApiRequest(uri.toString());

      // Decode the object returned
      result = new PtvTimetableValues();
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
  /*
   * public JSONArray performSearchRequest(String searchTerm) { // Request URL
   * /v2/search/%@?&devid=%@&signature=%@ StringBuilder uri = new
   * StringBuilder().append("/v2"); // Add search term uri.append("/search/" +
   * searchTerm); try { String apiCall = buildApiUrl(uri.toString());
   * System.out.println(apiCall); String response = sendQueryToApi(apiCall);
   * JSONParser parser = new JSONParser(); JSONArray result = (JSONArray)
   * parser.parse(response); return result; } catch (Exception e) {
   * Logger.getLogger(this.getClass().getSimpleName()).log(Level.SEVERE,
   * "Error in building API query"); System.out.println(e.getMessage()); return
   * null; } }
   */

  private JSONObject buildAndSendApiRequest(String uri) throws Exception {
    try {
      String apiCall = generateCompleteURLWithSignature(privateKey, uri, developerId);
      Logger.getLogger(this.getClass().getSimpleName()).log(Level.INFO,
          "Creating query: " + apiCall);
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
        System.out.println(line);
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
   * @param uri
   *          - Request URI with parameters(Example
   *          :/v2/mode/0/line/8/stop/1104/directionid/0/departures/all/limit/5?
   *          for_utc=2014-08-15T06:18:08Z)
   * 
   * @return Complete URL with signature
   * @throws Exception
   *
   */
  private String buildApiUrlMine(final String uri) throws Exception {
    String HMAC_SHA1_ALGORITHM = "HmacSHA1";
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

  public String buildApiUrl(final String baseURL, final String privateKey, final String uri,
      final int developerId) throws Exception {
    String HMAC_SHA1_ALGORITHM = "HmacSHA1";
    StringBuffer uriWithDeveloperID = new StringBuffer().append(uri)
        .append(uri.contains("?") ? "&" : "?").append("devid=" + developerId);

    byte[] keyBytes = privateKey.getBytes();
    byte[] uriBytes = uriWithDeveloperID.toString().getBytes();
    SecretKeySpec signingKey = new SecretKeySpec(keyBytes, HMAC_SHA1_ALGORITHM);

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
    System.out.println(signature);

    StringBuffer url = new StringBuffer(baseURL).append(uri).append(uri.contains("?") ? "&" : "?")
        .append("devid=" + developerId).append("&signature=" + signature);
    return url.toString();
  }

  /*
   * Generates a signature using the HMAC-SHA1 algorithm
   * @param privateKey - Developer Key supplied by PTV
   * @param uri - request uri (Example :/v2/HealthCheck)
   * @param developerId - Developer ID supplied by PTV
   * @return Unique Signature Value
   */
  public String generateSignature(final String privateKey, final String uri,
      final int developerId) {
    String encoding = "UTF-8";
    String HMAC_SHA1_ALGORITHM = "HmacSHA1";
    String signature;
    StringBuffer uriWithDeveloperID = new StringBuffer();
    uriWithDeveloperID.append(uri).append(uri.contains("?") ? "&" : "?")
        .append("devid=" + developerId);
    try {
      byte[] keyBytes = privateKey.getBytes(encoding);
      byte[] uriBytes = uriWithDeveloperID.toString().getBytes(encoding);
      Key signingKey = new SecretKeySpec(keyBytes, HMAC_SHA1_ALGORITHM);
      Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
      mac.init(signingKey);
      byte[] signatureBytes = mac.doFinal(uriBytes);
      StringBuffer buf = new StringBuffer(signatureBytes.length * 2);
      for (byte signatureByte : signatureBytes) {
        int intVal = signatureByte & 0xff;
        if (intVal < 0x10) {
          buf.append("0");
        }
        buf.append(Integer.toHexString(intVal));
      }
      signature = buf.toString();
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    } catch (InvalidKeyException e) {
      throw new RuntimeException(e);
    }
    return signature.toString().toUpperCase();
  }

  /**
   * Generate full URL using generateSignature() method
   * 
   * @param privateKey
   *          - Developer Key supplied by PTV (Example :
   *          "92dknhh31-6a30-4cac-8d8b-8a1970834799");
   * @param uri
   *          - request uri (Example :"/v2/mode/2/line/787/stops-for-line)
   * @param developerId
   *          - Developer ID supplied by PTV( int developerId )
   * @return - Full URL with Signature
   */
  public String generateCompleteURLWithSignature(final String privateKey, final String uri,
      final int developerId) {

    String baseURL = "http://timetableapi.ptv.vic.gov.au";
    StringBuffer url = new StringBuffer(baseURL).append(uri).append(uri.contains("?") ? "&" : "?")
        .append("devid=" + developerId)
        .append("&signature=" + generateSignature(privateKey, uri, developerId));
    System.out.println("Calling new one");
    return url.toString();

  }

}

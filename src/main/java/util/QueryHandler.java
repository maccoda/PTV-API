package main.java.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author D. Maccora
 *
 */
public class QueryHandler {

  private final int devId;
  private final String privateKey;

  public QueryHandler(String privateKey, int devId) {
    this.privateKey = privateKey;
    this.devId = devId;
  }

  /**
   * Build the complete API query by appending the developer ID and signature for provided request.
   * 
   * @param uri
   *          - PTV request URI
   * @return Complete URI that is ready to be sent.
   */
  public String buildQuery(String uri) {
    String apiCall = generateCompleteURLWithSignature(privateKey, uri, devId);
    Logger.getLogger(this.getClass().getSimpleName()).log(Level.INFO, "Creating query: " + apiCall);
    return apiCall;
  }

  /**
   * Helper method to perform the HTTP GET request and write JSON object to String.
   *
   * @param apiCall
   *          - formed API request
   * @return JSON response
   */
  public String sendQuery(String apiCall) {
    String line;
    StringBuilder jsonResponse = new StringBuilder();
    Logger.getLogger(this.getClass().getSimpleName()).log(Level.INFO, "sendQuery::Sending query: " + apiCall);

    try {
      URL url = new URL(apiCall);
      BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

      while ((line = reader.readLine()) != null) {
        jsonResponse.append(line);
      }
    } catch (IOException e) {
      Logger.getLogger(this.getClass().getSimpleName()).log(Level.SEVERE,
          "sendQueryToApi::Error obtaining API response");
    }

    return jsonResponse.toString();
  }

  /**
   * Given a response from a valid PTV request, this will be parsed and returned as a JSON Object.
   * If the parse fails an empty JSON object will be returned.
   * 
   * @param queryResult
   *          - Result from API request.
   * @return Result constructed into a JSON object.
   */
  public JSONObject parseQueryResult(String queryResult) {
    JSONParser parser = new JSONParser();
    JSONObject result;
    try {
      result = (JSONObject) parser.parse(queryResult);
      return result;
    } catch (ParseException e) {
      Logger.getLogger(this.getClass().getSimpleName()).log(Level.WARNING,
          "buildAndSendApiRequest::Error parsing the response. Response received: " + queryResult);
      return result = new JSONObject();
    }
  }

  /**
   * Generate full URL using generateSignature() method.
   * 
   * @param privateKey
   *          - Developer Key supplied by PTV (Example : "92dknhh31-6a30-4cac-8d8b-8a1970834799");
   * @param uri
   *          - request uri (Example :"/v2/mode/2/line/787/stops-for-line)
   * @param developerId
   *          - Developer ID supplied by PTV( int developerId )
   * @return - Full URL with Signature
   */
  private String generateCompleteURLWithSignature(final String privateKey, final String uri, final int developerId) {

    String baseURL = "http://timetableapi.ptv.vic.gov.au";
    StringBuffer url = new StringBuffer(baseURL).append(uri).append(uri.contains("?") ? "&" : "?")
        .append("devid=" + developerId).append("&signature=" + generateSignature(privateKey, uri, developerId));
    return url.toString();

  }

  /**
   * Generates a signature using the HMAC-SHA1 algorithm.
   * 
   * @param privateKey
   *          - Developer Key supplied by PTV
   * 
   * @param uri
   *          - request uri (Example :/v2/HealthCheck)
   * 
   * @param developerId
   *          - Developer ID supplied by PTV
   * 
   * @return Unique Signature Value
   */
  private String generateSignature(final String privateKey, final String uri, final int developerId) {
    String encoding = "UTF-8";
    String HMAC_SHA1_ALGORITHM = "HmacSHA1";
    String signature;
    StringBuffer uriWithDeveloperID = new StringBuffer();
    uriWithDeveloperID.append(uri).append(uri.contains("?") ? "&" : "?").append("devid=" + developerId);
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

}

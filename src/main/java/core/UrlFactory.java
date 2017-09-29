package core;

import ptvobjects.PtvRouteType;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Logger;

/**
 * Not quite a factory for creating all of the URLs for the API requests.
 *
 * @author D. Maccora
 */
class UrlFactory {
    /** Logger for loggy logs */
    private final Logger logger = Logger.getLogger(UrlFactory.class.getSimpleName());

    enum ApiVersion {
        /** Version 2 */
        V2("v2"),
        /** Version 3 */
        V3("v3");

        /** Representation in the uri */
        private final String uri;

        /**
         * Constructor.
         *
         * @param aUri
         *         uri
         */
        ApiVersion(final String aUri) {
            this.uri = aUri;
        }

        /** @return version as URI */
        String toUri() {
            return uri;
        }
    }

    /** Version of API to use */
    private final ApiVersion version;
    /** Private key of the developer */
    private final String privateKey;
    /** Developer ID */
    private final int developerId;

    /**
     * Constructor
     *
     * @param aVersion
     *         - version of API to use
     * @param aPrivateKey
     *         - private key of developer from PTV
     * @param aDeveloperId
     *         - developer ID from PTV
     */
    UrlFactory(final ApiVersion aVersion, final String aPrivateKey, final int aDeveloperId) {
        this.version = aVersion;
        this.privateKey = aPrivateKey;
        this.developerId = aDeveloperId;
    }

    /**
     * Builds health check URL.
     *
     * @return API URL
     */
    String healthCheck() {
        String uri = "/healthcheck";
        uri += "?timestamp=";
        uri += getCurrentTimeIso8061Utc();

        return generateCompleteURLWithSignature(uri);
    }

    /**
     * Builds <i>broad next departure</i> URL.
     *
     * @param aMode
     *         - mode  of transport
     * @param aStopId
     *         - stop ID
     * @param aLimit
     *         - limit of values to return
     * @return API URL
     */
    String broadNextDeparture(final PtvRouteType aMode, final int aStopId, final int aLimit) {
        // Request URL = /v2/mode/%@/stop/%@/departures/by-destination/limit/%
        // Add the transport mode as the index value
        String uri = "/mode/" + aMode.getId();
        // Add the stop id
        uri += "/stop/" + aStopId;
        // Add the other part
        uri += "/departures/by-destination";
        // Add the limit of services to show
        uri += "/limit/" + aLimit;

        return generateCompleteURLWithSignature(uri);
    }

    /**
     * Builds <i>stops nearby</i> URL
     *
     * @param latitude
     * @param longitude
     * @return API URL
     */
    String stopsNearby(final double latitude, final double longitude) {
        String uri = "/nearme";
        uri += "/latitude/" + latitude;
        uri += "/longitude/" + longitude;

        return generateCompleteURLWithSignature(uri);
    }

    String linesByMode(final PtvRouteType mode, final String name) {
        // Request URL = /v2/lines/mode/%@?name=%@
        String uri = "/lines/mode/" + mode.getId() + "?";
        uri += "name=" + name.trim();

        return generateCompleteURLWithSignature(uri);
    }

    String search(final String searchString) {
        // Request URL = /v2/search/%@?
        final String uri = "/search/" + searchString + "?";

        return generateCompleteURLWithSignature(uri);
    }

    String stopsOnALine(final PtvRouteType mode, final int lineId) {
        // Request URL = /v2/mode/%@/line/%@/stops-for-line?
        String uri = ("/mode/" + mode.getId());
        uri += ("/line/" + lineId);
        uri += ("/stops-for-line");

        return generateCompleteURLWithSignature(uri);
    }

    /**
     * Method to format the current UTC time into the expected format for API which is ISO-8061.
     *
     * @return Current time in ISO-8061 format.
     */
    private String getCurrentTimeIso8061Utc() {
        final TimeZone tz = TimeZone.getTimeZone("UTC");
        final DateFormat format = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss'Z'");
        format.setTimeZone(tz);
        return format.format(new Date());
    }

    /**
     * Generate full URL using generateSignature() method.
     *
     * @param uri
     *         - request uri (Example :"/v2/mode/2/line/787/stops-for-line)
     * @return - Full URL with Signature
     */
    private String generateCompleteURLWithSignature(final String uri) {
        final String versionedUri = "/" + version.toUri() + uri;
        final String baseURL = "http://timetableapi.ptv.vic.gov.au";
        final StringBuffer url = new StringBuffer(baseURL).append(versionedUri).append(versionedUri.contains("?") ? "&" : "?")
                .append("devid=" + developerId).append("&signature=" + generateSignature(versionedUri));
        logger.info("Building query " + url.toString());
        return url.toString();

    }

    /**
     * Generates a signature using the HMAC-SHA1 algorithm.
     *
     * @param uri
     *         - request uri (Example :/v2/HealthCheck)
     * @return Unique Signature Value
     */
    private String generateSignature(final String uri) {
        final String encoding = "UTF-8";
        final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
        final String signature;
        final StringBuffer uriWithDeveloperID = new StringBuffer();
        uriWithDeveloperID.append(uri).append(uri.contains("?") ? "&" : "?").append("devid=" + developerId);
        try {
            final byte[] keyBytes = privateKey.getBytes(encoding);
            final byte[] uriBytes = uriWithDeveloperID.toString().getBytes(encoding);
            final Key signingKey = new SecretKeySpec(keyBytes, HMAC_SHA1_ALGORITHM);
            final Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(signingKey);
            final byte[] signatureBytes = mac.doFinal(uriBytes);
            final StringBuffer buf = new StringBuffer(signatureBytes.length * 2);
            for (final byte signatureByte : signatureBytes) {
                final int intVal = signatureByte & 0xff;
                if (intVal < 0x10) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(intVal));
            }
            signature = buf.toString();
        } catch (final UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (final NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (final InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        return signature.toString().toUpperCase();
    }
}

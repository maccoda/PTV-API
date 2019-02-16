package core;

import auth.Authentication;
import core.url.RequestUrlBuilder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

/**
 * Not quite a factory for creating all of the URLs for the API requests.
 *
 * @author D. Maccora
 */
public class UrlSignatureDecorator {
    /** Logger for loggy logs */
    private final Logger logger = Logger.getLogger(UrlSignatureDecorator.class.getSimpleName());

    public enum ApiVersion {
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
            uri = aUri;
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
    public UrlSignatureDecorator(final ApiVersion aVersion, final String aPrivateKey, final int aDeveloperId) {
        version = aVersion;
        privateKey = aPrivateKey;
        developerId = aDeveloperId;
    }

    public UrlSignatureDecorator(final ApiVersion aVersion, final Authentication auth) {
        version = aVersion;
        privateKey = auth.getPrivateKey().asString();
        developerId = auth.getDeveloperId().asInteger();
    }

    /**
     * Build the full URL with the provided {@link RequestUrlBuilder}
     *
     * @param urlBuilder
     *         - request builder
     * @return as above
     */
    String buildUrl(final RequestUrlBuilder urlBuilder) {
        return generateCompleteURLWithSignature(urlBuilder.buildUrl());
    }

    /**
     * Generate full URL using generateSignature() method.
     *
     * @param uri
     *         - request uri (Example :"/v2/mode/2/line/787/stops-for-line)
     * @return - Full URL with Signature
     */
    String generateCompleteURLWithSignature(final String uri) {
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

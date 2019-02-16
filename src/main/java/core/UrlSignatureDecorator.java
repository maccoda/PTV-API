package core;

import auth.Authentication;
import core.url.v3.UrlPathBuilder;

import java.util.logging.Logger;

public class UrlSignatureDecorator {
    private final Logger logger = Logger.getLogger(UrlSignatureDecorator.class.getSimpleName());

    private final String privateKey;
    private final int developerId;


    public UrlSignatureDecorator(final Authentication auth) {
        privateKey = auth.getPrivateKey().asString();
        developerId = auth.getDeveloperId().asInteger();
    }

    /**
     * Generate full URL using generateSignature() method.
     *
     * @param uri
     *         - request uri (Example :"/v2/mode/2/line/787/stops-for-line)
     * @return - Full URL with Signature
     */
    String generateCompleteURLWithSignature(final String uri) {
        final String baseURL = "http://timetableapi.ptv.vic.gov.au";
        final String pathSegments = appendDevIdAndSignature(uri);

        return baseURL + pathSegments;
    }

    private String appendDevIdAndSignature(final String uri) {
        final UrlPathBuilder builder = new UrlPathBuilder();
        builder.appendPathSegment(uri);
        builder.appendQueryParam("devid", Integer.toString(developerId));
        final String withDevId = builder.build();
        builder.appendQueryParam("signature", new SignatureGenerator().generateSignatureFromInput(withDevId, privateKey));
        return builder.build();
    }


}

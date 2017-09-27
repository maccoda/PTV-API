package core;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Logger;


/**
 * @author D. Maccora
 */
public final class QueryHandler {
    /** Logger */
    private static final Logger LOGGER = Logger.getLogger(QueryHandler.class.getSimpleName());

    /**
     * Empty constructor
     */
    private QueryHandler() {
        // empty
    }

    /**
     * Helper method to perform the HTTP GET request and write JSON object to String.
     *
     * @param apiCall
     *         - formed API request
     * @return JSON response
     */
    public static String sendQuery(final String apiCall) {
        String line;
        final StringBuilder jsonResponse = new StringBuilder();
        LOGGER.info("sendQuery::Sending query: " + apiCall);

        try {
            final URL url = new URL(apiCall);
            final BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            while ((line = reader.readLine()) != null) {
                jsonResponse.append(line);
            }
        } catch (final IOException e) {
            LOGGER.severe("sendQueryToApi::Error obtaining API response");
        }

        return jsonResponse.toString();
    }

    /**
     * Given a response from a valid PTV request, this will be parsed and returned as a JSON Object.
     * If the parse fails an empty JSON object will be returned.
     *
     * @param queryResult
     *         - Result from API request.
     * @param clazz
     *         - Data type to convert to
     * @return Result constructed into a JSON object.
     */
    public static <T> T parseQueryResult(final String queryResult, final Class<T> clazz) {
        // TODO look into container factories for the parsing.
        final Gson gson = new Gson();
        return gson.fromJson(queryResult, clazz);
    }


}

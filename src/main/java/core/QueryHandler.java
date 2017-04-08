package core;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author D. Maccora
 */
public class QueryHandler {

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
        Logger.getLogger(QueryHandler.class.getSimpleName()).log(Level.INFO, "sendQuery::Sending query: " + apiCall);

        try {
            final URL url = new URL(apiCall);
            final BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            while ((line = reader.readLine()) != null) {
                jsonResponse.append(line);
            }
        } catch (final IOException e) {
            Logger.getLogger(QueryHandler.class.getSimpleName()).log(Level.SEVERE,
                    "sendQueryToApi::Error obtaining API response");
        }

        return jsonResponse.toString();
    }

    /**
     * Given a response from a valid PTV request, this will be parsed and returned as a JSON Object.
     * If the parse fails an empty JSON object will be returned.
     *
     * @param queryResult
     *         - Result from API request.
     * @return Result constructed into a JSON object.
     */
    public static <T> T parseQueryResult(final String queryResult) {
        // TODO look into container factories for the parsing.
        final JSONParser parser = new JSONParser();
        final T result;
        try {
            result = (T) parser.parse(queryResult);
            return result;
        } catch (final ParseException e) {
            Logger.getLogger(QueryHandler.class.getClass().getSimpleName()).log(Level.WARNING,
                    "buildAndSendApiRequest::Error parsing the response. Response received: " + queryResult);
            return null;
        }
    }


}

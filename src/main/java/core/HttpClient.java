package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Logger;

class HttpClient {
    private static final Logger LOGGER = Logger.getLogger(HttpClient.class.getSimpleName());

    String send(final String apiCall) {
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

}

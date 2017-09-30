package core.url;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Builds Health Request URL
 */
public final class HealthRequestUrlBuilder implements RequestUrlBuilder {
    @Override
    public String buildUrl() {
        String uri = "/healthcheck";
        uri += "?timestamp=";
        uri += getCurrentTimeIso8061Utc();

        return uri;
    }

    /**
     * Method to format the current UTC time into the expected format for API which is ISO-8061.
     *
     * @return Current time in ISO-8061 format.
     */
    static String getCurrentTimeIso8061Utc() {
        final TimeZone tz = TimeZone.getTimeZone("UTC");
        final DateFormat format = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss'Z'");
        format.setTimeZone(tz);
        return format.format(new Date());
    }
}

package ptvobjects;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class PtvTimetable implements PtvBasicObject {

    private PtvPlatform platform;
    private PtvRun run;
    private String flags;
    private String time_timetable_utc;
    private String time_realtime_utc;
    private Calendar timetableUtc;
    private Calendar realtimeUtc;
    private List<PtvDisruptionInformation> disruptions;


    /**
     * Constructor. Initializes the time fields for use with GSON.
     */
    public PtvTimetable() {
        timetableUtc = null;
        realtimeUtc = null;
    }

    /**
     * @return PtvPlatform Object
     */
    public PtvPlatform getPlatform() {
        return platform;
    }

    /**
     * @return PtvRun Object
     */
    public PtvRun getRun() {
        return run;
    }

    /**
     * a stop may have zero or more flags associated with it, delimited by a “-” character; examples include: <ul>
     * <li>RR = Reservations Required</li> <li> GC = Guaranteed Connection</li> <li>DOO = Drop Off Only</li> <li>PUO =
     * Pick Up Only</li> <li>MO = Mondays only</li> <li>TU = Tuesdays only</li> <li>WE = Wednesdays only</li> <li>TH =
     * Thursdays only</li> <li>FR = Fridays only</li> <li>SS = School days only</li> </ul> note: ignore “E” flag returns
     * empty if no flags apply
     *
     * @return flags associated with the stop
     */
    public String getFlags() {
        return flags;
    }

    /**
     * Date and time expressed in ISO 8601 UTC format
     *
     * @return the scheduled time of the service at the stop.
     */
    public Calendar getTimeTimetableUtc() {
        if (timetableUtc == null) {
            timetableUtc = parseCalendarTime(time_timetable_utc);
        }
        return timetableUtc;
    }

    /**
     * A place holder for the real-time of the service at the stop if this is available. The API receives data from
     * multiple feeds covering tram and bus services; if the relevant feed system is not available, it will return null.
     * Date and time expressed in ISO 8601 UTC format
     *
     * @return real-time of the service
     */
    public Calendar getRealtimeUtc() {
        if (realtimeUtc == null) {
            realtimeUtc = parseCalendarTime(time_realtime_utc);
        }
        return realtimeUtc;
    }

    public List<PtvDisruptionInformation> getDisruptions() {
        return disruptions;
    }

    private Calendar parseCalendarTime(final String input) {
        final int year = Integer.parseInt(input.substring(0, 4));
        final int month = Integer.parseInt(input.substring(5, 7));
        final int day = Integer.parseInt(input.substring(8, 10));

        final int hour = Integer.parseInt(input.substring(11, 13));
        final int minutes = Integer.parseInt(input.substring(14, 16));
        final int seconds = Integer.parseInt(input.substring(17, 19));

        return new GregorianCalendar(year, month, day, hour, minutes, seconds);
    }

}

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


    public PtvTimetable() {
        timetableUtc = null;
        realtimeUtc = null;
    }

    public PtvPlatform getPlatform() {
        return platform;
    }

    public PtvRun getRun() {
        return run;
    }

    public String getFlags() {
        return flags;
    }

    public Calendar getTimeTimetableUtc() {
        if (timetableUtc == null) {
            timetableUtc = parseCalendarTime(time_timetable_utc);
        }
        return timetableUtc;
    }

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

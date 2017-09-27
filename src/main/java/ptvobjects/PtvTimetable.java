package ptvobjects;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class PtvTimetable implements PtvObject {

    private PtvPlatform platform;
    private PtvRun run;
    private String flags;
    private Calendar timetableUtc;
    private Calendar realtimeUtc;
    private PtvDisruptionInformation[] disruptions;


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
        return timetableUtc;
    }

    public Calendar getRealtimeUtc() {
        return realtimeUtc;
    }

    public PtvDisruptionInformation[] getDisruptions() {
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

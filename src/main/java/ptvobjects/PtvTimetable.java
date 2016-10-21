package ptvobjects;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.json.simple.JSONObject;

import util.JSONHelper;

public class PtvTimetable implements PtvObject {

  private PtvPlatform platform;
  private PtvRun run;
  private String flags;
  private String timeTimetableUtc;
  private String timeRealtimeUtc;
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

  @Override
  public void populateFields(JSONObject object) {
    platform = new PtvPlatform();
    platform.populateFields((JSONObject) object.get("platform"));
    run = new PtvRun();
    run.populateFields((JSONObject) object.get("run"));
    flags = JSONHelper.parseStringValue(object, "flags");

    timeTimetableUtc = JSONHelper.parseStringValue(object, "time_timetable_utc");
    // This can be null by API standards
    try {
      timeRealtimeUtc = JSONHelper.parseStringValue(object, "time_realtime_utc");
    } catch (NullPointerException e) {
      timeRealtimeUtc = null;
    }
    timetableUtc = parseCalendarTime(timeTimetableUtc);
    if (timeRealtimeUtc != null) {
      realtimeUtc = parseCalendarTime(timeRealtimeUtc);
    } else {
      realtimeUtc = null;
    }

    // TODO work out what to do with the disruptions
  }

  private Calendar parseCalendarTime(String input) {
    int year = Integer.parseInt(input.substring(0, 4));
    int month = Integer.parseInt(input.substring(5, 7));
    int day = Integer.parseInt(input.substring(8, 10));

    int hour = Integer.parseInt(input.substring(11, 13));
    int minutes = Integer.parseInt(input.substring(14, 16));
    int seconds = Integer.parseInt(input.substring(17, 19));

    return new GregorianCalendar(year, month, day, hour, minutes, seconds);
  }

}

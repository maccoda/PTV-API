package main.ptvapi.ptvobjects;

import org.json.simple.JSONObject;

import main.ptvapi.JSONHelper;

public class PtvTimetable implements PtvObject {
  private PtvPlatform                platform;
  private PtvRun                     run;
  private String                     flags;
  private String                     timeTimetableUtc;
  private String                     timeRealtimeUtc;
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

  public String getTimeTimetableUtc() {
    return timeTimetableUtc;
  }

  public String getTimeRealtimeUtc() {
    return timeRealtimeUtc;
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
    // TODO I think this would be better if it were parsed to a Date object for
    // easier use
    timeTimetableUtc = JSONHelper.parseStringValue(object, "time_timetable_utc");
    // IDEA Substrings to find the time
    // TODO Check if substring is inclusive of the last index herte i have
    // assumed so
    int year = Integer.parseInt(timeTimetableUtc.substring(0, 4));
    System.out.println(year);
    int month = Integer.parseInt(timeTimetableUtc.substring(5, 7));
    System.out.println(month);
    int day = Integer.parseInt(timeTimetableUtc.substring(8, 10));
    System.out.println(day);

    int hour = Integer.parseInt(timeTimetableUtc.substring(11, 13));
    System.out.println(hour);
    int minutes = Integer.parseInt(timeTimetableUtc.substring(14, 16));
    System.out.println(minutes);
    int seconds = Integer.parseInt(timeTimetableUtc.substring(17, 19));
    System.out.println(seconds);

    timeRealtimeUtc = JSONHelper.parseStringValue(object, "time_realtime_utc");
    // TODO work out what to do with the distruptions
  }

}

package main.ptvobjects;

import org.json.simple.JSONObject;

import main.JSONHelper;

public class PtvTimetable implements PtvObject {
  private PtvPlatform platform;
  private PtvRun run;
  private String flags;
  private String timeTimetableUtc;
  private String timeRealtimeUtc;
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
    // TODO I think this would be better if it were parsed to a Date object for easier use
    timeTimetableUtc = JSONHelper.parseStringValue(object, "time_timetable_utc");
    // IDEA Substrings to find the time
    // TODO Check if substring is inclusive of the last index herte i have assumed so
    int year = Integer.parseString(timeTimetableUtc.substring(0,3));
    int month = Integer.parseString(timeTimetableUtc.substring(5,6));
    int day = Integer.parseString(timeTimetableUtc.substring(7,8));

    int hour = Integer.parseString(timeTimetableUtc.substring(10,11));
    int minutes = Integer.parseString(timeTimetableUtc.substring(13,14));
    int seconds = Integer.parseString(timeTimetableUtc.substring(16,17));






    timeRealtimeUtc = JSONHelper.parseStringValue(object, "time_realtime_utc");
    // TODO work out what to do with the distruptions

  }

}

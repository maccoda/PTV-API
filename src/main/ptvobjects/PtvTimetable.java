package main.ptvobjects;

import org.json.simple.JSONObject;

import main.JSONHelper;

public class PtvTimetable extends PtvObject {
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
    timeTimetableUtc = JSONHelper.parseStringValue(object, "time_table_utc");
    timeRealtimeUtc = JSONHelper.parseStringValue(object, "time_realtime_utc");
    // TODO work out what to do with the distruptions

  }

}

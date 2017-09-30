package core.url;

import ptvobjects.PtvRouteType;

public final class StoppingPatternUrlBuilder implements RequestUrlBuilder {

    private final PtvRouteType mode;
    private final int runId;
    private final int stopId;
    private final String forUtc;

    public StoppingPatternUrlBuilder(final PtvRouteType mode, final int runId, final int stopId, final String forUtc) {
        this.mode = mode;
        this.runId = runId;
        this.stopId = stopId;
        this.forUtc = forUtc;
    }

    @Override
    public String buildUrl() {
        String uri = "/mode/" + mode.getId();
        uri += "/run/" + runId;
        uri += "/stop/" + stopId;
        uri += "/stopping-pattern";
        if (forUtc != "") {
            uri += "?for_utc=" + forUtc;
        }
        return uri;
    }
}

package core.url;

import ptvobjects.PtvRouteType;

public final class LinesByModeUrlBuilder implements RequestUrlBuilder {
    private final PtvRouteType mode;
    private final String name;

    /**
     * Constructor.
     *
     * @param mode
     *         - mode to request
     * @param name
     *         - name of line
     */
    public LinesByModeUrlBuilder(final PtvRouteType mode, final String name) {
        this.mode = mode;
        this.name = name;
    }

    @Override
    public String buildUrl() {
        // Request URL = /v2/lines/mode/%@?name=%@
        String uri = "/lines/mode/" + mode.getId() + "?";
        uri += "name=" + name.trim();

        return uri;
    }
}

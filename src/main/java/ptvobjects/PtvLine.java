package ptvobjects;


public class PtvLine implements PtvObject {

    private PtvRouteType routeType;
    private int lineId;
    private String lineName;
    private String lineNumber;
    private String lineNameShort;
    private String lineNumberLong;


    public PtvRouteType getRouteType() {
        return routeType;
    }

    public int getLineId() {
        return lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public String getLineNameShort() {
        return lineNameShort;
    }

    public String getLineNumberLong() {
        return lineNumberLong;
    }


}

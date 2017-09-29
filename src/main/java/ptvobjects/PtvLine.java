package ptvobjects;


public class PtvLine implements PtvBasicObject {

    private int route_type;
    private int line_id;
    private String line_name;
    private String line_number;
    private String line_name_short;
    private String line_number_long;


    public PtvRouteType getRouteType() {
        return PtvRouteType.values()[route_type];
    }

    public int getLineId() {
        return line_id;
    }

    public String getLineName() {
        return line_name;
    }

    public String getLineNumber() {
        return line_number;
    }

    public String getLineNameShort() {
        return line_name_short;
    }

    public String getLineNumberLong() {
        return line_number_long;
    }


}

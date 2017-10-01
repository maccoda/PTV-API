package ptvobjects;


public class PtvLine implements PtvBasicObject {

    private int route_type;
    private int line_id;
    private String line_name;
    private String line_number;
    private String line_name_short;
    private String line_number_long;


    /**
     * @return mode of transport serviced by the
     */
    public PtvRouteType getRouteType() {
        return PtvRouteType.values()[route_type];
    }

    /**
     * @return the unique identifier of each line
     */
    public int getLineId() {
        return line_id;
    }

    /**
     * @return the complete name of the line (i.e. includes the line number(s))
     */
    public String getLineName() {
        return line_name;
    }

    /**
     * @return the main line number that is presented to the public (i.e. not the “line_id”)
     */
    public String getLineNumber() {
        return line_number;
    }

    /**
     * @return the name of the line (i.e. doesn’t include the line_number)
     */
    public String getLineNameShort() {
        return line_name_short;
    }

    /**
     * @return the complete line number, i.e. includes numbers of all paths
     */
    public String getLineNumberLong() {
        return line_number_long;
    }


}

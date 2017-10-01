package ptvobjects;

/**
 * PTV Direction Object.
 */
public class PtvDirection implements PtvBasicObject {

    private int linedir_id;
    private int direction_id;
    private String direction_name;
    private PtvLine line;


    /**
     * @return unique identifier of a particular line and direction
     */
    public int getLineDirId() {
        return linedir_id;
    }

    /**
     * @return unique identifier of a direction (e.g. “0” signifies “city”)
     */
    public int getDirectionId() {
        return direction_id;
    }

    /**
     * @return name of the direction of the service
     */
    public String getDirectionName() {
        return direction_name;
    }

    /**
     * @return PtvLine Object
     */
    public PtvLine getLine() {
        return line;
    }

}

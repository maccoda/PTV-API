package ptvobjects;


public class PtvDirection implements PtvObject {

    private int linedir_id;
    private int direction_id;
    private String direction_name;
    private PtvLine line;


    public int getLineDirId() {
        return linedir_id;
    }

    public int getDirectionId() {
        return direction_id;
    }

    public String getDirectionName() {
        return direction_name;
    }

    public PtvLine getLine() {
        return line;
    }

}

package ptvobjects;


public class PtvDirection implements PtvObject {

    private int lineDirId;
    private int directionId;
    private String directionName;
    private PtvLine line;


    public int getLineDirId() {
        return lineDirId;
    }

    public int getDirectionId() {
        return directionId;
    }

    public String getDirectionName() {
        return directionName;
    }

    public PtvLine getLine() {
        return line;
    }

}

package ptvobjects;

import org.json.simple.JSONObject;
import util.JSONHelper;

public class PtvResult {

    /**
     * The types of results that can be returned. This dictates what type of object needs to be created.
     */
    enum ResultType {
        STOP, LINE,
    }

    PtvObject obj;
    ResultType result;

    public PtvResult(JSONObject object) {
        result = ResultType.valueOf(JSONHelper.parseStringValue(object, "type").toUpperCase());
        // TODO Need to build the type we need
        // Cannot be bothered looking into reflection just going to do a case
        switch (result) {
            case STOP:
                obj = new PtvStop((JSONObject) object.get("result"));
                break;
            case LINE:
                obj = new PtvLine((JSONObject) object.get("result"));
                break;
            default:
                throw new RuntimeException("Unknown reuslt type");
        }
    }

    public String getType() {
        return result.name().toLowerCase();
    }

    public PtvObject getObject() {
        return obj;
    }

}
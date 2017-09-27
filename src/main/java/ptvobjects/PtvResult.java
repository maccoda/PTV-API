package ptvobjects;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class PtvResult {

    /**
     * The types of results that can be returned. This dictates what type of object needs to be created.
     */
    enum ResultType {
        STOP, LINE,
    }

    PtvObject result;
    ResultType type;

    public PtvResult(final JsonObject object) {
        type = ResultType.valueOf(object.get("type").getAsString().toUpperCase());
        switch (type) {
            case LINE:
                result = new Gson().fromJson(object.get("result"), PtvLine.class);
                break;
            case STOP:
                result = new Gson().fromJson(object.get("result"), PtvStop.class);
                break;
            default:
                throw new IllegalArgumentException("Unexpected type");
        }
    }

    public String getType() {
        return type.name().toLowerCase();
    }

    public PtvStop getObjectAsStop() throws IllegalAccessException {
        if (type == ResultType.STOP) {
            return (PtvStop) result;
        } else {
            throw new IllegalAccessException("Not correct type");
        }
    }

    public PtvLine getObjectAsLine() throws IllegalAccessException {
        if (type == ResultType.LINE) {
            return (PtvLine) result;
        } else {
            throw new IllegalAccessException("Not correct type");
        }
    }

}
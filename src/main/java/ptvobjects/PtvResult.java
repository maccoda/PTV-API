package ptvobjects;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Result data type which can be obtained from several different queries. It is represented as a union type which
 * can have either a {@link PtvStop} or {@link PtvLine} payload.
 */
public class PtvResult implements PtvUnionObject {

    /**
     * The types of results that can be returned. This dictates what type of object needs to be created.
     */
    enum ResultType {
        /** Data type is a PtvStop object */
        STOP,
        /** Data type is a PtvLine object */
        LINE,
    }

    PtvBasicObject result;
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
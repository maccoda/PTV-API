package ptvobjects;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Result data type which can be obtained from several different queries. It is represented as a union type which can
 * have either a {@link PtvStop} or {@link PtvLine} payload.
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

    /**
     * Constructor. Builds the union type from the provided JSON object. It assumes the object has keys <b>type</b> and
     * <b>result</b>
     *
     * @param object
     *         - JSON Object received
     */
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

    /**
     * @return type descriminator of the result union type
     */
    public String getType() {
        return type.name().toLowerCase();
    }

    /**
     * @return result object as a {@link PtvStop}
     *
     * @throws IllegalAccessException
     *         when type is not STOP
     */
    public PtvStop getObjectAsStop() throws IllegalAccessException {
        if (type == ResultType.STOP) {
            return (PtvStop) result;
        } else {
            throw new IllegalAccessException("Not correct type");
        }
    }

    /**
     * @return result object as a {@link PtvLine}
     *
     * @throws IllegalAccessException
     *         when type is not LINE
     */
    public PtvLine getObjectAsLine() throws IllegalAccessException {
        if (type == ResultType.LINE) {
            return (PtvLine) result;
        } else {
            throw new IllegalAccessException("Not correct type");
        }
    }

}
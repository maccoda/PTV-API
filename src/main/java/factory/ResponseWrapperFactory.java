package factory;

import deserialize.ResponseDeserializerWrapper;

public class ResponseWrapperFactory {
    public static ResponseDeserializerWrapper responseDeserializerWrapper() {
        return new ResponseDeserializerWrapper(GsonFactory.gson());
    }
}

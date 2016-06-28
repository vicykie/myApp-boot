package org.vicykie.myapp.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.vicykie.myapp.enums.ResponseStatus;

import java.io.IOException;

/**
 * Created by vicykie on 2016/6/7.
 * 反序列化
 */
public class ResponseStatusDeserializer extends JsonDeserializer<ResponseStatus> {


    @Override
    public ResponseStatus deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {
        String value = p.getValueAsString();
        ResponseStatus responseStatus = ResponseStatus.getResponseStatusByCode(value, ResponseStatus.UNKOWN);
        return responseStatus;
    }
}

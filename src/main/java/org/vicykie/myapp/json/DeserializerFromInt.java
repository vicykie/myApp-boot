package org.vicykie.myapp.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * Created by vicykie on 2016/6/13.
 */
public class DeserializerFromInt<T extends Enum> extends JsonDeserializer<T> {


    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        int index = p.getIntValue();
//        Enum<?> result =
        return null;
    }
}

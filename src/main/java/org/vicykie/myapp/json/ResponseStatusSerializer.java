package org.vicykie.myapp.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.vicykie.myapp.enums.ResponseStatus;

import java.io.IOException;

/**
 * Created by vicykie on 2016/6/7.
 * 序列化
 */
public class ResponseStatusSerializer extends JsonSerializer<ResponseStatus> {
    @Override
    public void serialize(ResponseStatus value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(gen, value.getCode());
    }
}

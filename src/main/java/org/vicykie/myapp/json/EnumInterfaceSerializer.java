package org.vicykie.myapp.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.vicykie.myapp.enums.EnumInterface;

import java.io.IOException;

/**
 * Created by 李朝衡 on 2016/7/27.
 */
public class EnumInterfaceSerializer extends JsonSerializer<EnumInterface> {
    @Override
    public void serialize(EnumInterface enumInterface, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(jsonGenerator, enumInterface.getCode());
    }
}

package de.intranda.api.serializer;

import java.io.IOException;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonObjectSerializer extends JsonSerializer<JSONObject> {

    @Override
    public void serialize(JSONObject jsonObject, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeRawValue(jsonObject.toString());
    }
}

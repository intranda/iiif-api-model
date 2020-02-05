package de.intranda.api.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import de.intranda.api.iiif.presentation.IPresentationModelElement;

public class ActivityObjectSerializer extends JsonSerializer<IPresentationModelElement> {

    @Override
    public void serialize(IPresentationModelElement value, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeStartObject();
        generator.writeStringField("id", value.getId().toString());
        generator.writeStringField("type", value.getType());
        generator.writeEndObject();
    }

}

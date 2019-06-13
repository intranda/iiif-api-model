package de.intranda.api.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import de.intranda.api.iiif.presentation.ICanvas;
import de.intranda.api.iiif.presentation.PartOfCanvas;

public class AnnotationTargetSerializer extends JsonSerializer<ICanvas> {

    @Override
    public void serialize(ICanvas target, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if(target instanceof PartOfCanvas) {
            PartOfCanvas part = (PartOfCanvas)target;
            gen.writeStartObject();
            gen.writeStringField("@type", "oa:SpecificResource");
            gen.writeStringField("full", part.getCanvas().getId().toString());
            gen.writeObjectFieldStart("selector");
            gen.writeStringField("@type", "oa:FragmentSelector");
            gen.writeStringField("value", part.getFragment());
            gen.writeEndObject();
            gen.writeEndObject();
        } else{
            gen.writeString(target.getId().toString());
        }
        
    }

}

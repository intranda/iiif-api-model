package de.intranda.api.deserializer;

import java.io.IOException;
import java.net.URI;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import de.intranda.api.annotation.IAnnotation;
import de.intranda.api.annotation.oa.OpenAnnotation;
import de.intranda.api.annotation.wa.WebAnnotation;

public class AnnotationDeserializer extends StdDeserializer<IAnnotation> {
    

    private static final long serialVersionUID = -8048305385834181297L;
    private ObjectMapper mapper = new ObjectMapper();
    
    public AnnotationDeserializer() {
        this(null);
    }

    public AnnotationDeserializer(Class<IAnnotation> vc) {
        super(vc);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
    }

    @Override
    public IAnnotation deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        String type = "unknown";
        if(node.has("@type")) {
            type = node.get("@type").asText();
        } else if(node.has("type")){
            type = node.get("type").asText();
        }
        switch(type) {
            case OpenAnnotation.TYPE:
                return mapper.convertValue(node, OpenAnnotation.class);
            case WebAnnotation.TYPE:
                return mapper.convertValue(node, WebAnnotation.class);
            default:
                String id = "unknown";
                if(node.has("@id")) {
                    id = node.get("@id").asText();
                } else if(node.has("id"))  {
                    id = node.get("id").asText();
                }
                return new WebAnnotation(URI.create(id));
        }
    }

}

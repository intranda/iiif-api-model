package de.intranda.api.deserializer;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

public class AnnotationListResourceDeserializer extends StdDeserializer<List<IAnnotation>> {
    private static final long serialVersionUID = 7191457467443751957L;
    private ObjectMapper mapper = new ObjectMapper();

    public AnnotationListResourceDeserializer() {
        this(null);
    }

    protected AnnotationListResourceDeserializer(Class<?> vc) {
        super(vc);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
    }

    @Override
    public List<IAnnotation> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        if (node.isArray()) {
            List<IAnnotation> annoList = new ArrayList<>();
            for (JsonNode arrNode : node) {
                annoList.add(deserializeSingleAnno(arrNode));
            }
            return annoList;
        } else {
            return Collections.singletonList(deserializeSingleAnno(node));
        }
    }

    private IAnnotation deserializeSingleAnno(JsonNode node) throws JsonProcessingException {
        
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
        
//        if (node.has("resource")) {
//            return mapper.treeToValue(node, OpenAnnotation.class);
//        } else {
//            return mapper.treeToValue(node, OpenAnnotation.class);
//        }
    }
}

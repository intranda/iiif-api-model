package de.intranda.api.deserializer;

import java.io.IOException;
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

import de.intranda.api.annotation.IAnnotation;
import de.intranda.api.annotation.oa.LinkedAnnotation;
import de.intranda.api.annotation.oa.TextualAnnotation;

public class AnnotationListResourceDeserializer extends StdDeserializer<List<IAnnotation>> {
    private static final long serialVersionUID = 7191457467443751957L;
    private ObjectMapper mapper = new ObjectMapper();

    public AnnotationListResourceDeserializer() {
        this(null);
    }

    protected AnnotationListResourceDeserializer(Class<?> vc) {
        super(vc);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
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
        if (node.has("resource")) {
            return mapper.treeToValue(node, LinkedAnnotation.class);
        } else {
            return mapper.treeToValue(node, TextualAnnotation.class);
        }
    }
}

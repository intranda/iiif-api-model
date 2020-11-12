package de.intranda.api.deserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import de.intranda.api.iiif.presentation.IPresentationModelElement;
import de.intranda.metadata.multilanguage.IMetadataValue;
import de.intranda.metadata.multilanguage.MultiLanguageMetadataValue;
import de.intranda.metadata.multilanguage.SimpleMetadataValue;

public class IMetadataValueDeserializer extends StdDeserializer<IMetadataValue> {

    private static final long serialVersionUID = -304949900474343685L;

    public IMetadataValueDeserializer() {
        this(null);
    }

    public IMetadataValueDeserializer(Class<IPresentationModelElement> vc) {
        super(vc);
    }

    @Override
    public IMetadataValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        if (node.isTextual()) {
            return new SimpleMetadataValue(node.asText());
        } else if (node.isArray()) {
            MultiLanguageMetadataValue mlmv = new MultiLanguageMetadataValue();
            for (JsonNode objNode : node) {
                if (objNode.isObject()) {
                    mlmv.setValue(objNode.get("@value").asText(), objNode.get("@language").asText());
                } else {
                    return new SimpleMetadataValue(objNode.asText());
                }
            }
            return mlmv;
        } else if( node.isObject()) {
            MultiLanguageMetadataValue mlmv = new MultiLanguageMetadataValue();
            Iterator<String> fieldNames = node.fieldNames();
            while(fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                JsonNode valueNode = node.get(fieldName);
                String value = null;
                if(valueNode.isTextual()) {
                    value = valueNode.textValue();
                } else if(valueNode.isArray()) {
                    if(valueNode.size() == 1 && valueNode.get(0).isTextual()) {
                        value = valueNode.get(0).textValue();
                    } else {                        
                        List<String> list = new ArrayList<String>();
                        for (JsonNode listNode : valueNode) {
                            if(listNode.isTextual()) {
                                list.add(listNode.textValue());
                            }
                        }
                        value = list.stream().collect(Collectors.joining("; "));
                    }
                }
                if(value != null) {
                    mlmv.setValue(value, fieldName);
                }
            }
            return mlmv;
        }
        return null;
    }
}

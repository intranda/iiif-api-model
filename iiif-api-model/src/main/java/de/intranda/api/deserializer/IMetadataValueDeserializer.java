package de.intranda.api.deserializer;

import java.io.IOException;

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
        } else {
            if (node.isArray()) {
                MultiLanguageMetadataValue mlmv = new MultiLanguageMetadataValue();
                for (JsonNode objNode : node) {
                    if (objNode.isObject()) {
                        mlmv.setValue(objNode.get("@value").asText(), objNode.get("@language").asText());
                    } else {
                        return new SimpleMetadataValue(objNode.asText());
                    }
                }
                return mlmv;
            }
            return null;
        }
    }

}

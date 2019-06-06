package de.intranda.api.deserializer;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import de.intranda.api.iiif.presentation.content.IContent;
import de.intranda.api.iiif.presentation.content.ImageContent;
import de.intranda.api.iiif.presentation.content.LinkingContent;

public class LinkedAnnotationResourceDeserializer extends StdDeserializer<IContent> {

    private static final long serialVersionUID = 1465316436742776706L;
    private ObjectMapper mapper = new ObjectMapper();

    public LinkedAnnotationResourceDeserializer() {
        this(null);
    }

    protected LinkedAnnotationResourceDeserializer(Class<?> vc) {
        super(vc);
        // TODO Auto-generated constructor stub
    }

    @Override
    public IContent deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        if (node.isTextual()) {
            try {
                URI id = new URI(node.textValue());
                return new ImageContent(id);
            } catch (URISyntaxException e) {
                return null;
            }
        } else if (node.has("width")) {
            return mapper.treeToValue(node, ImageContent.class);
        } else {
            return mapper.treeToValue(node, LinkingContent.class);
        }
    }

}

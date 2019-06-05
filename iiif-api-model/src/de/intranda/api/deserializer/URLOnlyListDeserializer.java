package de.intranda.api.deserializer;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import de.intranda.api.iiif.presentation.IPresentationModelElement;
import de.intranda.api.iiif.presentation.Manifest;

public class URLOnlyListDeserializer extends StdDeserializer<List<IPresentationModelElement>> {

    private static final long serialVersionUID = 548556810800461156L;

    public URLOnlyListDeserializer() {
        this(null);
    }

    protected URLOnlyListDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public List<IPresentationModelElement> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        if (node.isTextual()) {
            try {
                return Collections.singletonList(new Manifest(new URI(node.asText())));
            } catch (URISyntaxException e) {
                return null;
            }
        }
        return null;
    }

}

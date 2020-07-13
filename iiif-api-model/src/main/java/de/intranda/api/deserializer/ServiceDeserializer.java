package de.intranda.api.deserializer;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import de.intranda.api.services.BaseService;
import de.intranda.api.services.Service;


public class ServiceDeserializer extends StdDeserializer<Service> {

    /**
     * 
     */
    private static final long serialVersionUID = -378940547310966442L;

    public ServiceDeserializer() {
        this(null);
    }
    
    public ServiceDeserializer(Class<Service> vc) {
        super(vc);
    }

    @Override
    public Service deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        if(node.has("@context")) {
            String context = node.get("@context").asText();
            return new BaseService(URI.create(context));
        } else {
            return new BaseService();
        }
    }
    

}

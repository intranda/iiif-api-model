package de.intranda.api.deserializer;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import de.intranda.api.annotation.AgentType;
import de.intranda.api.annotation.wa.Agent;

public class AgentDeserializer extends StdDeserializer<Agent> {

    /**
     * 
     */
    private static final long serialVersionUID = -6733687272555595794L;
    
    private ObjectMapper mapper = new ObjectMapper();

    public AgentDeserializer() {
        this(null);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
    }
    
    /**
     * @param vc
     */
    public AgentDeserializer(Class<?> vc) {
        super(vc);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
    }


    @Override
    public Agent deserialize(JsonParser p, DeserializationContext context) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        try {
            return parseNode(node, p, context);
        } catch (NullPointerException e) {
            throw new JsonParseException(p, "Missing json properties. Could not deserialize resource");
        }

    }

    /**
     * @param p
     * @param node
     * @param p 
     * @param context 
     * @throws IOException 
     * @throws JsonParseException
     */
    private Agent parseNode(JsonNode node, JsonParser p, DeserializationContext context) throws IOException {
        Agent resource = null;
            if (node.isTextual()) {
                resource = new Agent(URI.create(node.asText()), null, null);
                return resource;
            } else {
                URI id = Optional.ofNullable(node.get("id")).map(JsonNode::asText).map(URI::create).orElse(null);
                AgentType type = Optional.ofNullable(node.get("type")).map(JsonNode::asText).map(String::toUpperCase).map(AgentType::valueOf).orElse(null);
                String name = Optional.ofNullable(node.get("name")).map(JsonNode::asText).orElse(null);
                resource = new Agent(id, type, name);
                return resource;
            }
    }


}

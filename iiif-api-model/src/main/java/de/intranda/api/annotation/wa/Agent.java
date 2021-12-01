package de.intranda.api.annotation.wa;

import java.net.URI;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import de.intranda.api.annotation.AgentType;
import de.intranda.api.annotation.IAgent;
import de.intranda.api.deserializer.AgentDeserializer;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonDeserialize(using=AgentDeserializer.class)
public class Agent implements IAgent {

    private final URI id;
    private final AgentType type;
    private final String name;
    
    public Agent() {
        this(null, null, null);
    }
    
    public Agent(URI id, AgentType type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }
    
    @Override
    public URI getId() {
        return id;
    }
    
    public AgentType getType() {
        return type;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj.getClass().equals(this.getClass())) {
            Agent other = (Agent) obj;
            return this.getId().equals(other.getId())
                    && this.getType().equals(other.getType())
                    && (StringUtils.isBlank(this.getName()) && StringUtils.isBlank(other.getName()) || (this.getName() != null && this.getName().equals(other.getName())));
        } else {
            return false;
        }
    }
    
    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();        
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
           return super.toString();
        }
    }

}

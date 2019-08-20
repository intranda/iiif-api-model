package de.intranda.api.annotation.wa;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import de.intranda.api.annotation.AgentType;
import de.intranda.api.annotation.IAgent;

@JsonInclude(Include.NON_NULL)
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

}

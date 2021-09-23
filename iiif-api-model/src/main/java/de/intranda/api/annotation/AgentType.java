package de.intranda.api.annotation;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AgentType {

    PERSON("Person"),
    ORGANIZATION("Organization"),
    SOFTWARE("Software"),
    AGENT("Agent");
    
    private String label;
    
    private AgentType(String label) {
        this.label = label;
    }
    
    @JsonValue
    public String getLabel() {
        return label;
    }

    
}

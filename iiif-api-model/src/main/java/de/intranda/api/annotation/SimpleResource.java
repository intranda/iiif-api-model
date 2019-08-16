package de.intranda.api.annotation;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public class SimpleResource implements IResource {
    
    private final URI id;
    
    public SimpleResource(URI id) {
        this.id = id;
    }
    
    @JsonValue()
    public URI getId() {
        return id;
    }
    

}

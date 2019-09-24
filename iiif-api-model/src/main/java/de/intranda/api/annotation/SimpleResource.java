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
    
    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj.getClass().equals(SimpleResource.class)) {
            return this.id.equals(((SimpleResource)obj).id);            
        } else {
            return false;
        }
    }
    

}

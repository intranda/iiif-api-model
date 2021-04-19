package de.intranda.api.annotation.wa;

import java.net.URI;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import de.intranda.api.annotation.IResource;
import de.intranda.api.annotation.ITypedResource;
import de.intranda.api.deserializer.ResourceDeserializer;

@JsonInclude(Include.NON_NULL)
@JsonDeserialize(using=ResourceDeserializer.class)
public class TypedResource implements ITypedResource {

    private final String type;
    private final String format;
    private final URI id;
    private final String profile;

    public TypedResource(URI id, String type, String format, String profile) {
        this.id = id;
        this.type = type;
        this.format = format;
        this.profile = profile;
    }
    
    public TypedResource(URI id, String type, String format) {
        this(id, type, format, null);
    }

    public TypedResource(URI id, String type) {
    	this(id, type, null, null);
    }
    
    public TypedResource() {
        this(null, null, null, null);
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the format
     */
    public String getFormat() {
        return format;
    }
    
    @Override
    public URI getId() {
        return id;
    }
    
    public String getProfile() {
    	return profile;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj.getClass().equals(this.getClass())) {
            TypedResource other = (TypedResource)obj;
            return this.getId().equals(other.getId()) 
                    && Objects.equals(this.getType(), other.getType())
                    && Objects.equals(this.getFormat(), other.getFormat());
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

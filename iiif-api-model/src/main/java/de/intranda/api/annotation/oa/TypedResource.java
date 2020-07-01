package de.intranda.api.annotation.oa;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import de.intranda.api.annotation.IResource;
import de.intranda.api.annotation.ITypedResource;
import de.intranda.api.annotation.SimpleResource;
import de.intranda.api.deserializer.ResourceDeserializer;

@JsonInclude(Include.NON_NULL)
@JsonDeserialize(using=ResourceDeserializer.class)
public class TypedResource implements ITypedResource, IResource{
    
    private final URI id;
    private final String type;
    private final String format;
    
    public TypedResource() {
        this.id = null;
        this.type = null;
        this.format = null;
    }
    
    public TypedResource(URI id, String type, String format) {
        this.type = type;
        this.format = format;
        this.id = id;
    }

    public TypedResource(URI id, String type) {
        this.type = type;
        this.id = id;
        this.format = null;
    }
    /**
     * @return the type
     */
    @JsonProperty("@type")
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

    
    
    
}

package de.intranda.api.annotation.oa;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import de.intranda.api.annotation.IResource;
import de.intranda.api.annotation.SimpleResource;

@JsonInclude(Include.NON_NULL)
public class TypedResource implements IResource{
    
    private final URI id;
    private final String type;
    private final String format;
    
    public TypedResource(URI id, String type, String format) {
        this.type = type;
        this.format = format;
        this.id = id;
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

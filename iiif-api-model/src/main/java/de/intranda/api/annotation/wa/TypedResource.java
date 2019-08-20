package de.intranda.api.annotation.wa;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import de.intranda.api.annotation.IResource;
import de.intranda.api.annotation.SimpleResource;

@JsonInclude(Include.NON_NULL)
public class TypedResource implements IResource {

    private final String type;
    private final String format;
    private final URI id;
    
    public TypedResource(URI id, String type, String format) {
        this.id = id;
        this.type = type;
        this.format = format;
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
    
}

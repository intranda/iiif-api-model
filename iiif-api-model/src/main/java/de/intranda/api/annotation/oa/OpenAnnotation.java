package de.intranda.api.annotation.oa;

import java.net.URI;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import de.intranda.api.annotation.AbstractAnnotation;
import de.intranda.api.annotation.IAgent;
import de.intranda.api.annotation.IResource;

@JsonPropertyOrder({ "@id", "@type", "motivation", "resource", "on" })
public class OpenAnnotation extends AbstractAnnotation{

    public final static String TYPE = "oa:Annotation";

    @JsonProperty("@type")
    public String getType() {
        return TYPE;
    }
    
    public OpenAnnotation() {
        super(null);
    }
    
    /**
     * @param id
     */
    public OpenAnnotation(URI id) {
        super(id);
    }
    @Override
    @JsonProperty("@id")
    public URI getId() {
        return super.getId();
    }

    @Override
    @JsonProperty("on")
    public IResource getTarget() {
        return super.getTarget();
    }
    
    @Override
    @JsonProperty("resource")
    public IResource getBody() {
        return super.getBody();
    }

    @Override
    public IAgent getCreator() {
        return null;
    }

    @Override
    public IAgent getGenerator() {
        return null;
    }

    @Override
    public Date getCreated() {
        return null;
    }

    @Override
    public Date getModified() {
        return null;
    }

}

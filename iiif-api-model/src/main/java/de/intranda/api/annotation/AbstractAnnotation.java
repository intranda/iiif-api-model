package de.intranda.api.annotation;

import java.net.URI;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import de.intranda.api.deserializer.ResourceDeserializer;

@JsonPropertyOrder({ "@id", "@type", "motivation", "on", "resource" })
@JsonInclude(Include.NON_NULL)
public abstract class AbstractAnnotation implements IAnnotation {

    private String motivation;
    private final URI id;
    private IResource body;
    private IResource target;

    /**
     * @param id
     */
    public AbstractAnnotation(URI id) {
        this.id = id;
    }

    @Override
    public URI getId() {
        return id;
    }



    /**
     * @return the motivation
     */
    public String getMotivation() {
        return motivation;
    }

    /**
     * @param motivation the motivation to set
     */
    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    /**
     * @return the on
     */
    public IResource getTarget() {
        return target;
    }

    /**
     * @param on the on to set
     */
    public void setTarget(IResource on) {
        this.target = on;
    }
    
    public void setBody(IResource body) {
        this.body = body;
    }
    
    @JsonDeserialize(using = ResourceDeserializer.class)
    public IResource getBody() {
        return body;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj.getClass().equals(this.getClass())) {
            AbstractAnnotation other = (AbstractAnnotation)obj;
            return this.getId().equals(other.getId())
                    && Objects.equals(this.getBody(), other.getBody()) 
                    && Objects.equals(this.getTarget(), other.getTarget())
                    && Objects.equals(this.getMotivation(), other.getMotivation());
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

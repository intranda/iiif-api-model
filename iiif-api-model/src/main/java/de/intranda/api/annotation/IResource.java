package de.intranda.api.annotation;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import de.intranda.api.deserializer.AnnotationDeserializer;
import de.intranda.api.deserializer.ResourceDeserializer;

@JsonIgnoreProperties(ignoreUnknown=true)
public interface IResource {

    public URI getId();
    
}

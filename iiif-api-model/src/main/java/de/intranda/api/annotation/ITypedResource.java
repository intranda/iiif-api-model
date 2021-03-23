package de.intranda.api.annotation;

import java.net.URI;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import de.intranda.api.deserializer.ResourceDeserializer;

@JsonDeserialize(using=ResourceDeserializer.class)
public interface ITypedResource extends IResource{

    public URI getId();
    public String getType();
    public String getFormat();
    /**
     * By default profile returns null. This may be overwritten by implementing classes
     * @return
     */
    public default String getProfile() {
    	return null;
    }
}

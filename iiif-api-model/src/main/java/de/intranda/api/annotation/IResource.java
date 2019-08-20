package de.intranda.api.annotation;

import java.net.URI;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import de.intranda.api.deserializer.ResourceDeserializer;

@JsonDeserialize(using=ResourceDeserializer.class)
public interface IResource {

    public URI getId();

}

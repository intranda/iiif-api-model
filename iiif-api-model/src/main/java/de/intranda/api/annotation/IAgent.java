package de.intranda.api.annotation;

import java.net.URI;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import de.intranda.api.deserializer.AgentDeserializer;

@JsonDeserialize(using=AgentDeserializer.class)
public interface IAgent {

    public URI getId();

}

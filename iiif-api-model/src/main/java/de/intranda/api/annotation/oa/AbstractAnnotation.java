package de.intranda.api.annotation.oa;

import java.net.URI;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.intranda.api.annotation.IAgent;
import de.intranda.api.annotation.IAnnotation;
import de.intranda.api.annotation.IResource;
import de.intranda.api.annotation.SimpleResource;
import de.intranda.api.iiif.presentation.Canvas;
import de.intranda.api.iiif.presentation.ICanvas;
import de.intranda.api.iiif.presentation.enums.Motivation;
import de.intranda.api.serializer.AnnotationTargetSerializer;

@JsonPropertyOrder({ "@id", "@type", "motivation", "on", "resource" })
public abstract class AbstractAnnotation implements IAnnotation {

    public final static String TYPE = "oa:Annotation";

    private Motivation motivation;
    private ICanvas on;
    private final URI id;

    /**
     * @param id
     */
    public AbstractAnnotation(URI id) {
        this.id = id;
    }

    @Override
    @JsonProperty("@id")
    public URI getId() {
        return id;
    }

    @JsonProperty("@type")
    public String getType() {
        return TYPE;
    }

    /**
     * @return the motivation
     */
    public Motivation getMotivation() {
        return motivation;
    }

    /**
     * @param motivation the motivation to set
     */
    public void setMotivation(Motivation motivation) {
        this.motivation = motivation;
    }

    /**
     * @return the on
     */
    @JsonSerialize(using = AnnotationTargetSerializer.class)
    @JsonDeserialize(as = Canvas.class)
    public ICanvas getOn() {
        return on;
    }

    /**
     * @param on the on to set
     */
    public void setOn(ICanvas on) {
        this.on = on;
    }

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.servlets.rest.content.IComment#getTarget()
     */
    @Override
    @JsonIgnore
    public IResource getTarget() {
        return new SimpleResource(on.getId());
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

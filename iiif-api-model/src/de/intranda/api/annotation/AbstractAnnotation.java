package de.intranda.api.annotation;

import java.net.URI;
import java.net.URISyntaxException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.intranda.api.iiif.presentation.Canvas;
import de.intranda.api.iiif.presentation.enums.Motivation;
import de.intranda.api.serializer.URLOnlySerializer;

public class AbstractAnnotation implements IAnnotation {

    public final static String TYPE = "oa:Annotation";

    private Motivation motivation;
    private Canvas on;
    private final URI id;

    /**
     * @param id
     */
    public AbstractAnnotation(URI id) {
        this.id = id;
    }

    @Override
    @JsonProperty("@id")
    public URI getId() throws URISyntaxException {
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
    @JsonSerialize(using = URLOnlySerializer.class)
    public Canvas getOn() {
        return on;
    }

    /**
     * @param on the on to set
     */
    public void setOn(Canvas on) {
        this.on = on;
    }

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.servlets.rest.content.IComment#getTarget()
     */
    @Override
    @JsonIgnore
    public URI getTarget() throws URISyntaxException {
        return on.getId();
    }
}

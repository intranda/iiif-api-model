package de.intranda.api.annotation;

import java.net.URI;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import de.intranda.api.deserializer.AnnotationDeserializer;

@JsonDeserialize(using=AnnotationDeserializer.class)
public class IncomingAnnotation extends AbstractAnnotation {

    private LocalDateTime created;
    
    public IncomingAnnotation(URI uri) {
        super(uri);
        this.created = LocalDateTime.now();
    }
    
    public IncomingAnnotation(AbstractAnnotation orig) {
        super(orig.getId());
        this.setTarget(orig.getTarget());
        this.setBody(orig.getBody());
        this.setMotivation(orig.getMotivation());
        this.setRights(orig.getRights());
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
    public LocalDateTime getCreated() {
        return this.created;
    }

    @Override
    public LocalDateTime getModified() {
        return this.created;
    }

}

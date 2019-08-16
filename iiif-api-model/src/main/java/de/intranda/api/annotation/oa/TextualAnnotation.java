package de.intranda.api.annotation.oa;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.intranda.api.annotation.IAnnotation;

public class TextualAnnotation  extends AbstractAnnotation implements IAnnotation{

    private TextualAnnotationBody body;
    
    public TextualAnnotation(URI id) {
        super(id);
    }

    @JsonProperty("resource")
    public TextualAnnotationBody getBody() {
        return body;
    }
    
    public void setBody(TextualAnnotationBody body) {
        this.body = body;
    }
    

}

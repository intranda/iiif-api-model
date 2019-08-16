package de.intranda.api.annotation.wa;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.intranda.api.annotation.IAnnotation;

public class TextualAnnotation  extends AbstractAnnotation implements IAnnotation{

    private TextualAnnotationBody body;
    
    public TextualAnnotation(URI id) {
        super(id);
    }

    public TextualAnnotationBody getBody() {
        return body;
    }
    
    public void setBody(TextualAnnotationBody body) {
        this.body = body;
    }
    

}

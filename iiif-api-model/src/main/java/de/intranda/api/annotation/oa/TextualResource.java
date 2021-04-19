package de.intranda.api.annotation.oa;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TextualResource extends TypedResource {

    private final String text;
    
    public TextualResource(URI id, String text) {
    	super(id, "cnt:ContentAsText", "text/plain");
        this.text = text;
    }
    
    public TextualResource(String text) {
        super(null, "cnt:ContentAsText", "text/plain");
        this.text = text;
    }
    
    @JsonProperty("chars")
    public String getText() {
        return text;
    }
}

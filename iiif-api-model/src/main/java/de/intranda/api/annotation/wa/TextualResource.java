package de.intranda.api.annotation.wa;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TextualResource extends TypedResource {

    private final String text;
    
    public TextualResource(String text) {
        super(null, "TextualBody", "text/plain");
        this.text = text;
    }
    
    @JsonProperty("value")
    public String getText() {
        return text;
    }
}

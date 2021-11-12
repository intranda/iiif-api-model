package de.intranda.api.annotation.wa;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TextualResource extends TypedResource {

    private final String text;
    
    public TextualResource(String text) {
        super(null, "TextualBody", "text/plain");
        this.text = text;
    }
    
    public TextualResource(String text, String format) {
        super(null, "TextualBody", format);
        this.text = text;
    }
    
    @JsonProperty("value")
    public String getText() {
        return text;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj.getClass().equals(this.getClass())) {
            return this.getText().equals(((TextualResource) obj).getText());
        } else {
            return false;
        }
    }
    
    @Override
    public String toString() {
        return getText();
    }
}

package de.intranda.api.annotation.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import de.intranda.api.annotation.IResource;

@JsonPropertyOrder({"@type", "format", "chars"})
public class TextualAnnotationBody implements IResource {

    private static final String TYPE = "cnt:ContentAsText";
    private static final String FORMAT = "text/plain";
    
    private String value;
    
    @JsonProperty("@type")
    public String getType() {
        return TYPE;
    }
    
    public String getFormat() {
        return FORMAT;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    @JsonProperty("chars")
    public String getValue() {
        return value;
    }
}
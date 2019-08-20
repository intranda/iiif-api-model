package de.intranda.api.annotation.oa;

import java.awt.Rectangle;
import java.net.URI;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.intranda.api.annotation.ISelector;

public class SpecificResource extends TypedResource {
    
    private final ISelector selector;
    
    public SpecificResource(URI source, ISelector selector) {
        super(source, "oa:SpecificResource", null);
        this.selector = selector;
    }

    public ISelector getSelector() {
        return this.selector;
    }
    
    @Override
    @JsonProperty("full")
    public URI getId() {
        return super.getId();
    }

}

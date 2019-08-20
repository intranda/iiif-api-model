package de.intranda.api.annotation.wa;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.intranda.api.annotation.ISelector;

public class SpecificResource extends TypedResource {
    
    private final ISelector selector;
    
    public SpecificResource(URI source, ISelector selector) {
        super(source, "SpecificResource", null);
        this.selector = selector;
    }

    public ISelector getSelector() {
        return this.selector;
    }

    @Override
    @JsonProperty("source")
    public URI getId() {
        // TODO Auto-generated method stub
        return super.getId();
    }


}

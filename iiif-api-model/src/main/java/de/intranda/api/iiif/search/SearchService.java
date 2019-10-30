package de.intranda.api.iiif.search;

import java.net.URI;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.intranda.api.services.Service;

public class SearchService implements Service{
    
    private static final String CONTEXT = "http://iiif.io/api/search/1/search";
    private static final String PROFILE = "http://iiif.io/api/search/1/search";
    
    private final URI id;

    public SearchService(URI id) {
        this.id = id;
    }
    
    @JsonProperty("@id")
    public URI getId() {
        return id;
    }
    
    public URI getContext() {
        return URI.create(CONTEXT);
    }
    
    public URI getProfile() {
        return URI.create(PROFILE);
    }


}

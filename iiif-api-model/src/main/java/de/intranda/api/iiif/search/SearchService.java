package de.intranda.api.iiif.search;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import de.intranda.api.PropertyList;
import de.intranda.api.services.Service;

@JsonPropertyOrder({ "@context", "@id", "profile", "@type"})
@JsonInclude(Include.NON_EMPTY)
public class SearchService implements Service{
    
    private static final String CONTEXT = "http://iiif.io/api/search/1/context.json";
    private static final String PROFILE = "http://iiif.io/api/search/1/search";
    private static final String TYPE = "SearchService1";
    
    private final URI id;
    private List<Service> service = new ArrayList<>();


    public SearchService(URI id) {
        this.id = id;
    }
    
    @JsonProperty("@id")
    public URI getId() {
        return id;
    }
    
    @JsonProperty("@type")
    public String getType() {
        return TYPE;
    }
    
    public URI getContext() {
        return URI.create(CONTEXT);
    }
    
    public URI getProfile() {
        return URI.create(PROFILE);
    }
    
    public void setService(PropertyList<Service> service) {
        this.service = service;
    }
    
    public void addService(Service service) {
        this.service.add(service);
    }
    
    public List<Service> getService() {
        if(service.isEmpty()) {
            return null;
        }
        return service;
    }



}

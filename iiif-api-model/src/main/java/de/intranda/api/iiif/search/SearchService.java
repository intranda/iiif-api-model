package de.intranda.api.iiif.search;

import java.net.URI;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import de.intranda.api.PropertyList;
import de.intranda.api.services.Service;
import de.intranda.metadata.multilanguage.IMetadataValue;

@JsonPropertyOrder({ "@context", "@id", "@type"})
@JsonInclude(Include.NON_EMPTY)
public class SearchService implements Service{
    
    private static final String CONTEXT = "http://iiif.io/api/search/1/context.json";
    private static final String PROFILE = "http://iiif.io/api/search/1/search";
    
    private final URI id;
    private PropertyList<Service> service = new PropertyList<>();
    private IMetadataValue label = null;


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
    
    public void setService(PropertyList<Service> service) {
        this.service = service;
    }
    
    public void addService(Service service) {
        this.service.add(service);
    }
    
    public PropertyList<Service> getService() {
        return service;
    }
    
    public IMetadataValue getLabel() {
        return label;
    }
    
    public void setLabel(IMetadataValue label) {
        this.label = label;
    }


}

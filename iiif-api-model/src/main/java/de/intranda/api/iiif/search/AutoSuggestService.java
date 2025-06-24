package de.intranda.api.iiif.search;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import de.intranda.api.services.Service;
import de.intranda.metadata.multilanguage.IMetadataValue;

@JsonPropertyOrder({ "@context", "@id", "profile", "@type" })
@JsonInclude(Include.NON_NULL)
public class AutoSuggestService implements Service {

    private static final String CONTEXT = "http://iiif.io/api/search/1/context.json";
    private static final String PROFILE = "http://iiif.io/api/search/1/autocomplete";
    private static final String TYPE = "AutoCompleteService1";

    private final URI id;
    private IMetadataValue label = null;

    public AutoSuggestService(URI id) {
        this.id = id;
    }

    @Override
    @JsonIgnore
    public URI getContext() {
        return URI.create(CONTEXT);
    }

    public String getProfile() {
        return PROFILE;
    }

    @JsonProperty("@id")
    public URI getId() {
        return id;
    }

    @JsonProperty("@type")
    public String getType() {
        return TYPE;
    }

    public IMetadataValue getLabel() {
        return label;
    }

    public void setLabel(IMetadataValue label) {
        this.label = label;
    }

}

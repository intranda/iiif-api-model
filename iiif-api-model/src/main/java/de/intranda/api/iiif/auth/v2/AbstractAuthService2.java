package de.intranda.api.iiif.auth.v2;

import java.net.URI;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.intranda.api.iiif.image.Service;

public abstract class AbstractAuthService2 extends Service {

    static final String CONTEXT = "http://iiif.io/api/auth/2/context.json";

    public Map<String, String> heading;

    public Map<String, String> note;
    
    public Map<String, String> confirmNote;

    @JsonProperty("@id")
    public abstract URI getId();

    @JsonIgnore
    public URI getContext() {
        return URI.create(CONTEXT);
    }

    @JsonProperty("@type")
    public abstract String getType();
}

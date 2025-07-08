package de.intranda.api.iiif.auth.v2;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.intranda.api.services.Service;

public abstract class AbstractAuthService2 implements IAuthMessage, Service {

    public abstract URI getId();

    @JsonProperty("@context")
    public URI getContext() {
        return URI.create(CONTEXT);
    }

    public abstract String getType();
}

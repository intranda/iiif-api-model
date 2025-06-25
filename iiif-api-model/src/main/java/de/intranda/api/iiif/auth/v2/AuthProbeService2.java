package de.intranda.api.iiif.auth.v2;

import java.net.URI;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthProbeService2 extends AbstractAuthService2 {

    public static final String TYPE = "AuthProbeService2";

    private final URI id;

    private Map<String, String> errorHeading;

    private Map<String, String> errorNote;

    private List<AuthAccessService2> service;

    public AuthProbeService2(URI id, List<AuthAccessService2> service) {
        this.id = id;
        this.service = service;
    }

    @JsonProperty("id")
    public URI getId() {
        return id;
    }

    @JsonProperty("type")
    public String getType() {
        return TYPE;
    }

    @JsonProperty("errorHeading")
    public Map<String, String> getErrorHeading() {
        return errorHeading;
    }

    @JsonProperty("errorNote")
    public Map<String, String> getErrorNote() {
        return errorNote;
    }

    @JsonProperty("service")
    public List<AuthAccessService2> getService() {
        return service;
    }
}

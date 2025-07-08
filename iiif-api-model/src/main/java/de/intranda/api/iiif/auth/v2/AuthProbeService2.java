package de.intranda.api.iiif.auth.v2;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "@context", "id", "type", "errorHeading", "errorNote", "service" })
public class AuthProbeService2 extends AbstractAuthService2 {

    public static final String TYPE = "AuthProbeService2";

    private final URI id;

    private final Map<String, String> errorHeading = new HashMap<>();

    private final Map<String, String> errorNote = new HashMap<>();

    private final List<AuthAccessService2> service;

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
    @JsonInclude(Include.NON_NULL)
    public Map<String, String> getErrorHeading() {
        return errorHeading;
    }

    @JsonProperty("errorNote")
    @JsonInclude(Include.NON_NULL)
    public Map<String, String> getErrorNote() {
        return errorNote;
    }

    @JsonProperty("service")
    public List<AuthAccessService2> getService() {
        return service;
    }
}

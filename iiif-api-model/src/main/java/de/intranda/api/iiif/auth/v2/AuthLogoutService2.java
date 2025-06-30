package de.intranda.api.iiif.auth.v2;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthLogoutService2 extends AbstractAuthService2 {

    public static final String TYPE = "AuthLogoutService2";

    private final URI id;

    private Map<String, String> label = new HashMap<>();

    public AuthLogoutService2(URI id) {
        this.id = id;
    }

    @JsonProperty("id")
    public URI getId() {
        return id;
    }

    @JsonProperty("type")
    public String getType() {
        return TYPE;
    }

    @JsonProperty("label")
    public Map<String, String> getLabel() {
        return label;
    }

}

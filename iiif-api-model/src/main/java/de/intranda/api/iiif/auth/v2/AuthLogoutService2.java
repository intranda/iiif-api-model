package de.intranda.api.iiif.auth.v2;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthLogoutService2 extends AbstractAuthService2 {

    public static final String TYPE = "AuthLogoutService2";

    private final URI id;

    public AuthLogoutService2(URI id) {
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
}

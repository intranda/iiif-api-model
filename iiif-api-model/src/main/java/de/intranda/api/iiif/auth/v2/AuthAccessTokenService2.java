package de.intranda.api.iiif.auth.v2;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "@context", "id", "type", "errorHeading", "errorNote" })
public class AuthAccessTokenService2 extends AbstractAuthService2 {

    public static final String TYPE = "AuthAccessTokenService2";

    private final URI id;

    private final Map<String, String> errorHeading = new HashMap<>();

    private final Map<String, String> errorNote = new HashMap<>();

    public AuthAccessTokenService2(URI id) {
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

    @JsonProperty("errorHeading")
    @JsonInclude(Include.NON_EMPTY)
    public Map<String, String> getErrorHeading() {
        return errorHeading;
    }

    @JsonProperty("errorNote")
    @JsonInclude(Include.NON_EMPTY)
    public Map<String, String> getErrorNote() {
        return errorNote;
    }
}

package de.intranda.api.iiif.auth.v2;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "@context", "type", "status", "substitute", "location", "heading", "note" })
public class AuthProbeResult2 implements IAuthMessage {

    private static final String TYPE = "AuthProbeResult2";

    private int status;

    private final Map<String, String> substitute = new HashMap<>();

    private final Map<String, String> location = new HashMap<>();

    private final Map<String, String> heading = new HashMap<>();

    private final Map<String, String> note = new HashMap<>();

    @JsonProperty("@context")
    public String getContext() {
        return CONTEXT;
    }

    @JsonProperty("type")
    public String getType() {
        return TYPE;
    }

    @JsonProperty("status")
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     * @return this
     */
    public AuthProbeResult2 setStatus(int status) {
        this.status = status;
        return this;
    }

    @JsonProperty("substitute")
    @JsonInclude(Include.NON_EMPTY)
    public Map<String, String> getSubstitute() {
        return substitute;
    }

    @JsonProperty("location")
    @JsonInclude(Include.NON_EMPTY)
    public Map<String, String> getLocation() {
        return location;
    }

    @JsonProperty("heading")
    @JsonInclude(Include.NON_EMPTY)
    public Map<String, String> getHeading() {
        return heading;
    }

    @JsonProperty("note")
    @JsonInclude(Include.NON_EMPTY)
    public Map<String, String> getNote() {
        return note;
    }
}

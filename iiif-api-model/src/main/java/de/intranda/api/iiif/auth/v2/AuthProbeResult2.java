package de.intranda.api.iiif.auth.v2;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthProbeResult2 implements IAuthMessage {

    private static final String TYPE = "AuthProbeResult2";

    private int status;

    private Map<String, String> substitute;

    private Map<String, String> location;

    private Map<String, String> heading;

    private Map<String, String> note;

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
    @JsonInclude(Include.NON_NULL)
    public Map<String, String> getSubstitute() {
        return substitute;
    }

    /**
     * @param substitute the substitute to set
     * @return this
     */
    public AuthProbeResult2 setSubstitute(Map<String, String> substitute) {
        this.substitute = substitute;
        return this;
    }

    @JsonProperty("location")
    @JsonInclude(Include.NON_NULL)
    public Map<String, String> getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     * @return this
     */
    public AuthProbeResult2 setLocation(Map<String, String> location) {
        this.location = location;
        return this;
    }

    @JsonProperty("heading")
    @JsonInclude(Include.NON_NULL)
    public Map<String, String> getHeading() {
        return heading;
    }

    /**
     * @param heading the heading to set
     * @return this
     */
    public AuthProbeResult2 setHeading(Map<String, String> heading) {
        this.heading = heading;
        return this;
    }

    @JsonProperty("note")
    @JsonInclude(Include.NON_NULL)
    public Map<String, String> getNote() {
        return note;
    }

    /**
     * @param note the note to set
     * @return this
     */
    public AuthProbeResult2 setNote(Map<String, String> note) {
        this.note = note;
        return this;
    }

}

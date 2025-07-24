package de.intranda.api.iiif.auth.v2;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "@context", "id", "type", "profile", "label", "heading", "note", "confirmLabel", "service" })
public class AuthAccessService2 extends AbstractAuthService2 {

    public enum Profile {
        ACTIVE,
        EXTERNAL,
        KIOSK;
    }

    public static final String TYPE = "AuthAccessService2";

    private final URI id;

    private final Profile profile;

    private final Map<String, String> label;

    private final Map<String, String> heading = new HashMap<>();

    private final Map<String, String> note = new HashMap<>();

    private final Map<String, String> confirmLabel = new HashMap<>();

    private final AuthAccessTokenService2 tokenService;

    private final AuthLogoutService2 logoutService;

    /**
     * 
     * @param id
     * @param profile
     * @param label
     * @param tokenService
     * @param logoutService
     */
    public AuthAccessService2(URI id, Profile profile, Map<String, String> label, AuthAccessTokenService2 tokenService,
            AuthLogoutService2 logoutService) {
        if (profile == null) {
            throw new IllegalArgumentException("profile may not be null");
        }
        if (tokenService == null) {
            throw new IllegalArgumentException("tokenService may not be null");
        }

        this.id = id;
        this.profile = profile;
        this.label = label;
        this.tokenService = tokenService;
        this.logoutService = logoutService;
    }

    @JsonProperty("id")
    public URI getId() {
        return id;
    }

    @JsonProperty("type")
    public String getType() {
        return TYPE;
    }

    @JsonProperty("profile")
    public String getProfile() {
        // return profile.name().toLowerCase();
        return "http://iiif.io/api/auth/2/login";
    }

    @JsonProperty("label")
    public Map<String, String> getLabel() {
        return label;
    }

    /**
     * @return the heading
     */
    @JsonProperty("heading")
    @JsonInclude(Include.NON_EMPTY)
    public Map<String, String> getHeading() {
        return heading;
    }

    /**
     * 
     * @param lang
     * @param text
     * @return this
     */
    public AuthAccessService2 addHeading(String lang, String text) {
        heading.put(lang, text);
        return this;
    }

    /**
     * @return the note
     */
    @JsonProperty("note")
    @JsonInclude(Include.NON_EMPTY)
    public Map<String, String> getNote() {
        return note;
    }

    /**
     * 
     * @param lang
     * @param text
     * @return this
     */
    public AuthAccessService2 addNote(String lang, String text) {
        note.put(lang, text);
        return this;
    }

    /**
     * @return the confirmLabel
     */
    @JsonProperty("confirmLabel")
    @JsonInclude(Include.NON_EMPTY)
    public Map<String, String> getConfirmLabel() {
        return confirmLabel;
    }

    /**
     * 
     * @param lang
     * @param text
     * @return this
     */
    public AuthAccessService2 addConfirmLabel(String lang, String text) {
        confirmLabel.put(lang, text);
        return this;
    }

    @JsonProperty("service")
    public List<AbstractAuthService2> getService() {
        List<AbstractAuthService2> ret = new ArrayList<>();
        if (tokenService != null) {
            ret.add(tokenService);
        }
        if (logoutService != null) {
            ret.add(logoutService);
        }

        return ret;
    }

    /**
     * @return the tokenService
     */
    @JsonIgnore
    public AuthAccessTokenService2 getTokenService() {
        return tokenService;
    }

    /**
     * @return the logoutService
     */
    @JsonIgnore
    public AuthLogoutService2 getLogoutService() {
        return logoutService;
    }
}

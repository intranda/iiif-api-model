package de.intranda.api.iiif.auth.v2;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "@context", "type", "profile", "heading", "note", "messageId" })
public class AuthAccessTokenError2 implements IAuthMessage {

    public enum Profile {

        EXPIRED_ASPECT("expiredAspect"),
        INVALID_ASPECT("invalidAspect"),
        INVALID_ORIGIN("invalidOrigin"),
        INVALID_REQUEST("invalidRequest"),
        MISSING_ASPECT("missingAspect"),
        UNAVALIABLE("unavailable");

        private final String name;

        /**
         * Constructor.
         * 
         * @param name
         */
        private Profile(String name) {
            this.name = name;
        }

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }
    }

    private static final String TYPE = "AuthAccessTokenError2";

    private final Profile profile;

    private final String messageId;

    private final Map<String, String> heading = new HashMap<>();

    private final Map<String, String> note = new HashMap<>();

    /**
     * 
     * @param messageId
     * @param profile
     */
    public AuthAccessTokenError2(String messageId, Profile profile) {
        this.messageId = messageId;
        this.profile = profile;
    }

    @JsonProperty("@context")
    public String getContext() {
        return CONTEXT;
    }

    @JsonProperty("type")
    public String getType() {
        return TYPE;
    }

    @JsonProperty("profile")
    public String getProfile() {
        return profile.getName();
    }

    @JsonProperty("messageId")
    public String getMessageId() {
        return messageId;
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

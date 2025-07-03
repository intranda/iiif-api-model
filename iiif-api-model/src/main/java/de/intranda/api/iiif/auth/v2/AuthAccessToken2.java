package de.intranda.api.iiif.auth.v2;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "@context", "type", "messageId", "accessToken", "expiresIn" })
public class AuthAccessToken2 implements IAuthMessage, Serializable {

    private static final long serialVersionUID = -6431032660504903788L;

    private static final String TYPE = "AuthAccessToken2";

    private final String messageId;

    private final String accessToken;

    private final int expiresIn;

    private final Instant expiresAt;

    private final Map<String, Boolean> permissions = new HashMap<>();

    /**
     * 
     * @param messageId
     * @param expiresIn
     */
    public AuthAccessToken2(String messageId, int expiresIn) {
        this.messageId = messageId;
        this.accessToken = UUID.randomUUID().toString();
        this.expiresIn = expiresIn;
        this.expiresAt = Instant.now().plusSeconds(expiresIn);
    }

    @JsonIgnore
    public boolean isExpired() {
        return Instant.now().isAfter(expiresAt);
    }

    public void addPermission(String resourceId, boolean allowed) {
        permissions.put(resourceId, allowed);
    }

    public Boolean hasPermission(String resourceId) {
        return permissions.getOrDefault(resourceId, null);
    }

    @JsonProperty("@context")
    public String getContext() {
        return CONTEXT;
    }

    @JsonProperty("type")
    public String getType() {
        return TYPE;
    }

    /**
     * @return the messageId
     */
    @JsonProperty("messageId")
    public String getMessageId() {
        return messageId;
    }

    /**
     * @return the accessToken
     */
    @JsonProperty("accessToken")
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * @return the expiresIn
     */
    @JsonProperty("expiresIn")
    public int getExpiresIn() {
        return expiresIn;
    }

    /**
     * 
     * @return Immutable version of permissions
     */
    @JsonIgnore
    public Map<String, Boolean> getPermissions() {
        return Collections.unmodifiableMap(permissions);
    }

}

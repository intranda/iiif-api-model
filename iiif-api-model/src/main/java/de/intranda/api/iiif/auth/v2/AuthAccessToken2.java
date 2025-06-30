package de.intranda.api.iiif.auth.v2;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthAccessToken2 implements IAuthMessage {

    private static final String TYPE = "AuthAccessToken2";

    private final String messageId;

    private final String accessToken;

    private int expiresIn;

    @JsonProperty("@context")
    public String getContext() {
        return CONTEXT;
    }

    @JsonProperty("type")
    public String getType() {
        return TYPE;
    }

    public AuthAccessToken2(String messageId, String accessToken) {
        this.messageId = messageId;
        this.accessToken = accessToken;
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
     * @param expiresIn the expiresIn to set
     * @return this
     */
    public AuthAccessToken2 setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }
}

package de.intranda.api.iiif.auth.v2;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthAccessService2 extends AbstractAuthService2 {

    public enum Profile {
        ACTIVE,
        EXTERNAL,
        KIOSK;
    }

    public static final String TYPE = "AuthAccessService2";

    private final URI id;

    private final Profile profile;

    private final AuthAccessTokenService2 tokenService;

    private final AuthLogoutService2 logoutService;

    /**
     * 
     * @param id
     * @param profile
     * @param tokenService
     * @param logoutService
     */
    public AuthAccessService2(URI id, Profile profile, AuthAccessTokenService2 tokenService, AuthLogoutService2 logoutService) {
        if (profile == null) {
            throw new IllegalArgumentException("profile may not be null");
        }
        if (tokenService == null) {
            throw new IllegalArgumentException("tokenService may not be null");
        }

        this.id = id;
        this.profile = profile;
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

    /**
     * SHOULD NOT be present if profile="external"
     * 
     * @return String
     */
    @JsonProperty("profile")
    public String getProfile() {
        return profile.name().toLowerCase();
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
}

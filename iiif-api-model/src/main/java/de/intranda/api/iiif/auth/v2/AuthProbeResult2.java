package de.intranda.api.iiif.auth.v2;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthProbeResult2 {
    
    private static final String CONTEXT = "http://iiif.io/api/auth/2/context.json";
    
    private static final String TYPE= "AuthProbeResult2";

    private String heading;
    
    private String note;
    
    private int status;
    
    @JsonProperty("@type")
    public String getType() {
        return TYPE;
    }
    
    
}

package de.intranda.api.annotation;

import java.net.URI;
import java.net.URISyntaxException;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.intranda.api.serializer.JsonObjectSerializer;

public class JSONResource  implements IResource {

    private static final Logger logger = LoggerFactory.getLogger(JSONResource.class);
    
    private final JSONObject json;
    
    public JSONResource(String source) {
        this.json = new JSONObject(source);
    }
    
    public JSONResource(JSONObject source) {
        this.json = source;
    }
    
    @JsonValue
    @JsonSerialize(using=JsonObjectSerializer.class)
    public JSONObject getJSON() {
        return this.json;
    }
    
    @Override
    public URI getId() {
        try {            
            if(this.json.has("id")) {
                return new URI(this.json.getString("id"));
            } else if(this.json.has("@id")) {
                return new URI(this.json.getString("@id"));
            } else if(this.json.has("source")) {
                return new URI(this.json.getString("source"));
            } else {
                logger.warn("No id property found in " + this.json);
                return null;
            }
        } catch(URISyntaxException e) {
            logger.error("error getting id from " + this.json);
            return null;
        }
    }

}

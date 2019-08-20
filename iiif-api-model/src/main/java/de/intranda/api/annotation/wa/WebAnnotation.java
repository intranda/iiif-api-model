package de.intranda.api.annotation.wa;

import java.net.URI;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import de.intranda.api.annotation.AbstractAnnotation;
import de.intranda.api.annotation.IAgent;

@JsonPropertyOrder({ "@id", "@type", "motivation", "body", "target" })
public class WebAnnotation extends AbstractAnnotation {

    public final static String TYPE = "Annotation";
    protected static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    private Agent creator;
    private Agent generator;
    private Date created;
    private Date modified;

    public WebAnnotation() {
        super(null);
    }
    
    /**
     * @param id
     */
    public WebAnnotation(URI id) {
        super(id);
    }

    @JsonProperty("type")
    public String getType() {
        return TYPE;
    }

    @Override
    public Agent getCreator() {
        return creator;
    }

    @Override
    public Agent getGenerator() {
        return generator;
    }

    @Override
    @JsonFormat(pattern = DATETIME_FORMAT)
    public Date getCreated() {
        return created;
    }

    @Override
    @JsonFormat(pattern = DATETIME_FORMAT)
    public Date getModified() {
        return modified;
    }
}

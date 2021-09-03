package de.intranda.api.annotation.wa;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import de.intranda.api.annotation.AbstractAnnotation;

@JsonPropertyOrder({ "id", "type", "motivation", "body", "target" })
@JsonIgnoreProperties(ignoreUnknown=true)
public class WebAnnotation extends AbstractAnnotation {

    public final static String TYPE = "Annotation";
    protected static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    private Agent creator;
    private Agent generator;
    private LocalDateTime created;
    private LocalDateTime modified;

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
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_FORMAT)
    public LocalDateTime getCreated() {
        return created;
    }

    @Override
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_FORMAT)
    public LocalDateTime getModified() {
        return modified;
    }
    
    public void setCreator(Agent creator) {
        this.creator = creator;
    }
    
    public void setGenerator(Agent generator) {
        this.generator = generator;
    }
    
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
    
    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj.getClass().equals(this.getClass())) {
            WebAnnotation other = (WebAnnotation)obj;
            return super.equals(other)
                    && Objects.equals(this.getCreator(), other.getCreator())
                    && Objects.equals(this.getGenerator(), other.getGenerator());
        } else {
            return false;
        }
    }
    
    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return super.toString();
        }
    }
}

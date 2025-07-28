package de.intranda.api.annotation.wa;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import de.intranda.api.annotation.ITypedResource;
import de.intranda.api.deserializer.ResourceDeserializer;
import de.intranda.api.services.Service;

@JsonInclude(Include.NON_NULL)
@JsonDeserialize(using = ResourceDeserializer.class)
public class TypedResource implements ITypedResource {

    private final String type;
    private final String format;
    private final URI id;
    private final String profile;
    private final List<Service> service = new ArrayList<>();

    public TypedResource(URI id, String type, String format, String profile) {
        this.id = id;
        this.type = type;
        this.format = format;
        this.profile = profile;
    }

    public TypedResource(URI id, String type, String format) {
        this(id, type, format, null);
    }

    public TypedResource(URI id, String type) {
        this(id, type, null, null);
    }

    public TypedResource() {
        this(null, null, null, null);
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the format
     */
    public String getFormat() {
        return format;
    }

    @Override
    public URI getId() {
        return id;
    }

    public String getProfile() {
        return profile;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass().equals(this.getClass())) {
            TypedResource other = (TypedResource) obj;
            return this.getId().equals(other.getId())
                    && Objects.equals(this.getType(), other.getType())
                    && Objects.equals(this.getFormat(), other.getFormat());
        }
        return false;
    }

    @Override
    public String toString() {
        return asJson();
    }

    public String asJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return super.toString();
        }
    }

    /**
     * @return the service
     */
    @JsonProperty("service")
    @JsonInclude(Include.NON_EMPTY)
    public List<Service> getService() {
        return service;
    }

    public void addService(Service service) {
        if (service != null) {
            this.service.add(service);
        }
    }
}

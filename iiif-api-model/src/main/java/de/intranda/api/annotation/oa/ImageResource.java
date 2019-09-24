package de.intranda.api.annotation.oa;

import java.net.URI;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.intranda.api.iiif.image.ImageInformation;
import de.intranda.api.serializer.ImageInformationSerializer;

@JsonInclude(Include.NON_ABSENT)
public class ImageResource extends TypedResource {

    Optional<ImageInformation> service;
    
    public ImageResource(URI id, String format, Optional<ImageInformation> service) {
        super(id, "dcTypes:Image", format);
        this.service = service;
    }

    @JsonSerialize(using = ImageInformationSerializer.class)
    public ImageInformation getService() {
        return service.orElse(null);
    }
    
    public Integer getWidth() {
        return service.map(info -> info.getWidth()).orElse(null);
    }
    
    public Integer getHeight() {
        return service.map(info -> info.getHeight()).orElse(null);
    }
    
}

package de.intranda.api.annotation.wa;

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
        super(id, "type", format);
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
    
    @Override
    public boolean equals(Object obj) {
        if(obj.getClass().equals(this.getClass())) {
            return this.getId().equals(((ImageResource) obj).getId());
        } else {
           return false;
        }
    }
    
    @Override
    public String toString() {
       String ret = this.getId().toString();
       if(service.isPresent()) {
           ret += "\n\t" + service.get().toString();
       }
       return ret;
    }
}

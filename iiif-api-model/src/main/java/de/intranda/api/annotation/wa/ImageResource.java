package de.intranda.api.annotation.wa;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.intranda.api.annotation.IImageResource;
import de.intranda.api.iiif.image.ImageInformation;
import de.intranda.api.iiif.presentation.enums.Format;
import de.intranda.api.serializer.ImageInformationSerializer;

@JsonInclude(Include.NON_ABSENT)
public class ImageResource extends TypedResource implements IImageResource{

    private Optional<ImageInformation> service;
    
    public ImageResource(URI id, String format, Optional<ImageInformation> service) {
        super(id, "Image", format);
        this.service = service;
    }

    public ImageResource(URI id, Format format, ImageInformation info) {
		this(id, format.getLabel(), Optional.ofNullable(info));
	}
    
    public ImageResource(URI id, String format) {
        this(id, format, Optional.empty());
    }

	@JsonSerialize(contentUsing = ImageInformationSerializer.class)
	@JsonProperty("service")
    public List<ImageInformation> getServices() {
        return service.map(Arrays::asList).orElse(null);
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

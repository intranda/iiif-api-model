package de.intranda.api.annotation.wa;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.intranda.api.annotation.IImageResource;
import de.intranda.api.iiif.IIIFUrlResolver;
import de.intranda.api.iiif.image.ImageInformation;
import de.intranda.api.iiif.image.v3.ImageInformation3;
import de.intranda.api.iiif.presentation.enums.Format;
import de.intranda.api.serializer.ImageInformationSerializer;

@JsonInclude(Include.NON_ABSENT)
public class ImageResource extends TypedResource implements IImageResource {

    private final Optional<ImageInformation> service;
    private final Integer width;
    private final Integer height;

    public ImageResource(URI id, String format, Integer width, Integer height, Optional<ImageInformation> service) {
        super(id, "Image", format);
        this.service = service;
        this.width = width;
        this.height = height;
    }

    public ImageResource(URI id, String format, Optional<ImageInformation> service) {
        this(id, format, service.map(info -> info.getWidth()).filter(w -> w > 0).orElse(null),
                service.map(info -> info.getHeight()).filter(h -> h > 0).orElse(null), service);

    }

    public ImageResource(URI id, Format format, ImageInformation info) {
        this(id, format.getLabel(), Optional.ofNullable(info));
    }

    public ImageResource(URI id, String format) {
        this(id, format, Optional.empty());
    }

    public ImageResource(URI id) {
        this(id, null, Optional.empty());
    }

    /**
     * Constructor for IIIF image resources. Creates a thumbnail url with the given width and height and a ImageInformation service
     * 
     * @param baseId
     * @param width
     * @param height
     */
    public ImageResource(String baseId, int width, int height) {
        this(baseId, Format.IMAGE_JPEG, width, height);
    }

    /**
     * Constructor for IIIF image resources. Creates a thumbnail url with the given width and height and a ImageInformation service Allows specifying
     * a format other than jpg for delivery
     * 
     * @param baseId
     * @param width
     * @param height
     * @param format
     */
    public ImageResource(String baseId, Format outputFormat, int width, int height) {
        this(URI.create(IIIFUrlResolver.getIIIFImageUrl(baseId, "full", getSizeParameter(width, height), "0", "default", "jpg")), outputFormat,
                new ImageInformation3(baseId));
    }

    private static String getSizeParameter(int width, int height) {
        if (width == 0 && height == 0) {
            return "max";
        } else if (height == 0) {
            return width + ",";
        } else if (width == 0) {
            return "," + height;
        } else {
            return "!" + width + "," + height;
        }
    }

    @JsonSerialize(contentUsing = ImageInformationSerializer.class)
    @JsonProperty("service")
    public List<ImageInformation> getServices() {
        return service.map(Arrays::asList).orElse(null);
    }

    public Integer getWidth() {
        return this.width;
    }

    public Integer getHeight() {
        return this.height;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass().equals(this.getClass())) {
            return this.getId().equals(((ImageResource) obj).getId());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String ret = this.getId().toString();
        if (service.isPresent()) {
            ret += "\n\t" + service.get().toString();
        }
        return ret;
    }

}

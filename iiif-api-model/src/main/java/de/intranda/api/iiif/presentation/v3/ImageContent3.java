package de.intranda.api.iiif.presentation.v3;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.intranda.api.PropertyList;
import de.intranda.api.iiif.IIIFUrlResolver;
import de.intranda.api.iiif.image.ImageInformation;
import de.intranda.api.iiif.image.v3.ImageInformation3;
import de.intranda.api.iiif.presentation.content.ImageContent;
import de.intranda.api.iiif.presentation.enums.Format;

public class ImageContent3 extends ImageContent {
	
	private static final String TYPE ="Image";
	
	public ImageContent3(URI id) {
		super(id);
		this.format = Format.IMAGE_JPEG;
	}
    
    /**
     * Constructor for IIIF image resources. Creates a thumbnail url with the given width and height and
     * a ImageInformation service
     * 
     * @param baseId
     * @param width
     * @param height
     */
    public ImageContent3(String baseId, int width, int height) {
    	this(baseId, Format.IMAGE_JPEG, width, height);
    }

	
    /**
     * Constructor for IIIF image resources. Creates a thumbnail url with the given width and height and
     * a ImageInformation service
     * Allows specifying a format other than jpg for delivery
     * 
     * @param baseId
     * @param width
     * @param height
     * @param format
     */
    public ImageContent3(String baseId, Format outputFormat, int width, int height) {
    	super(URI.create(IIIFUrlResolver.getIIIFImageUrl(baseId, "full", getSizeParameter(width, height), "0", "default", "jpg")), new ImageInformation3(baseId));
    	this.format = outputFormat;
    }
	
    private static String getSizeParameter(int width, int height) {
		if(width == 0 && height == 0) {
			return "max";
		} else if(height == 0) {
			return width + ",";
		} else if(width == 0) {
			return "," + height;
		} else {
			return "!" + width + "," + height;
		}
	}
    
    @Override
    @JsonProperty("type")
    public String getType() {
    	return TYPE;
    }
    
    @Override
    @JsonProperty("id")
    public URI getId() {
    	return super.getId();
    }
    
    @Override
    public List<ImageInformation> getService() {
        return Arrays.asList(service);
    }
    
    @Override
    public String getFormat() {
    	return super.getFormat();
    }

}

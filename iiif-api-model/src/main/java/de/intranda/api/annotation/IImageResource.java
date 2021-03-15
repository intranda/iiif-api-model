package de.intranda.api.annotation;

import java.util.List;

import de.intranda.api.iiif.image.ImageInformation;

public interface IImageResource extends ITypedResource {

	public Integer getWidth();
	public Integer getHeight();
	public List<ImageInformation> getService();
}

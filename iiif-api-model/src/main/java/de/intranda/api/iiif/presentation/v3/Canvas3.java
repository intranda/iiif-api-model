/**
 * This file is part of the Goobi viewer - a content presentation and management application for digitized objects.
 *
 * Visit these websites for more information.
 *          - http://www.intranda.com
 *          - http://digiverso.com
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package de.intranda.api.iiif.presentation.v3;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import de.intranda.api.annotation.IResource;
import de.intranda.api.annotation.ITypedResource;
import de.intranda.api.annotation.SimpleResource;
import de.intranda.api.annotation.wa.Motivation;
import de.intranda.api.annotation.wa.TypedResource;
import de.intranda.api.annotation.wa.WebAnnotation;
import de.intranda.api.annotation.wa.collection.AnnotationPage;

/**
 * Canvas overwrites the items property behavior such that only one item may exist, which is an AnnotationPage
 * containing any images/audio/video/3d-objects associated with the canvas. Any other content is to be included in the annotations property,
 * either with the "supplementing" motivation for resources describing the canvas (ocr, pdf) or any other motivation suc as "commenting"
 * for resources "about" the canvas, such as comments and crowdsourcing annotations
 * 
 * @author Florian Alpers
 *
 */
@JsonPropertyOrder({"@context", "id", "type, label", "width", "height", "duration", "items", "annotations"})
public class Canvas3 extends AbstractPresentationModelElement3 implements IPresentationModelElement3 {

    private static final String TYPE = "Canvas";
    private static final String IMAGE_LIST_PATH = "media";

    private Integer width;
    private Integer height;
    private Float duration;
	private final List<AnnotationPage> items = new ArrayList<AnnotationPage>();

    public Canvas3() {
    	this("");
    }

    public Canvas3(String uri) {
        this(URI.create(uri));
    }

    /**
     * @param id
     */
    public Canvas3(URI id) {
        super(id);

    }

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.AbstractPresentationModelElement#getType()
     */
    @Override
    public String getType() {
        return TYPE;
    }

    /**
     * @return the width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(Integer height) {
        this.height = height;
    }
    
    public Float getDuration() {
		return duration;
	}
    
    public void setDuration(Float duration) {
		this.duration = duration;
	}

    /**
     * Add a resource body to the list of media items. The reosurce will be wrapped in an Annotation targeting the canvas itself
     * @param media	The annotation body containing the media reference
     * @param itemUrl URI pointing to the AnnotationPage containing the media reference. If the AnnotationPage already exists within the 
     * cavnvas the media annotation will be added to it. Otherwise a new AnnotationPage will be created
     */
    public void addMedia(URI itemUrl, ITypedResource media) {
    	AnnotationPage page = this.items.stream().filter(p -> p.getId().equals(itemUrl)).findAny().orElseGet(() -> {
    		AnnotationPage images = new AnnotationPage(itemUrl, false);
    		this.items.add(images);
    		return images;
    	});
    	URI annotationId = UriBuilder.fromUri(page.getId())
    			.path(Integer.toString(page.getItems().size() + 1) + "/")
    			.build();
    	WebAnnotation annotation = new WebAnnotation(annotationId);
    	annotation.setBody(media);
    	annotation.setTarget(new SimpleResource(this.getId()));
    	annotation.setMotivation(Motivation.PAINTING);
    	page.getItems().add(annotation);
    }

    
    @Override
    public List<AnnotationPage> getItems() {
		return items;
	}

    
}

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
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.intranda.api.annotation.IResource;
import de.intranda.api.annotation.wa.collection.AnnotationCollection;
import de.intranda.api.iiif.presentation.IPresentationModelElement;
import de.intranda.api.serializer.ContentLinkSerializer;
import de.intranda.api.serializer.LinkedResourceSerializer;
import de.intranda.api.serializer.URLOnlySerializer;

/**
 * @author Florian Alpers
 *
 */
@JsonInclude(Include.NON_EMPTY)
public class Range3 extends AbstractPresentationModelElement3 implements IPresentationModelElement3 {

    private static final String TYPE = "Range";

    private final List<IPresentationModelElement3> items = new ArrayList<>();
    private Canvas3 start = null;
    @JsonIgnore
    private boolean useMembers = false;
    

    public Range3() {

    }

    public Range3(String id) throws URISyntaxException {
        super(new URI(id));
    }

    /**
     * @param id
     */
    public Range3(URI id) {
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
     * @return the startCanvas
     */
    @JsonProperty("start")
    @JsonSerialize(using = LinkedResourceSerializer.class)
    public Canvas3 getStart() {
        return start;
    }

    /**
     * @param startCanvas the startCanvas to set
     */
    public void setStart(Canvas3 startCanvas) {
        this.start = startCanvas;
    }

	@Override
	public List<IPresentationModelElement3> getItems() {
		return this.items;
	}
	
	public void addItem(IPresentationModelElement3 item) {
		this.items.add(item);
	}


}

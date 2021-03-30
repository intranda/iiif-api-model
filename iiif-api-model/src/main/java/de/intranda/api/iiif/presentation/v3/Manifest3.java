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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.intranda.api.annotation.IResource;
import de.intranda.api.iiif.presentation.enums.ViewingDirection;
import de.intranda.api.serializer.LinkedResourceSerializer;
import de.intranda.api.serializer.URLOnlySerializer;

/**
 * @author Florian Alpers
 *
 */
public class Manifest3 extends AbstractPresentationModelElement3 implements IPresentationModelElement3 {

    public static final String TYPE = "Manifest";
    
    private Canvas3 start = null;
    private final List<Canvas3> items = new ArrayList<>();
    private final List<Range3> structures = new ArrayList<>();
    private ViewingDirection viewingDirection = null;

    public Manifest3() {
        super();
    }

    /**
     * @param id
     */
    public Manifest3(URI id) {
        super(id);
    }

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.AbstractPresentationModelElement#getType()
     */
    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public List<Canvas3> getItems() {
    	return items;
    }
    
	public void addItem(Canvas3 item) {
		this.items.add(item);
	}
    
    
    public List<Range3> getStructures() {
		return structures;
	}
    
    public void addRange(Range3 range) {
    	this.structures.add(range);
    }

    public void setViewingDirection(ViewingDirection viewingDirection) {
		this.viewingDirection = viewingDirection;
	}
    
    public ViewingDirection getViewingDirection() {
		return viewingDirection;
	}
    
    /**
     * @return the startCanvas
     */
    @JsonSerialize(using = LinkedResourceSerializer.class)
    @JsonProperty("start")
    public Canvas3 getStart() {
        return start;
    }

    /**
     * @param startCanvas the startCanvas to set
     */
    public void setStart(Canvas3 startCanvas) {
        this.start = startCanvas;
    }


}

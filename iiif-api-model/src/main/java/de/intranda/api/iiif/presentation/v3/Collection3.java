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
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import de.intranda.api.annotation.IResource;
import de.intranda.api.iiif.presentation.AbstractPresentationModelElement;
import de.intranda.api.iiif.presentation.IPresentationModelElement;

/**
 * @author Florian Alpers
 *
 */
@JsonInclude(Include.NON_EMPTY)
public class Collection3 extends AbstractPresentationModelElement3 implements IPresentationModelElement3 {

    public static final String TYPE = "Collection";
    @JsonIgnore
    private final String internalName;
    private final List<IPresentationModelElement3> items = new ArrayList<>();

    public Collection3() {
        super(null);
        this.internalName = "";
    }
    
    /**
     * @param id
     */
    public Collection3(URI id, String name) {
        super(id);
        this.internalName = name;
    }  
    
    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.AbstractPresentationModelElement#getType()
     */
    @Override
    public String getType() {
        return TYPE;
    }

    public String getInternalName() {
        return internalName;
    }
    
    public List<IPresentationModelElement3> getItems() {
		return items;
	}
    
    public void addItem(IPresentationModelElement3 item) {
    	this.items.add(item);
    }

    public Optional<Collection3> getCollectionByInternalName(String name) {
        if(name.equals(this.getInternalName())) {
            return Optional.of(this);
        } else {
        	return getItems().stream()
        	.filter(item -> item instanceof Collection3)
        	.map(item -> (Collection3)item)
        	.map(item -> item.getCollectionByInternalName(name))
        	.filter(o -> o.isPresent())
        	.map(o -> o.get())
        	.findFirst();
        }
    }


}

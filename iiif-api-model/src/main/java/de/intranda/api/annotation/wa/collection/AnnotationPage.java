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
package de.intranda.api.annotation.wa.collection;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.intranda.api.annotation.IAnnotation;
import de.intranda.api.annotation.IAnnotationCollection;
import de.intranda.api.annotation.IResource;
import de.intranda.api.deserializer.AnnotationDeserializer;
import de.intranda.api.deserializer.AnnotationListResourceDeserializer;
import de.intranda.api.serializer.URLOnlySerializer;

/**
 * A Page in a resource list which contains a list of paged items and a reference to the next and previous page and the containing list
 * 
 * @author Florian Alpers
 *
 */
@JsonPropertyOrder({"@context", "id", "type", "prev", "next", "partOf", "items"})
@JsonInclude(Include.NON_EMPTY)
public class AnnotationPage implements IAnnotationCollection{

    private final static String TYPE = "AnnotationPage";
    private static final String CONTEXT = "http://www.w3.org/ns/anno.jsonld";
    
    private final URI id;
    private AnnotationCollection partOf;
    private AnnotationPage prev;
    private AnnotationPage next;
    private Integer startIndex = null;
    private List<IAnnotation> items = new ArrayList<>();
    private final String context;
    
    public AnnotationPage() {
        this.id = null;
        this.context = CONTEXT;
    }
    
    /**
     * Constructs a collection page from the URI to the resource providing this object
     */
    public AnnotationPage(URI id) {
        this.id = id;
        this.context = CONTEXT; 
    }

    public AnnotationPage(URI id, boolean includeContext) {
    	this.id = id;
        this.context = includeContext ? CONTEXT : ""; 
	}

	/**
     * Reference to the containing collection
     * 
     * @return the containing collection
     */
    public AnnotationCollection getPartOf() {
        return partOf;
    }

    /**
     * Set the reference to the containing collection
     * 
     * @param partOf the containing collection
     */
    @JsonSerialize(using = URLOnlySerializer.class)
    public void setPartOf(AnnotationCollection partOf) {
        this.partOf = partOf;
    }

    /**
     * Get the previous collection page
     * 
     * @return the previous collection page. May be null if no previous page exists
     */
    public AnnotationPage getPrev() {
        return prev;
    }

    /**
     * Set the previous collection page
     * 
     * @param prev the page to set
     */
    public void setPrev(AnnotationPage prev) {
        this.prev = prev;
    }

    /**
     * Get the succeeding collection page
     * 
     * @return the succeeding collection page. May be null if no succeeding page exists
     */
    public AnnotationPage getNext() {
        return next;
    }

    /**
     * Set the succeeding collection page
     * 
     * @param next the page to set
     */
    public void setNext(AnnotationPage next) {
        this.next = next;
    }

    /**
     * Get the list of items on this page
     * 
     * @return the items
     */
    @JsonDeserialize(using=AnnotationListResourceDeserializer.class)
    public List<IAnnotation> getItems() {
        return items;
    }

    /**
     * Set the list of items for this page
     * 
     * @param orderedItems the items to set
     */
    public void setItems(List<IAnnotation> items) {
        this.items = items;
    }


	public void addItem(IAnnotation annotation) {
		this.items.add(annotation);
	}
    
    /**
     * @return the context
     */
    @JsonProperty("@context")    
    public String getContext() {
        return this.context;
    }


    /**
     * The type of this resource. Always "OrderedCollectionPage"
     * 
     * @return the type
     */
    public String getType() {
        return TYPE;
    }

    /**
     * @return the URI to the resource providing this object
     */
    public URI getId() {
        return id;
    }
    
    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }
    
    public Integer getStartIndex() {
        return startIndex;
    }

    
    
}

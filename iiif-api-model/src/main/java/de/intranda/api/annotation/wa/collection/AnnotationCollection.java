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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.intranda.api.annotation.IAnnotationCollection;
import de.intranda.api.annotation.IResource;
import de.intranda.api.serializer.WebAnnotationMetadataValueSerializer;
import de.intranda.api.serializer.URLOnlySerializer;
import de.intranda.metadata.multilanguage.IMetadataValue;

import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * A collection of items, divided into pages
 * 
 * @author Florian Alpers
 *
 */
@JsonPropertyOrder({"@context", "id", "type", "total", "first", "last"})
@JsonInclude(Include.NON_NULL)
public class AnnotationCollection implements IAnnotationCollection{

    private final static String TYPE = "AnnotationCollection";
    private static final String CONTEXT = "http://www.w3.org/ns/anno.jsonld";
    
    private final URI id;
    private long totalItems;
    private IMetadataValue label;
    private AnnotationPage first;
    private AnnotationPage last;
    
    public AnnotationCollection() {
        this.id = null;
     }
    
    public AnnotationCollection(String id) {
        this.id = URI.create(id);
     }
    
    /**
     * Constructs a collection from the URI to the resource providing this object
     */
    public AnnotationCollection(URI id) {
       this.id = id;
    }
    
    /**
     * @return the URI to the resource providing this object
     */
    public URI getId() {
        return id;
    }
    
    /**
     * Get the total number of items contained in the collection
     * 
     * @return the total number of items contained in the collection
     */
    @JsonProperty("total")
    public long getTotalItems() {
        return totalItems;
    }
    /**
     * Set the total number of items contained in the collection
     * 
     * @param totalItems the totalItems to set
     */
    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }
    /**
     * Returns a reference to the first page of this collection
     * 
     * @return a reference to the first page of this collection
     */
    public AnnotationPage getFirst() {
        return first;
    }
    /**
     *Sets the first page of this collection
     * 
     * @param first the first page to set
     */
    public void setFirst(AnnotationPage first) {
        this.first = first;
    }
    
    /**
     * Returns a reference to the last page of this collection
     * 
     * @return a reference to the last page of this collection
     */
    public AnnotationPage getLast() {
        return last;
    }
    /**
     *Sets the last page of this collection
     * 
     * @param last the last page to set
     */
    public void setLast(AnnotationPage last) {
        this.last = last;
    }
    /**
     * The JSON-LD context of the resource
     * 
     * @return The JSON-LD context of the resource
     */
    @JsonProperty("@context")    
    public String getContext() {
        return CONTEXT;
    }

    
    /**
     * The type of this resource. Always "OrderedCollection"
     * 
     * @return the type
     */
    public String getType() {
        return TYPE;
    }
    
    public IMetadataValue getLabel() {
        return label;
    }
    
    @JsonSerialize(using = WebAnnotationMetadataValueSerializer.class)
    public void setLabel(IMetadataValue label) {
        this.label = label;
    }
    
    
}

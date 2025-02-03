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
import java.util.List;

import jakarta.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import de.intranda.api.annotation.IAnnotation;
import de.intranda.api.annotation.wa.collection.AnnotationCollection;
import de.intranda.api.annotation.wa.collection.AnnotationPage;
import de.intranda.metadata.multilanguage.IMetadataValue;

/**
 * @author florian
 *
 */
@JsonInclude(Include.NON_NULL)
public class AnnotationCollectionBuilder {

    private int itemsPerPage = 10;
    private IMetadataValue label = null;
    private final long totalItemCount;
    private final URI baseURI;
    private String pageUrlPrefix = "";

    public AnnotationCollectionBuilder(URI baseURI, long totalItemCount) {
        this.baseURI = baseURI;
        this.totalItemCount = totalItemCount;
    }
    
    public AnnotationCollection buildCollection() {
        
        AnnotationCollection collection = new AnnotationCollection(baseURI);
        
        collection.setTotalItems(totalItemCount);
        collection.setLabel(label);
        if(totalItemCount > 0) {            
            collection.setFirst(new AnnotationPage(getPageURI(baseURI.toString(), 1)));
            collection.setLast(new AnnotationPage(getPageURI(baseURI.toString(), getLastPageNo())));
        }

        return collection;
    }
    
    public AnnotationPage buildPage(List<IAnnotation> items, int pageNo) {
        
        
        AnnotationPage page = new AnnotationPage(getPageURI(baseURI.toString(), pageNo));
        AnnotationCollection collection = new AnnotationCollection(baseURI);
        page.setPartOf(collection);
        
        if(pageNo < getLastPageNo()) {
            AnnotationPage next = new AnnotationPage(getPageURI(baseURI.toString(), pageNo + 1));
            page.setNext(next);
        }
        if(pageNo > 1) {
            AnnotationPage prev = new AnnotationPage(getPageURI(baseURI.toString(), pageNo - 1));
            page.setPrev(prev);
        }
        
        page.setItems(items);
        
        page.setStartIndex(getStartIndex(pageNo));
        
        return page;
    }


    private int getStartIndex(int pageNo) {
        return (pageNo-1) * itemsPerPage;
    }
    
    /**
     * @param first
     * @param totalItemCount
     * @return
     */
    public int getPageNo(int first) {
    	if(itemsPerPage == 0)  {
    		return 1;
    	} else {    		
    		return ((first-1)/itemsPerPage)+1;
    	}
    }

    /**
     * @param totalItemCount
     * @return
     */
    public int getLastPageNo() {
    	if(itemsPerPage == 0)  {
    		return 1;
    	} else {    		
    		return (int)((totalItemCount-1)/itemsPerPage) + 1;
    	}
    }
    
    /**
     * @param baseURI
     * @return
     */
    private URI getPageURI(String baseURI, int order) {
        URI id = UriBuilder.fromUri(baseURI).path(pageUrlPrefix + Integer.toString(order)).build();

        return id;
    }
    
    public AnnotationCollectionBuilder setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
        return this;
    }
    
    public AnnotationCollectionBuilder setLabel(IMetadataValue label) {
        this.label = label;
        return this;
    }
    
    public AnnotationCollectionBuilder setPageUrlPrefix(String pageUrlPrefix) {
        this.pageUrlPrefix = pageUrlPrefix;
        return this;
    }

}

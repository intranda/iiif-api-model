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

import de.intranda.api.annotation.IAnnotation;
import de.intranda.api.annotation.wa.collection.AnnotationCollection;
import de.intranda.api.annotation.wa.collection.AnnotationPage;
import de.intranda.metadata.multilanguage.IMetadataValue;

/**
 * @author florian
 *
 */
public class AnnotationCollectionBuilder {
    
    private int itemsPerPage = 10;
    
    public AnnotationCollectionBuilder setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
        return this;
    }
    
    public AnnotationCollection buildCollection(int totalItemCount, URI baseURI, IMetadataValue label) {
        
        AnnotationCollection collection = new AnnotationCollection(baseURI);
        
        collection.setTotalItems(totalItemCount);
        collection.setLabel(label);
        collection.setFirst(new AnnotationPage(getFirstPageURI(baseURI.toString())));
        if(totalItemCount > 0) {            
            collection.setLast(new AnnotationPage(getLastPageURI(baseURI.toString(), totalItemCount)));
        }

        return collection;
    }
    
    public AnnotationPage buildPage(List<IAnnotation> items, int first, int totalItemCount, URI baseURI, IMetadataValue label) {
        
        int pageNo = getPageNo(first);
        
        AnnotationPage page = new AnnotationPage(getPageURI(baseURI.toString(), pageNo));
        
        AnnotationCollection collection = new AnnotationCollection(baseURI);
        page.setPartOf(collection);
        
        if(pageNo < getLastPageNo(totalItemCount)) {
            AnnotationPage next = new AnnotationPage(getPageURI(baseURI.toString(), pageNo + 1));
            page.setNext(next);
        }
        if(pageNo > 1) {
            AnnotationPage prev = new AnnotationPage(getPageURI(baseURI.toString(), pageNo - 1));
            page.setPrev(prev);
        }
        
        page.setItems(items);
        
        return page;
    }


    /**
     * @param first
     * @param totalItemCount
     * @return
     */
    private int getPageNo(int first) {
        return (first-1/itemsPerPage)+1;
    }

    /**
     * @param totalItemCount
     * @return
     */
    private int getLastPageNo(int totalItemCount) {
        return (totalItemCount-1/itemsPerPage) + 1;
    }

    /**
     * @param baseURI
     * @return
     */
    private URI getLastPageURI(String baseURI, int totalItemCount) {
  
        if(!baseURI.endsWith("/")) {
            baseURI = baseURI + "/";
        }
        URI id = URI.create(baseURI + getLastPageNo(totalItemCount)); 
        return id;
    }
    
    /**
     * @param baseURI
     * @return
     */
    private URI getFirstPageURI(String baseURI) {
        if(!baseURI.endsWith("/")) {
            baseURI = baseURI + "/";
        }
        URI id = URI.create(baseURI + "1"); 
        return id;
    }
    
    /**
     * @param baseURI
     * @return
     */
    private URI getPageURI(String baseURI, int order) {
        if(!baseURI.endsWith("/")) {
            baseURI = baseURI + "/";
        }
        URI id = URI.create(baseURI + order); 
        return id;
    }

}

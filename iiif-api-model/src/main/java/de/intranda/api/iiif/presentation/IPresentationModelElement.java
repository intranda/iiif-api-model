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
package de.intranda.api.iiif.presentation;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.intranda.api.PropertyList;
import de.intranda.api.annotation.IImageResource;
import de.intranda.api.annotation.ILabeledResource;
import de.intranda.api.annotation.IResource;
import de.intranda.api.iiif.presentation.content.LinkingContent;
import de.intranda.api.iiif.presentation.enums.ViewingHint;
import de.intranda.api.services.Service;
import de.intranda.metadata.multilanguage.IMetadataValue;
import de.intranda.metadata.multilanguage.Metadata;

/**
 * Version agnostic base interface for IIIF Presentation objects
 * 
 * @author florian
 *
 */
@JsonInclude(Include.NON_EMPTY)
public interface IPresentationModelElement extends IResource {
	
	@JsonProperty("@context")
	List<String> getContext();
	
    URI getId();
	
    String getType();

    /**
     * @return the label
     */
    IMetadataValue getLabel();

    /**
     * @return the description
     */
    IMetadataValue getDescription();

    /**
     * @return the metadata
     */
    List<Metadata> getMetadata();

    /**
     * @return the thumbnail
     */
    List<? extends IImageResource> getThumbnails();


    /**
     * @return the license
     */
    List<URI> getLicenses();

    /**
     * @return the viewingHint
     */
    List<ViewingHint> getViewingHints();

    /**
     * @return the related
     */
    List<? extends ILabeledResource> getRelated();

    /**
     * @return the rendering
     */
    List<? extends ILabeledResource> getRendering();

    /**
     * @return one or more services - may be null!
     */
    List<Service> getServices();

    List<? extends ILabeledResource> getSeeAlso();

    List<IResource> getWithin();

    /**
     * 
     * @param allowedClasses All classes which should be included in the service list
     * @return A PropertyList of all services of one of the given classes
     */
    @Deprecated
    default List<Service> getServices(Class... allowedClasses) {
        List<Class> allowedClassesList = Arrays.asList(allowedClasses);
        if (this.getServices() != null) {
            return new PropertyList(this.getServices().stream()
                    .filter(service -> allowedClassesList.contains(service.getClass()))
                    .collect(Collectors.toList()));
        } else {
            return null;
        }
    }



}
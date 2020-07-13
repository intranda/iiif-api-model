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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.intranda.api.PropertyList;
import de.intranda.api.deserializer.ServiceDeserializer;
import de.intranda.api.deserializer.URLOnlyListDeserializer;
import de.intranda.api.iiif.presentation.content.ImageContent;
import de.intranda.api.iiif.presentation.content.LinkingContent;
import de.intranda.api.iiif.presentation.enums.ViewingHint;
import de.intranda.api.serializer.ImageContentLinkSerializer;
import de.intranda.api.serializer.URLOnlySerializer;
import de.intranda.api.services.Service;
import de.intranda.metadata.multilanguage.IMetadataValue;
import de.intranda.metadata.multilanguage.Metadata;

/**
 * @author florian
 *
 */
@JsonInclude(Include.NON_EMPTY)
@JsonPropertyOrder({ "@context", "@id", "@type", "label", "attribution", "license", "logo", "thumbnail", "viewingHint", "metadata", "service", "seeAlso" })
public interface IPresentationModelElement {

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
    @JsonSerialize(contentUsing = ImageContentLinkSerializer.class)
    @JsonProperty("thumbnail")
    List<ImageContent> getThumbnails();

    /**
     * @return the attribution
     */
    @JsonProperty("attribution")
    List<IMetadataValue> getAttributions();

    /**
     * @return the license
     */
    @JsonProperty("license")
    List<URI> getLicenses();

    /**
     * @return the logo
     */
    @JsonProperty("logo")
    List<ImageContent> getLogos();

    /**
     * @return the viewingHint
     */
    @JsonProperty("viewingHint")
    List<ViewingHint> getViewingHints();

    /**
     * @return the related
     */
    List<LinkingContent> getRelated();

    /**
     * @return the rendering
     */
    List<LinkingContent> getRendering();

    /**
     * @return one or more services - may be null!
     */
    @JsonProperty("service")
    List<Service> getServices();

    List<LinkingContent> getSeeAlso();

    /**
     * 
     * @param allowedClasses All classes which should be included in the service list
     * @return A PropertyList of all services of one of the given classes
     */
    @JsonProperty("service")
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

    /**
     * @return the id
     */
    URI getId();

    @JsonSerialize(using = URLOnlySerializer.class)
    @JsonDeserialize(using = URLOnlyListDeserializer.class)
    List<IPresentationModelElement> getWithin();

}
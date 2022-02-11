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
package de.intranda.api.iiif.presentation.v2;

import java.net.URI;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.intranda.api.annotation.IImageResource;
import de.intranda.api.annotation.IResource;
import de.intranda.api.deserializer.URLOnlyListDeserializer;
import de.intranda.api.iiif.presentation.IPresentationModelElement;
import de.intranda.api.iiif.presentation.content.ImageContent;
import de.intranda.api.iiif.presentation.content.LinkingContent;
import de.intranda.api.iiif.presentation.enums.ViewingHint;
import de.intranda.api.serializer.IIIF2MetadataValueSerializer;
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
public interface IPresentationModelElement2 extends IPresentationModelElement {

	
	
	@JsonProperty("@id")
	URI getId();
    
    @JsonProperty("@type")
    String getType();
	
    /**
     * @return the label
     */
    @JsonSerialize(using=IIIF2MetadataValueSerializer.class)
    @JsonProperty("label")
    IMetadataValue getLabel();

    /**
     * @return the description
     */
    @JsonSerialize(using=IIIF2MetadataValueSerializer.class)
    @JsonProperty("description")
    IMetadataValue getDescription();

    /**
     * @return the metadata
     */
    @JsonProperty("metadata")
    List<Metadata> getMetadata();

    /**
     * @return the thumbnail
     */
    @JsonProperty("thumbnail")
    @JsonSerialize(contentUsing = ImageContentLinkSerializer.class)
    List<ImageContent> getThumbnails();

    /**
     * @return the attribution
     */
    @JsonSerialize(using=IIIF2MetadataValueSerializer.class)
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
    @JsonSerialize(contentUsing = ImageContentLinkSerializer.class)
    List<ImageContent> getLogos();

    /**
     * @return the viewingHint
     */
    @JsonProperty("viewingHint")
    List<ViewingHint> getViewingHints();

    /**
     * @return the related
     */
    @JsonProperty("related")
    List<LinkingContent> getRelated();

    /**
     * @return the rendering
     */
    @JsonProperty("rendering")
    List<LinkingContent> getRendering();

    /**
     * @return one or more services - may be null!
     */
    @JsonProperty("service")
    List<Service> getServices();

    @JsonProperty("seeAlso")
    List<LinkingContent> getSeeAlso();

    @JsonSerialize(using = URLOnlySerializer.class)
    @JsonDeserialize(using = URLOnlyListDeserializer.class)
    List<IResource> getWithin();

}
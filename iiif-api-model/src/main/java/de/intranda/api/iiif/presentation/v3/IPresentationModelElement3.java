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
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import de.intranda.api.annotation.IResource;
import de.intranda.api.annotation.wa.ImageResource;
import de.intranda.api.iiif.presentation.IPresentationModelElement;
import de.intranda.api.iiif.presentation.enums.ViewingHint;
import de.intranda.api.serializer.IIIF3MetadataValueSerializer;
import de.intranda.api.services.Service;
import de.intranda.metadata.multilanguage.IMetadataValue;
import de.intranda.metadata.multilanguage.Metadata;

/**
 * @author florian
 *
 */
@JsonInclude(Include.NON_EMPTY)
@JsonPropertyOrder({ "@context", "id", "type", "label", "requiredStatement", "license", "provider", "thumbnail", "behavior", "metadata", "service", "seeAlso" })
public interface IPresentationModelElement3 extends IPresentationModelElement {
   
	public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	public static final String CONTEXT = "http://iiif.io/api/presentation/3/context.json";
	
	@JsonProperty("id")
	URI getId();
    
    @JsonProperty("type")
    String getType();


	
    /**
     * @return the label
     */
    @JsonProperty("label")
    @JsonSerialize(using=IIIF3MetadataValueSerializer.class)
    IMetadataValue getLabel();

    /**
     * @return the description
     */
    @JsonProperty("summary")
    @JsonSerialize(using=IIIF3MetadataValueSerializer.class)
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
    List<ImageResource> getThumbnails();

    /**
     * @return the attribution
     */
    @JsonProperty("requiredStatement")
    Metadata getRequiredStatement();

    /**
     * @return the license
     */
    @JsonProperty("rights")
    List<URI> getLicenses();

    /**
     * @return the logo
     */
    @JsonProperty("provider")
    List<IIIFAgent> getProvider();

    /**
     * @return the viewingHint
     */
    @JsonProperty("behavior")
    List<ViewingHint> getViewingHints();

    /**
     * WebPages about the owning resource (viewer object page, related cms-pages, browse page for collections)
     * 
     * @return the related
     */
    @JsonProperty("homepage")
    List<LabeledResource> getRelated();

    /**
     * Human usable, non-IIIF representations of the resource (pdf, epub, plaintext, rss-feed for collections)
     * 
     * @return the rendering
     */
    @JsonProperty("rendering")
    List<LabeledResource> getRendering();

    /**
     * @return one or more services - may be null!
     */
    @JsonProperty("service")
    List<Service> getServices();

    /**
     * Machine readable resources related to the resource (ALTO, LIDO, METS/MODS)
     */
    @JsonProperty("seeAlso")
    List<LabeledResource> getSeeAlso();

    @JsonProperty("partOf")
    List<IResource> getWithin();
    
    @JsonProperty("items")
    List<? extends IResource> getItems();


	/**
	 * @return the navDate
	 */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_FORMAT)
	public LocalDateTime getNavDate();

}
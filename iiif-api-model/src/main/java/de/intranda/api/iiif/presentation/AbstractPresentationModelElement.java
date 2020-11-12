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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.intranda.api.PropertyList;
import de.intranda.api.annotation.IResource;
import de.intranda.api.iiif.presentation.content.ImageContent;
import de.intranda.api.iiif.presentation.content.LinkingContent;
import de.intranda.api.iiif.presentation.enums.ViewingHint;
import de.intranda.api.serializer.ImageContentLinkSerializer;
import de.intranda.api.services.Service;
import de.intranda.metadata.multilanguage.IMetadataValue;
import de.intranda.metadata.multilanguage.Metadata;

/**
 * Parent class for all classes modeling the iiif presentation api resources except images and other canvas content
 * 
 * @author florian
 *
 */
public abstract class AbstractPresentationModelElement implements IPresentationModelElement, IResource {

    protected static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    private final URI id;
    private IMetadataValue label;
    private IMetadataValue description;
    private List<Metadata> metadata = new ArrayList<>();
    private List<ImageContent> thumbnails = new PropertyList<>();
    private List<IMetadataValue> attributions = new PropertyList<>();
    private List<ImageContent> logos = new PropertyList<>();
    private List<ViewingHint> viewingHints = new PropertyList<>();
    private List<LinkingContent> related = new PropertyList<>();
    private List<LinkingContent> rendering = new PropertyList<>();
    private List<URI> licenses = new PropertyList<>();
    private String context = null;
    private List<Service> services = new PropertyList<Service>();
    private List<LinkingContent> seeAlso = new PropertyList<>();
    private List<IPresentationModelElement> within = new PropertyList<>();

    public AbstractPresentationModelElement() {
        super();
        this.id = null;
    }

    public AbstractPresentationModelElement(URI id) {
        this.id = id;
    }

    /**
     * @param context the context to set
     */
    public void setContext(String context) {
        this.context = context;
    }

    @JsonProperty("@context")
    public PropertyList<String> getContext() {
        if(StringUtils.isNotBlank(context)) {            
            return new PropertyList<>(Collections.singletonList(context));
        } else {
            return null;
        }
    } 

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.IPresentationModelElement#getType()
     */
    @Override
    @JsonProperty("@type")
    public abstract String getType();

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.IPresentationModelElement#getLabel()
     */
    @Override
    public IMetadataValue getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(IMetadataValue label) {
        this.label = label;
    }

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.IPresentationModelElement#getDescription()
     */
    @Override
    public IMetadataValue getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(IMetadataValue description) {
        this.description = description;
    }

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.IPresentationModelElement#getMetadata()
     */
    @Override
    public List<Metadata> getMetadata() {
        return this.metadata.isEmpty() ? new ArrayList<>() : this.metadata;
    }

    public void addMetadata(Metadata md) {
        this.metadata.add(md);
    }

    /**
     * @param metadata the metadata to set
     */
    public void setMetadata(List<Metadata> metadata) {
        this.metadata = metadata;
    }

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.IPresentationModelElement#getThumbnail()
     */
    @Override
    public List<ImageContent> getThumbnails() {
        return this.thumbnails.isEmpty() ? new ArrayList<>() : this.thumbnails;
    }

    /**
     * @param thumbnail the thumbnail to set
     */
    public void addThumbnail(ImageContent thumbnail) {
        this.thumbnails.add(thumbnail);
    }

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.IPresentationModelElement#getAttribution()
     */
    @Override
    public List<IMetadataValue> getAttributions() {
        return this.attributions.isEmpty() ? new ArrayList<>() : this.attributions;
    }

    /**
     * @param attribution the attribution to set
     */
    public void addAttribution(IMetadataValue attribution) {
        this.attributions.add(attribution);
    }

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.IPresentationModelElement#getLicense()
     */
    @Override
    public List<URI> getLicenses() {
        return this.licenses.isEmpty() ? new ArrayList<>() : this.licenses;
    }

    /**
     * @param license the license to set
     */
    public void addLicense(URI license) {
        this.licenses.add(license);
    }

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.IPresentationModelElement#getLogo()
     */
    @Override
    @JsonSerialize(contentUsing = ImageContentLinkSerializer.class)
    public List<ImageContent> getLogos() {
        return this.logos.isEmpty() ? new ArrayList<>() : this.logos;
    }

    /**
     * @param logo the logo to set
     */
    public void addLogo(ImageContent logo) {
        this.logos.add(logo);
    }

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.IPresentationModelElement#getViewingHint()
     */
    @Override
    public List<ViewingHint> getViewingHints() {
        return this.viewingHints.isEmpty() ? new ArrayList<>() : this.viewingHints;
    }

    /**
     * @param viewingHint the viewingHint to set
     */
    public void addViewingHint(ViewingHint viewingHint) {
        this.viewingHints.add(viewingHint);
    }

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.IPresentationModelElement#getRelated()
     */
    @Override
    public List<LinkingContent> getRelated() {
        return related.isEmpty() ? new ArrayList<>() : related;
    }

    /**
     * @param related the related to set
     */
    public void addRelated(LinkingContent related) {
        this.related.add(related);
    }

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.IPresentationModelElement#getRendering()
     */
    @Override
    public List<LinkingContent> getRendering() {
        return rendering.isEmpty() ? new ArrayList<>() : rendering;
    }

    /**
     * @param rendering the rendering to set
     */
    public void addRendering(LinkingContent rendering) {
        this.rendering.add(rendering);
    }

    /* (non-Javadoc)
    * @see de.intranda.digiverso.presentation.model.iiif.presentation.IPresentationModelElement#getRendering()
    */
    @Override
    public List<LinkingContent> getSeeAlso() {
        return seeAlso.isEmpty() ? new ArrayList<>() : seeAlso;
    }

    /**
     * @param rendering the rendering to set
     */
    public void addSeeAlso(LinkingContent seeAlso) {
        this.seeAlso.add(seeAlso);
    }

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.IPresentationModelElement#getId()
     */
    @Override
    @JsonProperty("@id")
    public URI getId() {
        return id;
    }

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.IPresentationModelElement#getService()
     */
    @Override
    public List<Service> getServices() {
        return services.isEmpty() ? new ArrayList<>() : services;
    }

    public void addService(Service service) {
        this.services.add(service);
    }


    /**
     * @return the navDate
     */
    @JsonFormat(pattern = DATETIME_FORMAT)
    public Date getNavDate() {
        return null;
    }

    /**
     * @param navDate the navDate to set
     */
    public void setNavDate(Date navDate) {
    }

    /**
     * @return the within
     */
    @Override
    public List<IPresentationModelElement> getWithin() {
        return within.isEmpty() ? null : within;
    }

    public void addWithin(IPresentationModelElement within) {
        this.within.add(within);
    }

}

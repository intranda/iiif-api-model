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
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import de.intranda.api.PropertyList;
import de.intranda.api.annotation.IResource;
import de.intranda.api.iiif.presentation.enums.ViewingHint;
import de.intranda.api.iiif.presentation.v2.content.ImageContent;
import de.intranda.api.iiif.presentation.v2.content.LinkingContent;
import de.intranda.api.services.Service;
import de.intranda.metadata.multilanguage.IMetadataValue;
import de.intranda.metadata.multilanguage.Metadata;

/**
 * Parent class for all classes modeling the iiif presentation api resources except images and other canvas content
 * 
 * @author florian
 *
 */
public abstract class AbstractPresentationModelElement implements IPresentationModelElement {

    protected static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    protected final URI id;
    protected String context = null;
    protected IMetadataValue label;
    protected IMetadataValue description;
    protected List<Metadata> metadata;
    protected List<ImageContent> thumbnails;
    protected List<IMetadataValue> attributions;
    protected List<ViewingHint> viewingHints;
    protected List<LinkingContent> related;
    protected List<LinkingContent> rendering;
    protected List<URI> licenses;
    protected List<Service> services;
    protected List<LinkingContent> seeAlso;
    protected List<IResource> within;

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
     * @return the within
     */
    @Override
    public List<IResource> getWithin() {
        return within.isEmpty() ? null : within;
    }

    public void addWithin(IPresentationModelElement within) {
        this.within.add(within);
    }

}

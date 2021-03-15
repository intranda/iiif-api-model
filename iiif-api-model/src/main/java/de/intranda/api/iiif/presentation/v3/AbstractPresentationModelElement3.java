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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

import de.intranda.api.PropertyList;
import de.intranda.api.annotation.IImageResource;
import de.intranda.api.annotation.ILabeledResource;
import de.intranda.api.annotation.IResource;
import de.intranda.api.annotation.wa.ImageResource;
import de.intranda.api.annotation.wa.collection.AnnotationPage;
import de.intranda.api.iiif.presentation.IPresentationModelElement;
import de.intranda.api.iiif.presentation.content.LinkingContent;
import de.intranda.api.iiif.presentation.enums.ViewingHint;
import de.intranda.api.services.Service;
import de.intranda.metadata.multilanguage.IMetadataValue;
import de.intranda.metadata.multilanguage.Metadata;

/**
 * Parent class for all classes modeling the iiif presentation api resources except images and other canvas content
 * 
 * @author florian
 *
 */
public abstract class AbstractPresentationModelElement3 implements IPresentationModelElement3 {

	
	protected final URI id;
    protected String context = null;
    protected IMetadataValue label;
    protected IMetadataValue description;
    protected List<Metadata> metadata;
    protected List<ImageResource> thumbnails;
    protected List<IMetadataValue> attributions;
    protected List<ViewingHint> behavior;
    protected List<LabeledResource> related;
    protected List<LabeledResource> rendering;
    protected URI license;
    protected List<Service> services;
    protected List<LabeledResource> seeAlso;
    protected List<IResource> within;
	
	private List<IIIFAgent> provider;
	private Metadata requiredStatement = null;
	private LocalDateTime navDate = null;
	//What used to be layers; commenting annotations in resources
    private final List<AnnotationPage> annotations;

	
    public AbstractPresentationModelElement3() {
    	this(null);
    }

    public AbstractPresentationModelElement3(URI id) {
        this.id = id;
        metadata = new ArrayList<>();
        thumbnails = new ArrayList<>();
        behavior = new ArrayList<>();
        related = new ArrayList<>();
        rendering = new ArrayList<>();
        license = null;
        services = new ArrayList<Service>();
        seeAlso = new ArrayList<>();
        within = new ArrayList<>();
        provider = new ArrayList<>();
        annotations = new ArrayList<>();
    }

    public Metadata getRequiredStatement() {
		return requiredStatement;
	}
    
    public void setRequiredStatement(Metadata requiredStatement) {
		this.requiredStatement = requiredStatement;
	}

	@Override
    public LocalDateTime getNavDate() {
        return navDate;
    }

    public void setNavDate(LocalDateTime navDate) {
        this.navDate = navDate;
    }
    
    @Override
    public List<IIIFAgent> getProvider() {
    	return this.provider;
    }
    
    public void addProvider(IIIFAgent provider) {
    	this.provider.add(provider);
    }

    public List<AnnotationPage> getAnnotations() {
        return annotations;
    }    

    public void addAnnotations(AnnotationPage content) {
        this.annotations.add(content);
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
        return this.metadata;
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
    public List<ImageResource> getThumbnails() {
        return this.thumbnails;
    }

    /**
     * @param thumbnail the thumbnail to set
     */
    public void addThumbnail(ImageResource thumbnail) {
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
    	//the interface espects a list, but this may only be a single value. So package it in a PropertyList 
    	//of size 1 which is always rendered as single value
        return this.license == null ? null : new PropertyList<>(Arrays.asList(this.license));
    }

    /**
     * @param license the license to set
     */
    public void setLicense(URI license) {
        this.license = license;
    }

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.IPresentationModelElement#getViewingHint()
     */
    @Override
    public List<ViewingHint> getViewingHints() {
        return this.behavior;
    }

    /**
     * @param viewingHint the viewingHint to set
     */
    public void addViewingHint(ViewingHint viewingHint) {
        this.behavior.add(viewingHint);
    }

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.IPresentationModelElement#getRelated()
     */
    @Override
    public List<LabeledResource> getRelated() {
        return related;
    }

    /**
     * @param related the related to set
     */
    public void addRelated(LabeledResource related) {
        this.related.add(related);
    }

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.IPresentationModelElement#getRendering()
     */
    @Override
    public List<LabeledResource> getRendering() {
        return rendering;
    }

    /**
     * @param rendering the rendering to set
     */
    public void addRendering(LabeledResource rendering) {
        this.rendering.add(rendering);
    }

    /* (non-Javadoc)
    * @see de.intranda.digiverso.presentation.model.iiif.presentation.IPresentationModelElement#getRendering()
    */
    @Override
    public List<LabeledResource> getSeeAlso() {
        return seeAlso;
    }

    /**
     * @param rendering the rendering to set
     */
    public void addSeeAlso(LabeledResource seeAlso) {
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

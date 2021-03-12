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
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import de.intranda.api.annotation.wa.collection.AnnotationPage;
import de.intranda.api.iiif.presentation.AbstractPresentationModelElement;
import de.intranda.api.services.Service;
import de.intranda.metadata.multilanguage.Metadata;

/**
 * Parent class for all classes modeling the iiif presentation api resources except images and other canvas content
 * 
 * @author florian
 *
 */
public abstract class AbstractPresentationModelElement3 extends AbstractPresentationModelElement implements IPresentationModelElement3 {

	private List<IIIFAgent> provider;
	private Metadata requiredStatement = null;
	private LocalDateTime navDate = null;
	//What used to be layers; commenting annotations in resources
    private final List<AnnotationPage> annotations;

	
    public AbstractPresentationModelElement3() {
    	this(null);
    }

    public AbstractPresentationModelElement3(URI id) {
        super(id);
        metadata = new ArrayList<>();
        thumbnails = new ArrayList<>();
        viewingHints = new ArrayList<>();
        related = new ArrayList<>();
        rendering = new ArrayList<>();
        licenses = new ArrayList<>();
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
    @JsonFormat(pattern = DATETIME_FORMAT)
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
    
}

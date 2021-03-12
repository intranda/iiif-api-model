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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import de.intranda.api.PropertyList;
import de.intranda.api.iiif.presentation.AbstractPresentationModelElement;
import de.intranda.api.iiif.presentation.v2.content.ImageContent;
import de.intranda.api.services.Service;
import de.intranda.metadata.multilanguage.IMetadataValue;

/**
 * Parent class for all classes modeling the iiif presentation api resources except images and other canvas content
 * 
 * @author florian
 *
 */
public abstract class AbstractPresentationModelElement2 extends AbstractPresentationModelElement implements IPresentationModelElement2 {
    private List<IMetadataValue> attributions = new PropertyList<>();
    private List<ImageContent> logos;
    
    public AbstractPresentationModelElement2() {
    	this(null);
    }

    public AbstractPresentationModelElement2(URI id) {
        super(id);
        metadata = new ArrayList<>();
        thumbnails = new PropertyList<>();
        attributions = new PropertyList<>();
        logos = new PropertyList<>();
        viewingHints = new PropertyList<>();
        related = new PropertyList<>();
        rendering = new PropertyList<>();
        licenses = new PropertyList<>();
        services = new PropertyList<Service>();
        seeAlso = new PropertyList<>();
        within = new PropertyList<>();
    }

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
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.IPresentationModelElement#getLogo()
     */
    @Override
    public List<ImageContent> getLogos() {
        return this.logos.isEmpty() ? new ArrayList<>() : this.logos;
    }

    /**
     * @param logo the logo to set
     */
    public void addLogo(ImageContent logo) {
        this.logos.add(logo);
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

}

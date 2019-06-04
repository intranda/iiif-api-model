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
package de.intranda.api.annotation;

import java.net.URI;
import java.net.URISyntaxException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.intranda.api.iiif.presentation.AbstractPresentationModelElement;
import de.intranda.api.iiif.presentation.Canvas;
import de.intranda.api.iiif.presentation.IPresentationModelElement;
import de.intranda.api.iiif.presentation.content.IContent;
import de.intranda.api.iiif.presentation.enums.Motivation;
import de.intranda.api.serializer.URLOnlySerializer;

/**
 * @author Florian Alpers
 *
 */
public class LinkedAnnotation extends AbstractAnnotation implements IAnnotation{
    
    private IContent resource;
    
    /**
     * @param id
     */
    public LinkedAnnotation(URI id) {
       super(id);
    }

    /**
     * @return the resource
     */
    public IContent getResource() {
        return resource;
    }

    /**
     * @param resource the resource to set
     */
    public void setResource(IContent resource) {
        this.resource = resource;
    }


    

}

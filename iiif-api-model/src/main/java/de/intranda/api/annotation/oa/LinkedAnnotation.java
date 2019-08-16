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
package de.intranda.api.annotation.oa;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import de.intranda.api.annotation.IAnnotation;
import de.intranda.api.annotation.IResource;
import de.intranda.api.deserializer.LinkedAnnotationResourceDeserializer;
import de.intranda.api.iiif.presentation.content.IContent;

/**
 * @author Florian Alpers
 *
 */
public class LinkedAnnotation extends AbstractAnnotation implements IAnnotation {

    public LinkedAnnotation() {
        super(null);
    }

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
    @JsonDeserialize(using = LinkedAnnotationResourceDeserializer.class)
    public IContent getResource() {
        return resource;
    }

    /**
     * @param resource the resource to set
     */
    public void setResource(IContent resource) {
        this.resource = resource;
    }

    @Override
    @JsonIgnore
    public IResource getBody() {
        return getResource();
    }

}

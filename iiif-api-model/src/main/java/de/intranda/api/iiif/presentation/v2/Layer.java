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
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.intranda.api.serializer.URLOnlySerializer;

/**
 * @author Florian Alpers
 *
 */
public class Layer extends AbstractPresentationModelElement2 implements IPresentationModelElement2 {

    private static final String TYPE = "sc:Layer";
    
    private final List<AnnotationList> otherContent = new ArrayList<>();
    
    public Layer() {
        super(null);
    }
    
    /**
     * @param id
     */
    public Layer(URI id) {
        super(id);
    }

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.AbstractPresentationModelElement#getType()
     */
    @Override
    public String getType() {
        return TYPE;
    }
    
    /**
     * @return the otherContent
     */
    @JsonSerialize(using=URLOnlySerializer.class)
    public List<AnnotationList> getOtherContent() {
        return otherContent.isEmpty() ? null : otherContent;
    }
    
    public void addOtherContent(AnnotationList content) {
        this.otherContent.add(content);
    }

}

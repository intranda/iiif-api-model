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
package de.intranda.api.serializer;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import de.intranda.api.PropertyList;
import de.intranda.api.annotation.IAnnotation;
import de.intranda.api.annotation.IResource;
import de.intranda.api.annotation.wa.SpecificResource;
import de.intranda.api.annotation.wa.collection.AnnotationCollection;
import de.intranda.api.annotation.wa.collection.AnnotationPage;
import de.intranda.api.iiif.presentation.IPresentationModelElement;

/**
 * @author Florian Alpers
 *
 */
public class URLOnlySerializer extends JsonSerializer<Object> {

    /* (non-Javadoc)
     * @see com.fasterxml.jackson.databind.JsonSerializer#serialize(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider)
     */
    @Override
    public void serialize(Object o, JsonGenerator generator, SerializerProvider provider) throws IOException, JsonProcessingException {

        write(o, generator);
    }
    
    private void write(Object o, JsonGenerator generator) throws IOException {
        if (o instanceof IPresentationModelElement) {
            IPresentationModelElement element = (IPresentationModelElement) o;
            generator.writeString(element.getId().toString());
        } else if(o instanceof IResource) {
            IResource resource = (IResource) o;
            if(resource instanceof de.intranda.api.annotation.oa.SpecificResource) {
                de.intranda.api.annotation.oa.SpecificResource s = (de.intranda.api.annotation.oa.SpecificResource)resource;
                generator.writeString(s.getId() + Optional.ofNullable(s.getSelector()).map(selector -> "#" + selector.getValue()).orElse(""));
            } else if(resource instanceof de.intranda.api.annotation.wa.SpecificResource) {
                de.intranda.api.annotation.wa.SpecificResource s = (de.intranda.api.annotation.wa.SpecificResource)resource;
                generator.writeString(s.getId() + Optional.ofNullable(s.getSelector()).map(selector -> "#" + selector.getValue()).orElse(""));
            } else {                
                generator.writeString(resource.getId().toString());
            }
        } else if(o instanceof AnnotationPage) {
            AnnotationPage resource = (AnnotationPage) o;
            generator.writeString(resource.getId().toString());
        }else if(o instanceof AnnotationCollection) {
            AnnotationCollection resource = (AnnotationCollection) o;
            generator.writeString(resource.getId().toString());
        } else if(o instanceof IAnnotation)  {
            IAnnotation anno = (IAnnotation)o;
            generator.writeString(anno.getId().toString());
        } else if (o instanceof Collection) {
            Collection collection = (Collection) o;
            if (collection instanceof PropertyList &&  collection.size() == 1) {
                Object obj = collection.iterator().next();
                write(obj, generator);
            } else if (!collection.isEmpty()) {
                generator.writeStartArray();
                for (Object child : collection) {
                    write(child, generator);
                }
                generator.writeEndArray();
            }
        }
    }

}

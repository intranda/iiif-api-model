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
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import de.intranda.api.iiif.presentation.IPresentationModelElement;
import de.intranda.api.iiif.presentation.enums.ViewingHint;
import de.intranda.api.iiif.presentation.v2.IPresentationModelElement2;
import de.intranda.api.iiif.presentation.v2.Range2;
import de.intranda.api.iiif.presentation.v2.content.ImageContent;
import de.intranda.api.iiif.presentation.v2.content.LinkingContent;
import de.intranda.api.iiif.presentation.v3.Range3;
import de.intranda.api.services.Service;
import de.intranda.metadata.multilanguage.IIIF2MetadataValue;

/**
 * @author Florian Alpers
 *
 */
public class ContentLinkSerializer extends JsonSerializer<List<IPresentationModelElement2>> {

    private static final Logger logger = LoggerFactory.getLogger(ContentLinkSerializer.class);

    /* (non-Javadoc)
     * @see com.fasterxml.jackson.databind.JsonSerializer#serialize(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider)
     */
    @Override
    public void serialize(List<IPresentationModelElement2> elements, JsonGenerator generator, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        if (elements != null && !elements.isEmpty()) {
            generator.writeStartArray();
            for (IPresentationModelElement element : elements) {
                try {
                    writeElement(element, generator, provider);
                } catch (IOException e) {
                    //Ignore: probably a ClientAbortException
                }
            }
            generator.writeEndArray();
        }

    }

    /**
     * @param element
     * @param generator
     * @throws IOException
     */
    public void writeElement(IPresentationModelElement element, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeStartObject();
        generator.writeStringField("@id", element.getId().toString());
        generator.writeStringField("@type", element.getType());
        if (element.getLabel() != null && !element.getLabel().isEmpty()) {
            generator.writeObjectField("label", new IIIF2MetadataValue(element.getLabel()));
        }
        if(element.getDescription() != null) {
            generator.writeObjectField("description", new IIIF2MetadataValue(element.getDescription()));
        }
        if (element.getViewingHints() == null) {
            
        } else if (element.getViewingHints().size() > 1) {
            generator.writeArrayFieldStart("viewingHint");
            for (ViewingHint viewingHint : element.getViewingHints()) {
                generator.writeObject(viewingHint);
            }
            generator.writeEndArray();
        } else if(element.getViewingHints().size() == 1) {
            generator.writeObjectField("viewingHint", element.getViewingHints().get(0));
        }
        
        if (element.getThumbnails() == null) {
            
        } else if(element.getThumbnails().size() > 1) {
            generator.writeArrayFieldStart("thumbnail");
            for (ImageContent thumb : element.getThumbnails()) {
                new ImageContentLinkSerializer().serialize(thumb, generator, provider);

            }
            generator.writeEndArray();
        } else if(element.getThumbnails().size() == 1) {
            generator.writeFieldName("thumbnail");
            new ImageContentLinkSerializer().serialize(element.getThumbnails().get(0), generator, provider);
        }
        


        if (element.getServices() == null) {
            
        } else if(element.getServices() != null) {
            if (element.getServices().size() == 1) {
                generator.writeObjectField("service", element.getServices().get(0));
            } else if (element.getServices().size() > 1) {
                generator.writeArrayFieldStart("service");
                for (Service service : element.getServices()) {
                    generator.writeObject(service);
                }
                generator.writeEndArray();
            }
        }

        if (element.getRendering() != null && !element.getRendering().isEmpty()) {
            generator.writeFieldName("rendering");
            generator.writeStartArray();
            for (LinkingContent renderLink : element.getRendering()) {
                generator.writeObject(renderLink);
            }
            generator.writeEndArray();
        }

        if (element.getRelated() != null && !element.getRelated().isEmpty()) {
            generator.writeFieldName("related");
            generator.writeStartArray();
            for (LinkingContent renderLink : element.getRelated()) {
                generator.writeObject(renderLink);
            }
            generator.writeEndArray();
        }

        if (element instanceof Range2) {
            if (((Range2) element).getStartCanvas() != null) {
                generator.writeObjectField("startCanvas", ((Range2) element).getStartCanvas().getId());
            }
        }
        generator.writeEndObject();
    }

}

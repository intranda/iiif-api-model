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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import de.intranda.api.iiif.image.ImageInformation;
import de.intranda.api.iiif.presentation.content.ImageContent;
import de.intranda.api.services.Service;

/**
 * @author Florian Alpers
 *
 */
public class ImageContentLinkSerializer extends JsonSerializer<ImageContent>{

    /* (non-Javadoc)
     * @see com.fasterxml.jackson.databind.JsonSerializer#serialize(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider)
     */
    @Override
    public void serialize(ImageContent element, JsonGenerator generator, SerializerProvider provicer) throws IOException, JsonProcessingException {
        
        if(element.getService() == null || element.getService().isEmpty()) {
//            generator.writeString(element.getId().toString());
            generator.writeStartObject();
            generator.writeStringField("@id", element.getId().toString());
            generator.writeStringField("@type", element.getType());
            generator.writeEndObject();
        } else if(element.getService().size() == 1){
        	ImageInformation service = element.getService().get(0);
            generator.writeStartObject();
            generator.writeStringField("@id", element.getId().toString());
            generator.writeStringField("@type", element.getType());
            generator.writeObjectFieldStart("service");
            generator.writeStringField("@context", ImageInformation.JSON_CONTEXT.toString());
            generator.writeStringField("@id", service.getId().toString());
            generator.writeStringField("profile", ImageInformation.IIIF_COMPLIANCE_LEVEL.getUri());
            generator.writeEndObject();
            generator.writeEndObject();
        } else {
        	generator.writeStartObject();
            generator.writeStringField("@id", element.getId().toString());
            generator.writeStringField("@type", element.getType());
            generator.writeArrayFieldStart("service");
            for (ImageInformation service : element.getService()) {
				generator.writeStartObject();
				generator.writeStringField("@context", ImageInformation.JSON_CONTEXT.toString());
				generator.writeStringField("@id", service.getId().toString());
				generator.writeStringField("profile", ImageInformation.IIIF_COMPLIANCE_LEVEL.getUri());
				generator.writeEndObject();
			}
            generator.writeEndArray();
            generator.writeEndObject();
        }
        
        
    }

}

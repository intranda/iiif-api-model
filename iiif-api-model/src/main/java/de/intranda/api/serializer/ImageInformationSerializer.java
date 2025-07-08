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
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import de.intranda.api.iiif.image.ImageInformation;
import de.intranda.api.iiif.image.v3.ImageInformation3;
import de.intranda.api.services.Service;

/**
 * @author Florian Alpers
 *
 */
public class ImageInformationSerializer extends JsonSerializer<ImageInformation> {

    /* (non-Javadoc)
     * @see com.fasterxml.jackson.databind.JsonSerializer#serialize(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider)
     */
    @Override
    public void serialize(ImageInformation element, JsonGenerator generator, SerializerProvider provicer) throws IOException {

        if (element instanceof ImageInformation3) {
            generator.writeStartObject();
            generator.writeStringField("id", element.getId().toString());
            generator.writeStringField("type", ImageInformation3.TYPE);
            generator.writeStringField("profile", ImageInformation3.IIIF_COMPLIANCE_LEVEL.getLabel());
            generator.writeArrayFieldStart("service");
            for (Service service : element.getService()) {
                generator.writeObject(service);
            }
            generator.writeEndArray();
            generator.writeEndObject();
        } else {
            generator.writeStartObject();
            generator.writeStringField("@context", ImageInformation.JSON_CONTEXT.toString());
            generator.writeStringField("@id", element.getId().toString());
            generator.writeStringField("profile", ImageInformation.IIIF_COMPLIANCE_LEVEL.getUri());
            generator.writeEndObject();
        }

    }

}

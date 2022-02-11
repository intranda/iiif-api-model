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

import de.intranda.metadata.multilanguage.IMetadataValue;
import de.intranda.metadata.multilanguage.MultiLanguageMetadataValue.ValuePair;

/**
 * Serialize Metadata conforming to the standard used in IIIF Presentation 2.0
 * 
 * @author Florian Alpers
 *
 */
public class IIIF2MetadataValueSerializer extends JsonSerializer<Object> {

    private static final Logger logger = LoggerFactory.getLogger(IIIF2MetadataValueSerializer.class);

    @Override
    public void serialize(Object obj, JsonGenerator generator, SerializerProvider provider) throws IOException, JsonProcessingException {

        if(obj instanceof IMetadataValue ) {
            writeSingleObject((IMetadataValue)obj, generator, provider);
        } else if(obj instanceof List) {
            if(((List) obj).size() == 1) {
                writeSingleObject((IMetadataValue)((List) obj).get(0), generator, provider);
            } else {
                generator.writeStartArray();
                for (Object element : (List)obj) {
                    if(element instanceof IMetadataValue) {
                        writeSingleObject((IMetadataValue)element, generator, provider);
                    }
                }
                generator.writeEndArray();
            }
        }
        


    }
    
    private void writeSingleObject(IMetadataValue element, JsonGenerator generator,SerializerProvider provider) throws IOException {
        if (!allTranslationsEqual( element)) {
            generator.writeStartArray();
            for (String language : element.getLanguages()) {
                element.getValue(language).ifPresent(value -> {                    
                    try {
                        generator.writeStartObject();
                        generator.writeFieldName("@language");
                        generator.writeString(language);
                        generator.writeFieldName("@value");
                        generator.writeString(value);
                        generator.writeEndObject();
                    } catch (IOException e) {
                        logger.error("Error writing metadata " + element, e);
                    }
                });
            }
            generator.writeEndArray();
            
        } else {
            generator.writeString(element.getValue().orElse(""));
        }
    }

    protected boolean allTranslationsEqual(IMetadataValue element) {
        return element.getValues().stream().map(ValuePair::getValue).distinct().count() == 1;
    }

}

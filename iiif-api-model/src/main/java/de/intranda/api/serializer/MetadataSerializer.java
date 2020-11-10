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

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import de.intranda.metadata.multilanguage.IMetadataValue;
import de.intranda.metadata.multilanguage.MultiLanguageMetadataValue;
import de.intranda.metadata.multilanguage.MultiLanguageMetadataValue.ValuePair;

/**
 * @author Florian Alpers
 *
 */
public class MetadataSerializer extends JsonSerializer<IMetadataValue> {

    /* (non-Javadoc)
     * @see com.fasterxml.jackson.databind.JsonSerializer#serialize(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider)
     */
    @Override
    public void serialize(IMetadataValue element, JsonGenerator generator, SerializerProvider provicer) throws IOException, JsonProcessingException {

        if (element instanceof MultiLanguageMetadataValue && !allTranslationsEqual((MultiLanguageMetadataValue) element)) {
            generator.writeStartObject();
            for (String language : element.getLanguages()) {
                if (element.getLanguages().size() > 1 && language.equals(MultiLanguageMetadataValue.DEFAULT_LANGUAGE)) {
                    continue;
                }
                element.getValue(language).filter(StringUtils::isNotBlank).ifPresent(value -> {
                    try {                        
                        generator.writeArrayFieldStart(language);
                        generator.writeString(StringEscapeUtils.escapeHtml4(value));
                        generator.writeEndArray();
                    } catch(IOException e) {
                    }
                    
                });

            }
            generator.writeEndObject();
        } else {
            generator.writeString(element.getValue().orElse(""));
        }

    }

    protected boolean allTranslationsEqual(MultiLanguageMetadataValue element) {
        return element.getValues().stream().map(ValuePair::getValue).distinct().count() == 1;
    }

}

package de.intranda.api.serializer;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;

import de.intranda.metadata.multilanguage.IMetadataValue;
import de.intranda.metadata.multilanguage.MultiLanguageMetadataValue;

/**
 * Serializes values like the {@link WebAnnotationMetadataValueSerializer} but always as a json
 * object, never as string
 * 
 * @author florian
 *
 */
public class IIIF3MetadataValueSerializer extends WebAnnotationMetadataValueSerializer {

	public static final String DEFAULT_LANGUAGE_TAG = "none";
	
	@Override
	public void serialize(IMetadataValue element, JsonGenerator generator, SerializerProvider provicer)
			throws IOException, JsonProcessingException {

		generator.writeStartObject();
		for (String language : element.getLanguages()) {
//			if (element.getLanguages().size() > 1 && isDefaultLanguage(language)) {
//				//skip default language if other languages are available
//				continue;
//			}
			element.getValue(language).filter(StringUtils::isNotBlank).ifPresent(value -> {
				try {
					generator.writeArrayFieldStart(isDefaultLanguage(language) ? DEFAULT_LANGUAGE_TAG : language);
					generator.writeString(StringEscapeUtils.unescapeHtml4(value));
					generator.writeEndArray();
				} catch (IOException e) {
				}

			});
		}
		generator.writeEndObject();

	}

	private boolean isDefaultLanguage(String language) {
		return language.equals(MultiLanguageMetadataValue.DEFAULT_LANGUAGE);
	}

}

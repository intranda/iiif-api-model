package de.intranda.api.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import de.intranda.api.iiif.presentation.IPresentationModelElement;
import de.intranda.api.iiif.presentation.v3.IPresentationModelElement3;

/**
 * Writes IIIF3 Presentation resources with only their "id" and "type" attributes as specified for all non-enumerable resources
 * in the IIIF Presentation 3.0 specification
 * 
 * @author florian
 *
 */
public class LinkedResourceSerializer  extends JsonSerializer<IPresentationModelElement3> {

	@Override
	public void serialize(IPresentationModelElement3 value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException {
		
		gen.writeStartObject();
		gen.writeStringField("id", value.getId().toString());
		gen.writeStringField("type", value.getType());
		gen.writeEndObject();
	}
	

}

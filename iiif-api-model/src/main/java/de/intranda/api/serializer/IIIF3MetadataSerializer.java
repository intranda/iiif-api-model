package de.intranda.api.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import de.intranda.metadata.multilanguage.IMetadataValue;
import de.intranda.metadata.multilanguage.Metadata;

public class IIIF3MetadataSerializer extends JsonSerializer<Metadata> {
	{

	}

	@Override
	public void serialize(Metadata value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		// TODO Auto-generated method stub
	}
}

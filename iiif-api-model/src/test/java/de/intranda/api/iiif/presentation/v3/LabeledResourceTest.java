package de.intranda.api.iiif.presentation.v3;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.intranda.api.annotation.ILabeledResource;
import de.intranda.api.iiif.presentation.enums.Format;
import de.intranda.metadata.multilanguage.IMetadataValue;
import de.intranda.metadata.multilanguage.SimpleMetadataValue;

public class LabeledResourceTest {

	private static final String HOMEPAGE_ID = "http://www.viewer.goobi.io/images/1234/";
	private static final String HOMEPAGE_LABEL = "viewImage";

	
	@Test
	public void testSerialize() throws JsonProcessingException {
		String uri = "https://www.resource.de";
		String type = "Text";
		IMetadataValue label = new SimpleMetadataValue("Das Label");
		
		LabeledResource resource = new LabeledResource(URI.create(uri), type, label);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(resource);
		System.out.println(json);
		
		
		List<? extends ILabeledResource> resources = Arrays.asList(resource);
		String jsonList = mapper.writeValueAsString(resources);
		System.out.println(jsonList);
		
		Manifest3 manifest = new Manifest3(URI.create(uri));
		manifest.addRelated(resource);
		manifest.addRelated(new LabeledResource(URI.create(HOMEPAGE_ID), "Text", Format.TEXT_HTML.getLabel(), new SimpleMetadataValue(HOMEPAGE_LABEL)));

		String jsonManifest = mapper.writeValueAsString(manifest);
		System.out.println(jsonManifest);
	}

}

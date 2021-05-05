package de.intranda.api.iiif.presentation.v3;

import static org.junit.Assert.*;

import java.net.URI;
import java.util.Arrays;
import java.util.Locale;

import javax.ws.rs.core.UriBuilder;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.intranda.api.annotation.SimpleResource;
import de.intranda.api.annotation.wa.ImageResource;
import de.intranda.api.annotation.wa.TextualResource;
import de.intranda.api.annotation.wa.WebAnnotation;
import de.intranda.api.annotation.wa.collection.AnnotationPage;
import de.intranda.api.iiif.image.ImageInformation;
import de.intranda.api.iiif.image.v3.ImageInformation3;
import de.intranda.api.iiif.presentation.enums.Format;
import de.intranda.metadata.multilanguage.IMetadataValue;
import de.intranda.metadata.multilanguage.MultiLanguageMetadataValue;
import de.intranda.metadata.multilanguage.SimpleMetadataValue;

public class Collection3Test {

private static final ObjectMapper mapper = new ObjectMapper();
	
	private static final String COLLECTION_ID = "htts://viewer.goobi.io/api/v1/collections/DC/dcall";
	private static final String COLLECTION_LABEL_DE = "Alle Werke";
	private static final String COLLECTION_LABEL_EN = "All records";
	private static final String COLLECTION_DESC_DE = "Eine Sammlung aller Werke";
	
	private static final String SUBCOLLECTION_ID = "htts://viewer.goobi.io/api/v1/collections/DC/dcall.ocr";
	private static final String SUBCOLLECTION_LABEL = "OCR";
	private static final String MANIFEST_ID = "htts://viewer.goobi.io/api/v1/records/1234/manifest";
	private static final String MANIFEST_LABEL_EN = "Title of my record";
	private static final String MANIFEST_LABEL_DE = "Der Titel meines Werkes";




	private Collection3 collection;
	private JSONObject jCollection;
	
	@Before
	public void setUp() throws JsonProcessingException {
	
		collection = new Collection3(URI.create(COLLECTION_ID), "dcall");
		IMetadataValue label = new MultiLanguageMetadataValue();
		label.setValue(COLLECTION_LABEL_DE, "de");
		label.setValue(COLLECTION_LABEL_EN, "en");
        collection.setLabel(label);
        
        IMetadataValue summary = new MultiLanguageMetadataValue();
        summary.setValue(COLLECTION_DESC_DE, "de");
        collection.setDescription(summary);
		
		Collection3 subCollection = new Collection3(URI.create(SUBCOLLECTION_ID), "dcall.ocr");
		subCollection.setLabel(new SimpleMetadataValue(SUBCOLLECTION_LABEL));
		collection.addItem(subCollection);
		
		Manifest3 manifest = new Manifest3(URI.create(MANIFEST_ID));
		IMetadataValue labelManifest = new MultiLanguageMetadataValue();
		labelManifest.setValue(MANIFEST_LABEL_DE, "de");
		labelManifest.setValue(MANIFEST_LABEL_EN, "en");
		manifest.setLabel(labelManifest);
        
		URI thumbnailServiceId = UriBuilder.fromUri(MANIFEST_ID).path("representative").build();
		URI thumbnailId = UriBuilder.fromUri(thumbnailServiceId).path("full").path("!400,400").path("0").path("default.jpg").build();
		ImageInformation info = new ImageInformation3(thumbnailServiceId);
		ImageResource thumbnail = new ImageResource(thumbnailId, Format.IMAGE_JPEG, info);
		manifest.addThumbnail(thumbnail);
		collection.addItem(manifest);
				
		String json = mapper.writeValueAsString(collection);
		// System.out.println(json);
		jCollection = new JSONObject(json);
	}
	
	@Test
	public void testCollectionBase() {
		assertEquals(COLLECTION_ID, jCollection.getString("id"));
		assertEquals("Collection", jCollection.getString("type"));
		assertEquals(COLLECTION_LABEL_DE, getText(jCollection.getJSONObject("label"), "de"));
		assertEquals(COLLECTION_LABEL_EN, getText(jCollection.getJSONObject("label"), "en"));
	}
	
	@Test
	public void testItems() {
		
		JSONArray items = jCollection.getJSONArray("items");
		assertEquals(2, items.length());
		
		JSONObject jSubCollection = items.getJSONObject(0);
		JSONObject jManifest = items.getJSONObject(1);
		
		assertEquals(SUBCOLLECTION_ID, jSubCollection.getString("id"));
		assertEquals("Collection", jSubCollection.getString("type"));
		assertEquals(SUBCOLLECTION_LABEL, getText(jSubCollection.getJSONObject("label"), null));
		
		assertEquals(MANIFEST_ID, jManifest.getString("id"));
		assertEquals("Manifest", jManifest.getString("type"));
		assertEquals(MANIFEST_LABEL_DE, getText(jManifest.getJSONObject("label"), "de"));
		assertEquals(MANIFEST_LABEL_EN, getText(jManifest.getJSONObject("label"), "en"));
		
		JSONObject thumbnail = jManifest.getJSONArray("thumbnail").getJSONObject(0);
		assertTrue(thumbnail.getString("id").endsWith("default.jpg"));
		assertEquals("Image", thumbnail.getString("type"));
		assertEquals("image/jpeg", thumbnail.getString("format"));

	}
	
	private String getText(JSONObject value, String language) {
		if(language != null) {
			return value.getJSONArray(language).getString(0);
		} else {
			return value.getJSONArray("none").getString(0);
		}
	}

}

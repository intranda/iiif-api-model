package de.intranda.api.iiif.presentation.v3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.intranda.api.iiif.image.ImageInformation;
import de.intranda.api.iiif.image.v3.ImageInformation3;
import de.intranda.api.iiif.presentation.v2.content.ImageContent;
import de.intranda.metadata.multilanguage.Metadata;
import de.intranda.metadata.multilanguage.MultiLanguageMetadataValue;
import de.intranda.metadata.multilanguage.SimpleMetadataValue;

public class Manifest3Test {

	private static final String[] LABEL_EN = {"en", "Title of my record"};
	private static final String[] LABEL_DE = {"de", "Der Titel meines Werkes"};
	
	private static final String DESCRIPTION = "Description of my record";

	private static final String[] MD1_DE_NAME = {"de", "Erstes Metadatum"};
	private static final String[] MD1_DE_VALUE = {"de", "Inhalt Erstes Metadatum"};
	private static final String[] MD1_EN_NAME = {"en", "First metadata"};
	private static final String[] MD1_EN_VALUE = {"en", "Value of first metadata"};

	private static final String[] MD2_DE_NAME = {"de", "Zweites Metadatum"};
	private static final String[] MD2_DE_VALUE = {"de", "Inhalt zweites Metadatum"};

	private static final String MD3_NAME = "Drittes Metadatum";
	private static final String MD3_VALUE = "Inhalt Drittes Metadatum";


	
	private static final ObjectMapper mapper = new ObjectMapper();
	private JSONObject jManifest;
	private Manifest3 manifest;
	
	@Before
	public void setUp() throws JsonProcessingException {
		
		URI manifestId = URI.create("htts://viewer.goobi.io/api/v1/records/1234/manifest");
		
		manifest = new Manifest3(manifestId);
		manifest.setContext(IPresentationModelElement3.CONTEXT);
		manifest.setLabel(new MultiLanguageMetadataValue(LABEL_EN));
		manifest.setDescription(new SimpleMetadataValue(DESCRIPTION));
		
		manifest.addMetadata(new Metadata(new MultiLanguageMetadataValue(MD1_DE_NAME, MD1_EN_NAME), 
									      new MultiLanguageMetadataValue(MD1_DE_VALUE, MD1_EN_VALUE)));
		
		manifest.addMetadata(new Metadata(new MultiLanguageMetadataValue(MD2_DE_NAME), 
			      						  new MultiLanguageMetadataValue(MD2_DE_VALUE)));
		
		manifest.addMetadata(new Metadata(new SimpleMetadataValue(MD3_NAME), 
				  						  new SimpleMetadataValue(MD3_VALUE)));
		
		URI thumbnailId = UriBuilder.fromUri(manifestId).path("representative").build();
		ImageContent thumbnail = new ImageContent(UriBuilder.fromUri(thumbnailId).path("full").path("!400,400").path("0").path("default.jpg").build());
		ImageInformation info = new ImageInformation3(thumbnailId);
		thumbnail.setService(info);
		manifest.addThumbnail(thumbnail);
		
		String json = mapper.writeValueAsString(manifest);
		System.out.println(json);
		
		jManifest = new JSONObject(json);
	}
	
	@Test
	public void testManifestBase() {
		assertEquals("http://iiif.io/api/presentation/3/context.json", jManifest.getString("@context"));
		assertEquals("htts://viewer.goobi.io/api/v1/records/1234/manifest", jManifest.getString("id"));
		assertEquals("Manifest", jManifest.getString("type"));
	}
	
	@Test
	public void testLabel() {
		assertTrue(jManifest.get("label") instanceof JSONObject);
		assertEquals(LABEL_EN[1], jManifest.getJSONObject("label").getJSONArray(LABEL_EN[0]).get(0));
	}
	
	@Test
	public void testSummary() {
		assertTrue(jManifest.get("summary") instanceof JSONObject);
		assertEquals(DESCRIPTION, jManifest.getJSONObject("summary").getJSONArray("none").get(0));
	}
	
	@Test
	public void testMetadata() {
		assertEquals(3, jManifest.getJSONArray("metadata").length());
		for (Object metadata : jManifest.getJSONArray("metadata")) {
			assertTrue(metadata instanceof JSONObject);
		}
	}

	@Test
	public void testThumbnail() {
		assertTrue(jManifest.get("thumbnail") instanceof JSONArray);
		JSONObject thumbnail = jManifest.getJSONArray("thumbnail").getJSONObject(0);
		assertNotNull(thumbnail);
		assertTrue(thumbnail.get("id") instanceof String);
		assertEquals("Image", thumbnail.getString("type"));
		assertEquals("image/jpeg", thumbnail.getString("format"));
		assertNotNull(thumbnail.getJSONArray("service"));
		JSONObject service = thumbnail.getJSONArray("service").getJSONObject(0);
		assertTrue(thumbnail.getString("id").startsWith(service.getString("id")));
		assertEquals("ImageService3", thumbnail.getString("type"));
		assertEquals("level2", thumbnail.getString("profile"));
	}
}

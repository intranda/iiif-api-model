package de.intranda.api.iiif.presentation.v3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import javax.ws.rs.core.UriBuilder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.intranda.api.annotation.wa.ImageResource;
import de.intranda.api.iiif.image.ImageInformation;
import de.intranda.api.iiif.image.v3.ImageInformation3;
import de.intranda.api.iiif.presentation.enums.Format;
import de.intranda.api.iiif.presentation.enums.ViewingHint;
import de.intranda.api.iiif.search.SearchService;
import de.intranda.metadata.multilanguage.IIIF3Metadata;
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
	private final static String NAVDATE_STRING = "1856-01-01T00:00:00Z";
	private final static LocalDateTime NAVDATE = LocalDateTime.of(1856, 1, 1, 0, 0);
	private final static String LICENCE = "https://creativecommons.org/licenses/by/4.0/";
	private final static String REQUIRED_LABEL = "To show:";
	private final static String REQUIRED_VALUE = "This must be shown";

	private static final String PROVIDER_LABEL = "intranda";
	private static final String PROVIDER_ID = "http://www.intranda.com";
	private static final String PROVIDER_LOGO = "https://www.intranda.com/wp-content/uploads/2014/07/intranda_logo_blue_2014.png";
	
	private static final String HOMEPAGE_LABEL = "viewImage";
	private static final String HOMEPAGE_ID = "http://www.viewer.goobi.io/images/1234/";
	
	private static final String SEARCH_SERVICE_ID = "htts://viewer.goobi.io/api/v1/records/1234/manifest/search";
	private static final String AUTOCOMPLETE_SERVICE_ID = "htts://viewer.goobi.io/api/v1/records/1234/manifest/autocomplete";

	private static final String METS_URL = "htts://viewer.goobi.io/api/v1/records/1234/meta.xml";
	private static final String METS_LABEL = "METS/MODS";
	private static final String METS_PROFILE = "http://www.loc.gov/METS/";

	
	private static final String VIEWER_URL = "htts://viewer.goobi.io/image/1234/";
	private static final String VIEWER_LABEL = "goobi viewer";
	
	private static final String COLLECTION_ID = "htts://viewer.goobi.io/collections/mycollection/";
	private static final String COLLECTION_LABEL = "Meine Sammlung";
	
	private static final String START_CANVAS_ID = "htts://viewer.goobi.io/records/1234/pages/3/canvas";


	@Before
	public void setUp() throws JsonProcessingException {
		
		
		URI manifestId = URI.create("htts://viewer.goobi.io/api/v1/records/1234/manifest");

		
		manifest = new Manifest3(manifestId);
		manifest.setContext(IPresentationModelElement3.CONTEXT);
		manifest.setLabel(new MultiLanguageMetadataValue(LABEL_EN));
		manifest.setDescription(new SimpleMetadataValue(DESCRIPTION));
		
		manifest.addMetadata(new IIIF3Metadata(new MultiLanguageMetadataValue(MD1_DE_NAME, MD1_EN_NAME), 
									      new MultiLanguageMetadataValue(MD1_DE_VALUE, MD1_EN_VALUE)));
		
		manifest.addMetadata(new IIIF3Metadata(new MultiLanguageMetadataValue(MD2_DE_NAME), 
			      						  new MultiLanguageMetadataValue(MD2_DE_VALUE)));
		
		manifest.addMetadata(new IIIF3Metadata(new SimpleMetadataValue(MD3_NAME), 
				  						  new SimpleMetadataValue(MD3_VALUE)));
		
		URI thumbnailServiceId = UriBuilder.fromUri(manifestId).path("representative").build();
		URI thumbnailId = UriBuilder.fromUri(thumbnailServiceId).path("full").path("!400,400").path("0").path("default.jpg").build();
		ImageInformation info = new ImageInformation3(thumbnailServiceId);
		ImageResource thumbnail = new ImageResource(thumbnailId, Format.IMAGE_JPEG, info);
		manifest.addThumbnail(thumbnail);
		
		manifest.addBehavior(ViewingHint.paged);
		manifest.setNavDate(NAVDATE);
		manifest.setRights(URI.create(LICENCE));
		manifest.setRequiredStatement(new IIIF3Metadata(new SimpleMetadataValue(REQUIRED_LABEL), new SimpleMetadataValue(REQUIRED_VALUE)));
		
		IIIFAgent provider = new IIIFAgent(URI.create(PROVIDER_ID), new SimpleMetadataValue(PROVIDER_LABEL));
		provider.addLogo(new ImageResource(URI.create(PROVIDER_LOGO), Format.IMAGE_JPEG.getLabel()));
		manifest.addProvider(provider);
		
		manifest.addHomepage(new LabeledResource(URI.create(HOMEPAGE_ID), "Text", Format.TEXT_HTML.getLabel(), new SimpleMetadataValue(HOMEPAGE_LABEL)));
		
		SearchService search = new SearchService1(URI.create(SEARCH_SERVICE_ID));
		AutoCompleteService1 autoComplete = new AutoCompleteService1(URI.create(AUTOCOMPLETE_SERVICE_ID));
		manifest.addService(search);
		manifest.addService(autoComplete);
		
		LabeledResource mets = new LabeledResource(URI.create(METS_URL), "Dataset", Format.TEXT_XML.getLabel(), METS_PROFILE, new SimpleMetadataValue(METS_LABEL));
		manifest.addSeeAlso(mets);

		manifest.addRendering(new LabeledResource(URI.create(VIEWER_URL), "Text", Format.TEXT_HTML.getLabel(), new SimpleMetadataValue(VIEWER_LABEL)));

		Collection3 collection = new Collection3(URI.create(COLLECTION_ID), COLLECTION_LABEL);
		collection.setLabel(new SimpleMetadataValue(COLLECTION_LABEL));
		manifest.addWithin(collection);
		
		Canvas3 startCanvas = new Canvas3(URI.create(START_CANVAS_ID));
		manifest.setStart(startCanvas);
		
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
		assertEquals("ImageService3", service.getString("type"));
		assertEquals("level2", service.getString("profile"));
	}
	
	@Test
	public void testViewingDirection() {
		assertEquals("left-to-right", jManifest.getString("viewingDirection"));
	}
	
	@Test
	public void testBehavior() {
		assertEquals("paged", jManifest.getJSONArray("behavior").get(0));
	}
	
	@Test
	public void testNavDate() {
		assertEquals(NAVDATE_STRING, jManifest.getString("navDate"));
	}
	
	@Test
	public void testRights() {
		assertEquals(LICENCE, jManifest.getString("rights"));
	}

	@Test
	public void testRequiredStatement() {
		assertEquals(this.REQUIRED_LABEL, jManifest.getJSONObject("requiredStatement").getJSONObject("label").getJSONArray("none").get(0));
		assertEquals(this.REQUIRED_VALUE, jManifest.getJSONObject("requiredStatement").getJSONObject("value").getJSONArray("none").get(0));

	}
	
	@Test
	public void testProvider() {
		JSONObject provider = jManifest.getJSONArray("provider").getJSONObject(0);
		JSONObject logo = provider.getJSONArray("logo").getJSONObject(0);
		
		assertNotNull(provider);
		assertNotNull(logo);
		assertEquals(PROVIDER_ID, provider.getString("id"));
		assertEquals(PROVIDER_LABEL, provider.getJSONObject("label").getJSONArray("none").get(0));
		assertEquals("Agent", provider.getString("type"));
		
		assertEquals(PROVIDER_LOGO, logo.getString("id"));
		assertEquals("Image", logo.getString("type"));
		assertEquals(Format.IMAGE_JPEG.getLabel(), logo.getString("format"));
	}
	
	@Test
	public void testHomepage() {
		JSONObject homepage = jManifest.getJSONArray("homepage").getJSONObject(0);
		assertNotNull(homepage);
		assertEquals(HOMEPAGE_ID, homepage.getString("id"));
		assertEquals("Text", homepage.getString("type"));
		assertEquals("text/html", homepage.getString("format"));
		assertEquals(HOMEPAGE_LABEL, homepage.getJSONObject("label").getJSONArray("none").getString(0));
	}
	
	@Test
	public void testService() {
		
		JSONArray services = jManifest.getJSONArray("service");
		assertEquals(2, services.length());
		
		
		JSONObject searchService = services.getJSONObject(0);
		assertNotNull(searchService);
		assertEquals(SEARCH_SERVICE_ID, searchService.getString("@id"));
		assertEquals("SearchService1", searchService.getString("@type"));
		
		JSONObject autoCompleteService = services.getJSONObject(1);
		assertNotNull(autoCompleteService);
		assertEquals(AUTOCOMPLETE_SERVICE_ID, autoCompleteService.getString("@id"));
		assertEquals("AutoCompleteService1", autoCompleteService.getString("@type"));
	}

	@Test
	public void testSeeAlso() {
		
		JSONArray seeAlso = jManifest.getJSONArray("seeAlso");
		assertEquals(1, seeAlso.length());
		
		
		JSONObject mets = seeAlso.getJSONObject(0);
		assertEquals(METS_URL, mets.getString("id"));
		assertEquals("Dataset", mets.getString("type"));
		assertEquals(METS_LABEL, getText(mets.getJSONObject("label"), null));
		assertEquals(Format.TEXT_XML.getLabel(), mets.getString("format"));
		assertEquals(METS_PROFILE, mets.getString("profile"));
	
	}
	
	@Test
	public void testRendering() {
		
		JSONArray rendering = jManifest.getJSONArray("rendering");
		assertEquals(1, rendering.length());
		
		JSONObject viewer = rendering.getJSONObject(0);
		assertEquals(VIEWER_URL, viewer.getString("id"));
		assertEquals("Text", viewer.getString("type"));
		assertEquals(VIEWER_LABEL, getText(viewer.getJSONObject("label"), null));
		assertEquals(Format.TEXT_HTML.getLabel(), viewer.getString("format"));
		
	}
	

	@Test
	public void testPartOf() {
		
		JSONArray collections = jManifest.getJSONArray("partOf");
		assertEquals(1, collections.length());
		JSONObject collection = collections.getJSONObject(0);
		assertEquals(COLLECTION_ID, collection.getString("id"));
		assertEquals("Collection", collection.getString("type"));
		assertEquals(COLLECTION_LABEL, getText(collection.getJSONObject("label"), null));
	}
	
	@Test
	public void testStart() {
		
		JSONObject start = jManifest.getJSONObject("start");
		assertEquals(START_CANVAS_ID, start.getString("id"));
		assertEquals("Canvas", start.getString("type"));		
	}
	
	private String getText(JSONObject value, String language) {
		if(language != null) {
			return value.getJSONArray(language).getString(0);
		} else {
			return value.getJSONArray("none").getString(0);
		}
	}
}

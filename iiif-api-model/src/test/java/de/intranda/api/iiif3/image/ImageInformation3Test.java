package de.intranda.api.iiif3.image;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Dimension;
import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.intranda.api.iiif.image.ImageProfile;
import de.intranda.api.iiif.image.ImageTile;
import de.intranda.api.iiif.image.PhysicalDimension;
import de.intranda.api.iiif.image.PhysicalDimension.ResolutionUnit;
import de.intranda.api.iiif.image.Service;
import de.intranda.metadata.multilanguage.IMetadataValue;
import de.intranda.metadata.multilanguage.MultiLanguageMetadataValue;

public class ImageInformation3Test {

	ImageInformation3 info;

	static final String ID = "https://iiif.goobi.io/images/sample";
	static final int HEIGHT = 2000;
	static final int WIDTH = 1500;
	static final String GERMAN_LABEL = "Bildtitel";
	static final String ENGLISH_LABEL = "Image title";
	static final IMetadataValue LABEL = new MultiLanguageMetadataValue(new String[][]{{"de", GERMAN_LABEL}, {"en", ENGLISH_LABEL}});
	static final String RIGHTS = "@goobi";
	static final Service PHYSICAL_DIMENSION_SERVICE = new PhysicalDimension(200f, ResolutionUnit.inch);
	static final Dimension SIZE_1 = new Dimension(1000, 750);	
	static final Dimension SIZE_2 = new Dimension(200, 150);	
	static final int TILE_SIZE = 512;
	static final Integer[] TILE_SCALE_FACTORS = {1, 32, 128};
	static final int MAX_WIDTH = 1000;
	static final int MAX_HEIGHT = 1000;
	static final int MAX_AREA = MAX_WIDTH * MAX_HEIGHT;
	static final String[] PREFERRED_FORMAT = {"tif", "jpg"};
	
	JSONObject json;

	@Before
	public void setUp() {
		info = new ImageInformation3(ID);
		info.setAttribution("foobar");	//should be excluded in json response
		info.setHeight(HEIGHT);
		info.setWidth(WIDTH);
		info.setLabel(LABEL);
		info.setLicense(RIGHTS);
		info.setLogo("foobar");	//should be excluded in json response
		info.addService(PHYSICAL_DIMENSION_SERVICE);
		info.addProfile(new ImageProfile(Arrays.asList("tif"), Arrays.asList("gray", "bitonal")));	//should be excluded in json response
		info.setSizesFromDimensions(Arrays.asList(SIZE_1, SIZE_2));
		info.addTile(new ImageTile(TILE_SIZE, TILE_SIZE, Arrays.asList(TILE_SCALE_FACTORS)));
		info.setMaxWidth(MAX_WIDTH);
		info.setMaxHeight(MAX_HEIGHT);
		info.setMaxArea(MAX_AREA);
		info.setPreferredFormats(PREFERRED_FORMAT);
		
		generateJson();
	}

	private void generateJson() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			json = new JSONObject(mapper.writeValueAsString(info));
		} catch (JsonProcessingException | JSONException e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testJson() throws JsonProcessingException, JSONException {
		assertNotNull(json);
	}
	
	@Test
	public void testContext() {
		assertEquals("http://iiif.io/api/image/3/context.json", json.getString("@context"));
	}
	
	@Test
	public void testId() {
		assertEquals(ID,json.getString("id"));
	}
	
	@Test
	public void testType() {
		assertEquals("ImageService3", json.getString("type"));
	}
	
	@Test
	public void testProtocol() {
		assertEquals("http://iiif.io/api/image", json.getString("protocol"));
	}
	
	@Test
	public void testProfile() {
		assertEquals("level2", json.getString("profile"));
	}
	
	@Test
	public void testWidth() {
		assertEquals(WIDTH, json.getInt("width"));
	}
	
	@Test
	public void testHeight() {
		assertEquals(HEIGHT, json.getInt("height"));
	}
	
	@Test
	public void testMaxWidth() {
		assertEquals(MAX_WIDTH, json.getInt("maxWidth"));
	}
	
	@Test
	public void testMaxHeight() {
		assertEquals(MAX_HEIGHT, json.getInt("maxHeight"));
	}
	
	@Test
	public void testMaxArea() {
		assertEquals(MAX_AREA, json.getInt("maxArea"));
	}
	
	@Test
	public void testLabel() {
		assertEquals(ENGLISH_LABEL, json.getJSONObject("label").getJSONArray("en").get(0));
		assertEquals(GERMAN_LABEL, json.getJSONObject("label").getJSONArray("de").get(0));
	}
	
	@Test
	public void testSimpleLabel() {
		
		String label = "TITEL";
		info.setLabel(label);
		generateJson();
		
		assertEquals(label, json.getString("label"));
	}
	
	@Test
	public void testRights() {
		assertEquals(RIGHTS, json.getString("rights"));
	}
	
	@Test
	public void testExtraFormats() {
		assertEquals("Extra formats has wrong size: " + json.getJSONArray("extraFormats"), 1, json.getJSONArray("extraFormats").length());
		assertTrue("Extra formats is missing formats: " + json.getJSONArray("extraFormats"), json.getJSONArray("extraFormats").toList().contains("tif"));
	}
	
	@Test
	public void testPreferredFormats() {
		assertEquals("Preferred formats has wrong size: " + json.getJSONArray("preferredFormats"), 2, json.getJSONArray("preferredFormats").length());
		assertTrue("Preferred formats is missing formats: " + json.getJSONArray("preferredFormats"), json.getJSONArray("preferredFormats").toList().contains("tif"));
		assertTrue("Preferred formats is missing formats: " + json.getJSONArray("preferredFormats"), json.getJSONArray("preferredFormats").toList().contains("jpg"));
	}
	
	@Test
	public void testExtraFeatures() {
		assertFalse("Not extra features found ", json.getJSONArray("extraFeatures").toList().isEmpty());
	}
	
	@Test
	public void testQualities() {
		assertFalse("Not extra qualities found ", json.getJSONArray("extraQualities").toList().isEmpty());
	}
	
	@Test
	public void testService() {
		assertEquals(1, json.getJSONArray("service").length());
		JSONObject service = json.getJSONArray("service").getJSONObject(0);
		assertEquals("http://iiif.io/api/annex/services/physdim/1/context.json", service.getString("@context"));
		assertEquals("http://iiif.io/api/annex/services/physdim", service.getString("profile"));
		assertNotNull(service.getFloat("physicalScale"));
		assertNotNull(service.getString("physicalUnits"));
	}
 
	@Test
	public void testSizes() {
		assertEquals(2, json.getJSONArray("sizes").length());
		for(Object element : json.getJSONArray("sizes")) {
			JSONObject size = (JSONObject)element;
			assertNotNull(size.getInt("width"));
			assertNotNull(size.getInt("height"));
		}
	}
	
	@Test 
	public void testTiles() {
		assertEquals(1, json.getJSONArray("tiles").length());
		for(Object element : json.getJSONArray("tiles")) {
			JSONObject tile = (JSONObject)element;
			assertEquals(TILE_SIZE, tile.getInt("width"));
			assertEquals(Arrays.asList(TILE_SCALE_FACTORS), tile.getJSONArray("scaleFactors").toList());
		}
	}
}

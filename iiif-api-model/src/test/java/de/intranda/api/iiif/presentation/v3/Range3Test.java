package de.intranda.api.iiif.presentation.v3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.intranda.metadata.multilanguage.SimpleMetadataValue;

public class Range3Test {

private static final ObjectMapper mapper = new ObjectMapper();
	
	private static final String RANGE_1_ID = "htts://viewer.goobi.io/api/v1/records/1234/sections/LOG_0001/range";
	private static final String RANGE_1_LABEL = "Erstes Kapitel";
	private static final String RANGE_2_ID = "htts://viewer.goobi.io/api/v1/records/1234/sections/LOG_0002/range";
	private static final String RANGE_2_LABEL = "Erstes Unterkapitel";
	private static final String RANGE_3_ID = "htts://viewer.goobi.io/api/v1/records/1234/sections/LOG_0003/range";
	private static final String RANGE_3_LABEL = "Zweites Unterkapitel";
	private static final String RANGE_2_CANVAS_1_ID = "htts://viewer.goobi.io/api/v1/records/1234/pages/1/canvas";
	private static final String RANGE_2_CANVAS_2_ID = "htts://viewer.goobi.io/api/v1/records/1234/pages/2/canvas";
	private static final String RANGE_3_CANVAS_1_ID = "htts://viewer.goobi.io/api/v1/records/1234/pages/3/canvas";
	
	
	
	private Range3 range;
	private JSONObject jRange;
	
	@Before
	public void setUp() throws JsonProcessingException {
	
		Range3 range  = new Range3(RANGE_1_ID);
		range.setLabel(new SimpleMetadataValue(RANGE_1_LABEL));
		
		Range3 range2  = new Range3(RANGE_2_ID);
		range2.setLabel(new SimpleMetadataValue(RANGE_2_LABEL));
		
		Range3 range3  = new Range3(RANGE_3_ID);
		range3.setLabel(new SimpleMetadataValue(RANGE_3_LABEL));
		
		Canvas3 canvas1 = new Canvas3(RANGE_2_CANVAS_1_ID);
		Canvas3 canvas2 = new Canvas3(RANGE_2_CANVAS_2_ID);
		Canvas3 canvas3 = new Canvas3(RANGE_3_CANVAS_1_ID);
		
		range.addItem(range2);
		range.addItem(range3);
		
		range2.addItem(canvas1);
		range2.addItem(canvas2);
		range3.addItem(canvas3);
        
		String json = mapper.writeValueAsString(range);
		// System.out.println(json);
		
		jRange = new JSONObject(json);
	}
	
	@Test
	public void testRangeBase() {
		assertEquals(RANGE_1_ID, jRange.getString("id"));
		assertEquals("Range", jRange.getString("type"));
		assertEquals(RANGE_1_LABEL, getText(jRange.getJSONObject("label"), null));
	}
	
	@Test
	public void testItems() {
		
		JSONArray items = jRange.getJSONArray("items");
		assertEquals(2, items.length());
		
		JSONObject jRange2 = items.getJSONObject(0);
		JSONObject jRange3 = items.getJSONObject(1);
		
		assertEquals(RANGE_2_ID, jRange2.getString("id"));
		assertEquals("Range", jRange2.getString("type"));
		assertEquals(2, jRange2.getJSONArray("items").length());
		
		assertEquals(RANGE_3_ID, jRange3.getString("id"));
		assertEquals("Range", jRange3.getString("type"));
		assertEquals(1, jRange3.getJSONArray("items").length());

	}
	
	private String getText(JSONObject value, String language) {
		if(language != null) {
			return value.getJSONArray(language).getString(0);
		} else {
			return value.getJSONArray("none").getString(0);
		}
	}

}

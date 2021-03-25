package de.intranda.api.iiif.presentation.v3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.util.Arrays;
import java.util.Locale;

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
import de.intranda.api.iiif.presentation.enums.Format;
import de.intranda.metadata.multilanguage.IMetadataValue;
import de.intranda.metadata.multilanguage.MultiLanguageMetadataValue;

public class Canvas3Test {

	private static final ObjectMapper mapper = new ObjectMapper();
	
	private static final String CANVAS_ID = "htts://viewer.goobi.io/api/v1/records/1234/pages/1/canvas";
	private static final String CANVAS_LABEL_DE = "Seite 1";
	private static final String CANVAS_LABEL_EN = "page 1";
	private static final int CANVAS_WIDTH = 1500;
	private static final int CANVAS_HEIGHT = 2500;
	
	private static final String IMAGE_ID = "htts://viewer.goobi.io/api/v1/records/1234/files/images/00000001.tif";
	private static final String THUMBNAIL_ID = IMAGE_ID + "/full/!400,400/0/default.jpg";

	private static final String FULLTEXT_LIST_ID = "htts://viewer.goobi.io/api/v1/records/1234/pages/1/list/fulltext";
	private static final String FULLTEXT_ID = "htts://viewer.goobi.io/api/v1/records/1234/pages/1/fulltext";
	private static final String FULLTEXT_VALUE = "Lorem ipsum dolor...";
	private static final String COMMENT_LIST_ID = "htts://viewer.goobi.io/api/v1/records/1234/pages/1/list/comments";
	private static final String COMMENT_ID_1 = "htts://viewer.goobi.io/api/v1/annotations/1";
	private static final String COMMENT_ID_2 = "htts://viewer.goobi.io/api/v1/annotations/2";
	private static final String COMMENT_VALUE_1 = "Ken sent me";
	private static final String COMMENT_VALUE_2 = "rosebud";
	
	private static final String FULLTEXT_SOURCE_ID = "htts://viewer.goobi.io/api/v1/records/1234/files/plaintext/00000001.txt";
	private static final String ALTO_SOURCE_ID = "htts://viewer.goobi.io/api/v1/records/1234/files/alto/00000001.xml";
	private static final String PDF_SOURCE_ID = "htts://viewer.goobi.io/api/v1/records/1234/files/pdf/00000001.pdf";
	private static final String ALTO_PROFILE = "http://www.loc.gov/standards/alto/ns-v2";



	private Canvas3 canvas;
	private JSONObject jCanvas;
	
	@Before
	public void setUp() throws JsonProcessingException {
	
		canvas = new Canvas3(CANVAS_ID);
		canvas.setWidth(CANVAS_WIDTH);
		canvas.setHeight(CANVAS_HEIGHT);
		
		IMetadataValue label = new MultiLanguageMetadataValue();
		label.setValue(CANVAS_LABEL_DE, Locale.GERMAN);
		label.setValue(CANVAS_LABEL_EN, Locale.ENGLISH);
		canvas.setLabel(label);
		
        canvas.addMedia(new ImageResource(IMAGE_ID, 400, 400));

        WebAnnotation fulltext = new WebAnnotation(URI.create(FULLTEXT_ID));
        fulltext.setBody(new TextualResource(FULLTEXT_VALUE));
        fulltext.setTarget(new SimpleResource(URI.create(CANVAS_ID)));
        AnnotationPage fulltexts = new AnnotationPage(URI.create(FULLTEXT_LIST_ID), false);
        fulltexts.setItems(Arrays.asList(fulltext));
        canvas.addAnnotations(fulltexts);
        
        WebAnnotation comment1 = new WebAnnotation(URI.create(COMMENT_ID_1));
        comment1.setBody(new TextualResource(COMMENT_VALUE_1));
        comment1.setTarget(new SimpleResource(URI.create(CANVAS_ID)));
        WebAnnotation comment2 = new WebAnnotation(URI.create(COMMENT_ID_2));
        comment2.setBody(new TextualResource(COMMENT_VALUE_2));
        comment2.setTarget(new SimpleResource(URI.create(CANVAS_ID)));
        AnnotationPage comments = new AnnotationPage(URI.create(COMMENT_LIST_ID), false);
        comments.setItems(Arrays.asList(comment1, comment2));
        canvas.addAnnotations(comments);
		
        canvas.addRendering(new LabeledResource(URI.create(PDF_SOURCE_ID), "Text", Format.APPLICATION_PDF.getLabel(), null ));
        canvas.addRendering(new LabeledResource(URI.create(FULLTEXT_SOURCE_ID), "Text", Format.TEXT_PLAIN.getLabel(), null ));
        canvas.addSeeAlso(new LabeledResource(URI.create(ALTO_SOURCE_ID), "Dataset", Format.TEXT_XML.getLabel(), ALTO_PROFILE, null ));
        
		String json = mapper.writeValueAsString(canvas);
		System.out.println(json);
		
		jCanvas = new JSONObject(json);
	}
		
	
	@Test
	public void testCanvasBase() {
		assertEquals(CANVAS_ID, jCanvas.getString("id"));
		assertEquals("Canvas", jCanvas.getString("type"));
		assertEquals(CANVAS_WIDTH, jCanvas.getInt("width"));
		assertEquals(CANVAS_HEIGHT, jCanvas.getInt("height"));
	}
	
	@Test
	public void testLabel() {
		assertEquals(CANVAS_LABEL_DE, getText(jCanvas.getJSONObject("label"), "de"));
		assertEquals(CANVAS_LABEL_EN, getText(jCanvas.getJSONObject("label"), "en"));
	}
	
	@Test
	public void testImage() {
		JSONArray items = jCanvas.getJSONArray("items");
		assertEquals(1, items.length());
		JSONObject page = items.getJSONObject(0);
		assertEquals("AnnotationPage", page.getString("type"));
		assertTrue(StringUtils.isNotBlank(page.getString("id")));
		
		System.out.println(page.toString());
		
		JSONArray pageItems = page.getJSONArray("items");
		assertEquals(1, pageItems.length());
		JSONObject annotation = pageItems.getJSONObject(0);
		assertTrue(StringUtils.isNotBlank(annotation.getString("id")));
		assertEquals("Annotation", annotation.getString("type"));
		assertEquals("painting", annotation.getString("motivation"));
		
		String target = annotation.getString("target");
		assertEquals(CANVAS_ID, target);
		
		JSONObject body = annotation.getJSONObject("body");
		assertEquals(THUMBNAIL_ID, body.getString("id"));
		assertEquals("Image", body.getString("type"));
		assertEquals("image/jpeg", body.getString("format"));
		
		JSONObject service = body.getJSONArray("service").getJSONObject(0);
		assertEquals(IMAGE_ID, service.getString("id"));
		assertEquals("ImageService3", service.getString("type"));
		assertEquals("level2", service.getString("profile"));
		
		
	}
	
	@Test
	public void testAnnotations() {
		JSONArray items = jCanvas.getJSONArray("annotations");
		assertEquals(2, items.length());
		
		JSONObject textPage = items.getJSONObject(0);
		assertEquals(FULLTEXT_LIST_ID, textPage.getString("id"));
		assertEquals("AnnotationPage", textPage.getString("type"));
		JSONArray textItems = textPage.getJSONArray("items");
		assertEquals(1, textItems.length());
		assertEquals(FULLTEXT_ID, textItems.getJSONObject(0).getString("id"));
		assertEquals("Annotation", textItems.getJSONObject(0).getString("type"));
		
		JSONObject commenPage = items.getJSONObject(1);
		assertEquals(COMMENT_LIST_ID, commenPage.getString("id"));
		assertEquals("AnnotationPage", commenPage.getString("type"));
		JSONArray comments = commenPage.getJSONArray("items");
		assertEquals(2, comments.length());
		assertEquals(COMMENT_ID_1, comments.getJSONObject(0).getString("id"));
		assertEquals("Annotation", comments.getJSONObject(0).getString("type"));
		assertEquals(COMMENT_ID_2, comments.getJSONObject(1).getString("id"));
		assertEquals("Annotation", comments.getJSONObject(1).getString("type"));
		
	}
	
	@Test
	public void testSeeAlso() {

		JSONArray seeAlsos = jCanvas.getJSONArray("seeAlso");
		assertEquals(1, seeAlsos.length());
		assertEquals(ALTO_SOURCE_ID, seeAlsos.getJSONObject(0).getString("id"));
		assertEquals("Dataset", seeAlsos.getJSONObject(0).getString("type"));
		assertEquals("text/xml", seeAlsos.getJSONObject(0).getString("format"));
		assertEquals(ALTO_PROFILE, seeAlsos.getJSONObject(0).getString("profile"));
	}
	
	@Test
	public void testRendering() {

		JSONArray rendering = jCanvas.getJSONArray("rendering");
		assertEquals(2, rendering.length());
		
		assertEquals(PDF_SOURCE_ID, rendering.getJSONObject(0).getString("id"));
		assertEquals("Text", rendering.getJSONObject(0).getString("type"));
		assertEquals("application/pdf", rendering.getJSONObject(0).getString("format"));
		
		assertEquals(FULLTEXT_SOURCE_ID, rendering.getJSONObject(1).getString("id"));
		assertEquals("Text", rendering.getJSONObject(1).getString("type"));
		assertEquals("text/plain", rendering.getJSONObject(1).getString("format"));
	}
	
	private String getText(JSONObject value, String language) {
		if(language != null) {
			return value.getJSONArray(language).getString(0);
		} else {
			return value.getJSONArray("none").getString(0);
		}
	}

}

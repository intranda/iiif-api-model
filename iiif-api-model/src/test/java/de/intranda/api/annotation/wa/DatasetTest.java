package de.intranda.api.annotation.wa;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.intranda.api.annotation.ITypedResource;

public class DatasetTest {

    File jsonFile = new File("src/test/resources/annotations/Dataset.json");
    String json;
    ObjectMapper mapper = new ObjectMapper();
    
    @Before
    public void setUp() throws Exception {
        json = FileUtils.readFileToString(jsonFile, "UTF-8");
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testRead() throws JsonMappingException, JsonProcessingException {
        ITypedResource resource = mapper.readValue(json, ITypedResource.class);
        Assert.assertTrue(resource instanceof Dataset);
        Dataset dataset = (Dataset)resource;
        Assert.assertEquals(3, dataset.getData().size());
        Assert.assertEquals("Der Titel", dataset.getData().get("MD_TITLE").get(0));
        Assert.assertEquals("Mustermann, Max", dataset.getData().get("MD_AUTHOR").get(0));
        Assert.assertEquals("Musterfrau, Magda", dataset.getData().get("MD_AUTHOR").get(1));
        Assert.assertEquals("Göttingen", dataset.getData().get("MD_PLACEOFPUBLICATION").get(0));
    }

    @Test
    public void testWrite() throws JsonProcessingException {
        
        Dataset dataset = new Dataset();
        dataset.setData("LABEL", "Die Bezeichnung");
        dataset.setData("YEAR", "1977", "2020");
        
        String string = mapper.writeValueAsString(dataset);
        JSONObject json = new JSONObject(string);
        
        Assert.assertEquals("Dataset", json.get("type"));
        Assert.assertEquals("1977", json.getJSONObject("data").getJSONArray("YEAR").get(0));
        Assert.assertEquals("2020", json.getJSONObject("data").getJSONArray("YEAR").get(1));

    }
}

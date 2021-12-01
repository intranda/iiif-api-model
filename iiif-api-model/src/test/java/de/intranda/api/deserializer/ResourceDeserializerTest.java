package de.intranda.api.deserializer;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import de.intranda.api.annotation.oa.OpenAnnotation;
import de.intranda.api.annotation.oa.SpecificResource;
import de.intranda.api.annotation.oa.TextualResource;
import de.intranda.api.annotation.wa.WebAnnotation;

public class ResourceDeserializerTest {

    @Test
    public void testDeserializeOpenAnnotationTextOnCanvasFragment() throws JsonParseException, JsonMappingException, IOException {
        
        Path jsonPath = Paths.get("src/test/resources/annotations/OA-TextOnCanvasFragment.json");
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        mapper.registerModule(new JavaTimeModule());
        
        OpenAnnotation anno = mapper.readValue(jsonPath.toFile(), OpenAnnotation.class);
        Assert.assertEquals(anno.getBody().getClass(), TextualResource.class);
        Assert.assertEquals(anno.getTarget().getClass(), SpecificResource.class);
    }
    
    @Test
    public void testDeserializeWebAnnotationTextOnCanvasFragment() throws JsonParseException, JsonMappingException, IOException {
        
        Path jsonPath = Paths.get("src/test/resources/annotations/WA-TextOnCanvasFragment.json");
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        mapper.registerModule(new JavaTimeModule());

        WebAnnotation anno = mapper.readValue(jsonPath.toFile(), WebAnnotation.class);
        Assert.assertEquals(anno.getBody().getClass(), de.intranda.api.annotation.wa.TextualResource.class);
        Assert.assertEquals(anno.getTarget().getClass(), de.intranda.api.annotation.wa.SpecificResource.class);
    }

}

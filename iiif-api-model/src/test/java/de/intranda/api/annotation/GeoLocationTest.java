package de.intranda.api.annotation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import de.intranda.api.annotation.wa.WebAnnotation;

public class GeoLocationTest {

    private final ObjectMapper mapper = new ObjectMapper();
    
    @Before
    public void setUp() throws Exception {
        mapper.registerModule(new JavaTimeModule());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testReadAnnotation() throws JsonParseException, JsonMappingException, IOException {
       Path annotationFile = Paths.get("src/test/resources/annotations/WA-GeoJson.json");
       String json = readFileToString(annotationFile.toFile(), "UTF-8");
       
       WebAnnotation annotation = mapper.readValue(json, WebAnnotation.class);
//       System.out.println("Annotation: " + annotation);
       Assert.assertTrue(annotation.getBody() instanceof GeoLocation);
    }
    
    public static String readFileToString(File file, String convertFileToCharset) throws FileNotFoundException, IOException {
        StringBuilder sb = new StringBuilder();
        try (FileInputStream fis = new FileInputStream(file)) {
            boolean charsetDetected = true;
            // logger.debug("{} charset: {}", file.getAbsolutePath(), charset);
            String charset = "ISO-8859-1";
            try (InputStreamReader in = new InputStreamReader(fis, charset); BufferedReader r = new BufferedReader(in)) {
                String line = null;
                while ((line = r.readLine()) != null) {
                    sb.append(line).append('\n');
                }
            }
            // Force conversion to target charset, if different charset detected
            if (charsetDetected && convertFileToCharset != null && !charset.equals(convertFileToCharset)) {
                try {
                    Charset toCharset = Charset.forName(convertFileToCharset);
                    FileUtils.write(file, sb.toString(), toCharset);
                } catch (UnsupportedEncodingException e) {
                }

            }
        } catch (UnsupportedEncodingException e) {
        }

        return sb.toString();
    }

}

package de.intranda.api.iiif.presentation;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.intranda.api.iiif.presentation.v2.AbstractPresentationModelElement2;
import de.intranda.api.iiif.presentation.v2.Manifest2;

public class ManifestTest {


    @Test
    public void testNavDate() {
        String dateString = "2019-06-06";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(AbstractPresentationModelElement2.DATETIME_FORMAT);
        LocalDate dateTime = LocalDate.parse(dateString);
//        System.out.println(formatter.format(dateTime.atStartOfDay(ZoneId.systemDefault())));
        
        
        String dateStringISO="2019-06-06T00:00:00Z";
        LocalDateTime time = LocalDateTime.parse(dateStringISO, formatter);
//        System.out.println(formatter.format(time));
    }
    
//    @Test
    public void testReadManifest() throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        Manifest2 manifest1 = mapper.readValue(new File("src/test/resources/manifests/bsb00079445.json"), Manifest2.class);
        Manifest2 manifest2 = mapper.readValue(new File("src/test/resources/manifests/AC08364319.json"), Manifest2.class);

    }

}

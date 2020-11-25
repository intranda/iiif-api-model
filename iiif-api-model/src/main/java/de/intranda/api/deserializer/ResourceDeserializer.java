package de.intranda.api.deserializer;

import java.awt.Rectangle;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import de.intranda.api.annotation.IResource;
import de.intranda.api.annotation.ISelector;
import de.intranda.api.annotation.SimpleResource;
import de.intranda.api.annotation.GeoLocation.Geometry;
import de.intranda.api.annotation.GeoLocation.Properties;
import de.intranda.api.annotation.GeoLocation.ViewPoint;
import de.intranda.api.annotation.wa.Dataset;
import de.intranda.api.annotation.wa.collection.AnnotationCollection;
import de.intranda.api.iiif.presentation.Manifest;

public class ResourceDeserializer extends StdDeserializer<IResource> {

    /**
     * 
     */
    private static final long serialVersionUID = -6733687272555595794L;
    
    private ObjectMapper mapper = new ObjectMapper();

    public ResourceDeserializer() {
        this(null);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    
    /**
     * @param vc
     */
    public ResourceDeserializer(Class<?> vc) {
        super(vc);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


    @Override
    public IResource deserialize(JsonParser p, DeserializationContext context) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        try {
            return parseNode(node, p, context);
        } catch (URISyntaxException e) {
            throw new JsonParseException(p, "Resource id '" + node.asText() + "' is not a URI");
        } catch (NullPointerException e) {
            throw new JsonParseException(p, "Missing json properties. Could not deserialize resource");
        }

    }

    /**
     * @param p
     * @param node
     * @param p 
     * @param context 
     * @throws IOException 
     * @throws JsonParseException
     */
    private IResource parseNode(JsonNode node, JsonParser p, DeserializationContext context) throws URISyntaxException, IOException {
        IResource resource = null;
            if (node.isTextual()) {
                resource = new SimpleResource(new URI(node.asText()));
                return resource;
            } else if (node.has("@type")) { 
                //OpenAnnotations Resource
                
                String type = node.get("@type").asText();
                switch(type) {
                    case "cnt:ContentAsText":
                        resource = new de.intranda.api.annotation.oa.TextualResource(node.get("chars").asText());
                        break;
                    case "oa:SpecificResource":
                        ISelector selector = null;
                        if(node.get("selector").get("@type").asText().equals("oa:FragmentSelector")) {     
                            selector = new de.intranda.api.annotation.oa.FragmentSelector(getRectangle(node.get("selector").get("value").asText()));
                        }
                        resource = new de.intranda.api.annotation.oa.SpecificResource(parseNode(node.get("full"), p, context).getId(), selector);
                        break;
                    default: 
                        if(node.has("format")) {
                            resource = new de.intranda.api.annotation.oa.TypedResource(new URI(node.get("@id").asText()), node.get("@type").asText(), node.get("format").asText());
                        } else {                            
                            resource = new de.intranda.api.annotation.oa.TypedResource(new URI(node.get("@id").asText()), node.get("@type").asText());
                        }
                }
            } else {    
                //WebAnnotationsResource
                
                String type = node.get("type").asText();
                switch(type) {
                    case "TextualBody":
                        if(node.has("format")) {
                            String format = node.get("format").asText();
                            resource = new de.intranda.api.annotation.wa.TextualResource(node.get("value").asText(), format);
                        } else {                            
                            resource = new de.intranda.api.annotation.wa.TextualResource(node.get("value").asText());
                        }
                        break;
                    case "SpecificResource":
                        ISelector selector = null;
                        if(node.get("selector").get("type").asText().equals("FragmentSelector")) {     
                            selector = new de.intranda.api.annotation.wa.FragmentSelector(getRectangle(node.get("selector").get("value").asText()));
                        }
                        resource = new de.intranda.api.annotation.wa.SpecificResource(parseNode(node.get("source"), p, context).getId(), selector);
                        break;
                    case "Feature": //geoJson
                        Geometry geometry = new Geometry(getAsDoubleArray(node.get("geometry").withArray("coordinates")), node.get("geometry").get("type").asText());
                        Properties properties = new Properties(Optional.ofNullable(node.get("properties").get("name")).map(JsonNode::asText).orElse(""));
                        ViewPoint view = new ViewPoint(node.get("view").get("zoom").asDouble(), getAsDoubleArray(node.get("view").withArray("center")));
                        resource = new de.intranda.api.annotation.GeoLocation(geometry, properties, view);
                        break;
                    case "Dataset":
                    case "dataset":
                        resource = mapper.convertValue(node, Dataset.class);
                        break;
                    default: 
                        if(node.has("format")) {
                            resource = new de.intranda.api.annotation.wa.TypedResource(new URI(node.get("id").asText()), node.get("type").asText(), node.get("format").asText());
                        } else {                            
                            resource = new de.intranda.api.annotation.wa.TypedResource(new URI(node.get("id").asText()), node.get("type").asText());
                        }
                }

            }
            return resource;
    }

    private double[] getAsDoubleArray(JsonNode withArray) {
        double[] d = new double[withArray.size()];
        for(int i = 0; i < withArray.size(); i++) {
            d[i] = withArray.get(i).asDouble();
        }
        return d;
    }

    private Rectangle getRectangle(String value) {
        String pattern = "xywh=(-?\\d+),(-?\\d+),(-?\\d+),(-?\\d+)";
        Matcher m = Pattern.compile(pattern).matcher(value);
        if (m.find()) {
            int x = Integer.parseInt(m.group(1));
            int y = Integer.parseInt(m.group(2));
            int w = Integer.parseInt(m.group(3));
            int h = Integer.parseInt(m.group(4));
            return new Rectangle(x, y, w, h);
        } else {
            return null;
        }
    }

}

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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import de.intranda.api.annotation.IResource;
import de.intranda.api.annotation.ISelector;
import de.intranda.api.annotation.SimpleResource;
import de.intranda.api.annotation.GeoLocation.Geometry;
import de.intranda.api.annotation.GeoLocation.Properties;
import de.intranda.api.annotation.GeoLocation.ViewPoint;

public class ResourceDeserializer extends StdDeserializer<IResource> {

    /**
     * 
     */
    private static final long serialVersionUID = -6733687272555595794L;

    public ResourceDeserializer() {
        this(null);
    }
    
    /**
     * @param vc
     */
    public ResourceDeserializer(Class<?> vc) {
        super(vc);
    }


    @Override
    public IResource deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        try {
            return parseNode(node);
        } catch (URISyntaxException e) {
            throw new JsonParseException(p, "Resource id '" + node.asText() + "' is not a URI");
        } catch (NullPointerException e) {
            throw new JsonParseException(p, "Missing json properties. Could not deserialize resource");
        }

    }

    /**
     * @param p
     * @param node
     * @throws JsonParseException
     */
    private IResource parseNode(JsonNode node) throws URISyntaxException {
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
                        resource = new de.intranda.api.annotation.oa.SpecificResource(parseNode(node.get("full")).getId(), selector);
                        break;
                    default: 
                        resource = new de.intranda.api.annotation.oa.TypedResource(new URI(node.get("@id").asText()), node.get("@type").asText(),
                                    node.get("format").asText());
                }
            } else {    
                //WebAnnotationsResource
                
                String type = node.get("type").asText();
                switch(type) {
                    case "TextualBody":
                        resource = new de.intranda.api.annotation.wa.TextualResource(node.get("value").asText());
                        break;
                    case "SpecificResource":
                        ISelector selector = null;
                        if(node.get("selector").get("type").asText().equals("FragmentSelector")) {     
                            selector = new de.intranda.api.annotation.wa.FragmentSelector(getRectangle(node.get("selector").get("value").asText()));
                        }
                        resource = new de.intranda.api.annotation.wa.SpecificResource(parseNode(node.get("source")).getId(), selector);
                        break;
                    case "Feature": //geoJson
                        Geometry geometry = new Geometry(getAsDoubleArray(node.get("geometry").withArray("coordinates")), node.get("geometry").get("type").asText());
                        Properties properties = new Properties(Optional.ofNullable(node.get("properties").get("name")).map(JsonNode::asText).orElse(""));
                        ViewPoint view = new ViewPoint(node.get("view").get("zoom").asDouble(), getAsDoubleArray(node.get("view").withArray("center")));
                        resource = new de.intranda.api.annotation.GeoLocation(geometry, properties, view);
                        break;
                    default: 
                        resource = new de.intranda.api.annotation.wa.TypedResource(new URI(node.get("id").asText()), node.get("type").asText(),
                                    node.get("format").asText());
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

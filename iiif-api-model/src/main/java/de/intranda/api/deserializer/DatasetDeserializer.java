/**
 * This file is part of the Goobi viewer - a content presentation and management application for digitized objects.
 *
 * Visit these websites for more information.
 *          - http://www.intranda.com
 *          - http://digiverso.com
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package de.intranda.api.deserializer;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import de.intranda.api.annotation.wa.Dataset;

/**
 * @author florian
 *
 */
public class DatasetDeserializer extends StdDeserializer<Dataset> {

    /**
     * 
     */
    private static final long serialVersionUID = 7690778905718690458L;
    private ObjectMapper mapper = new ObjectMapper();


    public DatasetDeserializer() {
        this(null);
    }
    
    /**
     * @param vc
     */
    protected DatasetDeserializer(Class<?> vc) {
        super(vc);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
    }

    /* (non-Javadoc)
     * @see com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext)
     */
    @Override
    public Dataset deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
        JsonNode node = parser.getCodec().readTree(parser);
        URI id = null;
        String format = Dataset.FORMAT_VIEWER;
        if(node.has("id")) {
            id = URI.create(node.get("id").asText());
        }if(node.has("format")) {
            format = node.get("format").asText();
        }
        Dataset dataset = new Dataset(id, format);
        Iterator<Entry<String, JsonNode>> dataNodes = node.get("data").fields();
        while(dataNodes.hasNext()) {
            Entry<String, JsonNode> subnode = dataNodes.next();
            String label = subnode.getKey();
            JsonNode value = subnode.getValue();
            List<String> stringValues = new ArrayList<>();
            if(value.isArray()) {
                ArrayNode arrayNode = (ArrayNode)value;
                arrayNode.forEach(item -> stringValues.add(item.textValue()));
            } else {
                stringValues.add(value.asText());
            }
            dataset.setData(label, stringValues);
        }
        return dataset;
    }


}

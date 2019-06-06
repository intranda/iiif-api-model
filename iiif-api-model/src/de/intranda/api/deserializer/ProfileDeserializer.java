package de.intranda.api.deserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import de.intranda.api.iiif.image.ComplianceLevel;
import de.intranda.api.iiif.image.ComplianceLevelProfile;
import de.intranda.api.iiif.image.IiifProfile;

public class ProfileDeserializer extends StdDeserializer<List<IiifProfile>> {

    private static final long serialVersionUID = 8149156759035383305L;

    public ProfileDeserializer() {
        this(null);
    }

    public ProfileDeserializer(Class<List<IiifProfile>> vc) {
        super(vc);
    }

    @Override
    public List<IiifProfile> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        List<IiifProfile> profiles = new ArrayList<>();
        if (node.isTextual()) {
            String profile = node.asText();
            if (profile.equals(ComplianceLevel.level0.getUri())) {
                profiles.add(new ComplianceLevelProfile(ComplianceLevel.level0));
            } else if (profile.equals(ComplianceLevel.level1.getUri())) {
                profiles.add(new ComplianceLevelProfile(ComplianceLevel.level1));
            } else if (profile.equals(ComplianceLevel.level2.getUri())) {
                profiles.add(new ComplianceLevelProfile(ComplianceLevel.level2));
            }
        }
        return profiles;
    }

}

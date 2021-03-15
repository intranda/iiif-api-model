package de.intranda.api.iiif.presentation.v3;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.intranda.api.annotation.AgentType;
import de.intranda.api.annotation.IAgent;
import de.intranda.api.annotation.wa.ImageResource;
import de.intranda.api.iiif.presentation.content.ImageContent;
import de.intranda.api.iiif.presentation.content.LinkingContent;
import de.intranda.api.serializer.IIIF3MetadataValueSerializer;
import de.intranda.metadata.multilanguage.IMetadataValue;

/**
 * IAgent implementation for IIIF (v3) which differs from WebAnnotation Agent by having a logo and homepage
 * and also a 'label' instead of 'name'
 * 
 * @author florian
 *
 */
public class IIIFAgent implements IAgent {

	private final URI id;
	private final AgentType type;
	private final IMetadataValue label;
	private final List<LabeledResource> homepage = new ArrayList<>();
	private final List<ImageResource> logo = new ArrayList<>();
	
	public IIIFAgent(URI id, IMetadataValue label) {
		this.id = id;
		this.type = AgentType.AGENT;
		this.label = label;
	}
	
	public void addHomepage(LabeledResource homepage) {
		this.homepage.add(homepage);
	}
	
	public void addLogo(ImageResource logo) {
		this.logo.add(logo);
	}
	
	@Override
	public URI getId() {
		return this.id;
	}
	
	public AgentType getType() {
		return type;
	}
	
	@JsonSerialize(using = IIIF3MetadataValueSerializer.class)
	public IMetadataValue getLabel() {
		return label;
	}
	
	public List<LabeledResource> getHomepage() {
		return homepage;
	}
	
	public List<ImageResource> getLogo() {
		return logo;
	}

}

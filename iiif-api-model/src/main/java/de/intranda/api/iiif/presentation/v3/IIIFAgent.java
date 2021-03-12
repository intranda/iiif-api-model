package de.intranda.api.iiif.presentation.v3;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import de.intranda.api.annotation.AgentType;
import de.intranda.api.annotation.IAgent;
import de.intranda.api.iiif.presentation.v2.content.ImageContent;
import de.intranda.api.iiif.presentation.v2.content.LinkingContent;
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
	private final List<LinkingContent> homepage = new ArrayList<>();
	private final List<ImageContent> logo = new ArrayList<>();
	
	public IIIFAgent(URI id, AgentType type, IMetadataValue label) {
		this.id = id;
		this.type = type;
		this.label = label;
	}
	
	public void addHomepage(LinkingContent homepage) {
		this.homepage.add(homepage);
	}
	
	public void addLogo(ImageContent logo) {
		this.logo.add(logo);
	}
	
	@Override
	public URI getId() {
		return this.id;
	}
	
	public AgentType getType() {
		return type;
	}
	
	public IMetadataValue getLabel() {
		return label;
	}
	
	public List<LinkingContent> getHomepage() {
		return homepage;
	}
	
	public List<ImageContent> getLogo() {
		return logo;
	}

}

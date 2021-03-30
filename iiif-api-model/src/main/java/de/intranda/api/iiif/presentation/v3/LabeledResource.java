package de.intranda.api.iiif.presentation.v3;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.intranda.api.annotation.ILabeledResource;
import de.intranda.api.annotation.wa.TypedResource;
import de.intranda.api.serializer.IIIF3MetadataValueSerializer;
import de.intranda.metadata.multilanguage.IMetadataValue;

@JsonPropertyOrder({"id", "type", "format", "profile", "label"})
public class LabeledResource extends TypedResource implements ILabeledResource {

	private final IMetadataValue label;

	public LabeledResource() {
		super();
		this.label = null;
	}



	/**
	 * @param id
	 * @param type
	 * @param format
	 */
	public LabeledResource(URI id, String type, String format, String profile, IMetadataValue label) {
		super(id, type, format, profile);
		this.label = label;
	}
	
	/**
	 * @param id
	 * @param type
	 * @param format
	 */
	public LabeledResource(URI id, String type, String format, IMetadataValue label) {
		super(id, type, format);
		this.label = label;
	}



	/**
	 * @param id
	 * @param type
	 */
	public LabeledResource(URI id, String type, IMetadataValue label) {
		super(id, type);
		this.label = label;
	}



	@Override
	@JsonSerialize(using=IIIF3MetadataValueSerializer.class)
	public IMetadataValue getLabel() {
		return this.label;
	}

	
	
}

package de.intranda.metadata.multilanguage;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.intranda.api.serializer.IIIF3MetadataValueSerializer;

public class IIIF3Metadata extends Metadata {

	
	
    /**
	 * 
	 */
	public IIIF3Metadata() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param label
	 * @param value
	 */
	public IIIF3Metadata(IMetadataValue label, IMetadataValue value) {
		super(label, value);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param label
	 * @param value
	 */
	public IIIF3Metadata(IMetadataValue label, String value) {
		super(label, value);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param label
	 * @param value
	 */
	public IIIF3Metadata(String label, IMetadataValue value) {
		super(label, value);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param label
	 * @param value
	 */
	public IIIF3Metadata(String label, String value) {
		super(label, value);
		// TODO Auto-generated constructor stub
	}

	@Override
    @JsonSerialize(using=IIIF3MetadataValueSerializer.class)
    public IMetadataValue getLabel() {
        return super.getLabel();
    }

    @Override
    @JsonSerialize(using=IIIF3MetadataValueSerializer.class)
    public IMetadataValue getValue() {
        return super.getValue();
    }


}

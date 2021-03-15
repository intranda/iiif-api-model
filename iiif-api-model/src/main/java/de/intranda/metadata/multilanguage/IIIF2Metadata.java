package de.intranda.metadata.multilanguage;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.intranda.api.serializer.IIIF2MetadataValueSerializer;

public class IIIF2Metadata extends Metadata {

	
	
    /**
	 * 
	 */
	public IIIF2Metadata() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param label
	 * @param value
	 */
	public IIIF2Metadata(IMetadataValue label, IMetadataValue value) {
		super(label, value);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param label
	 * @param value
	 */
	public IIIF2Metadata(IMetadataValue label, String value) {
		super(label, value);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param label
	 * @param value
	 */
	public IIIF2Metadata(String label, IMetadataValue value) {
		super(label, value);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param label
	 * @param value
	 */
	public IIIF2Metadata(String label, String value) {
		super(label, value);
		// TODO Auto-generated constructor stub
	}

	@Override
    @JsonSerialize(using=IIIF2MetadataValueSerializer.class)
    public IMetadataValue getLabel() {
        return super.getLabel();
    }

    @Override
    @JsonSerialize(using=IIIF2MetadataValueSerializer.class)
    public IMetadataValue getValue() {
        return super.getValue();
    }


}

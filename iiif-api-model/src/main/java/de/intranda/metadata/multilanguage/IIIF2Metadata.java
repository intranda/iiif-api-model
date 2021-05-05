package de.intranda.metadata.multilanguage;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.intranda.api.serializer.IIIF2MetadataValueSerializer;

/**
 * Metadata that is always serialized accordings to IIIF2 specifications
 * 
 * @author florian
 *
 */
public class IIIF2Metadata extends Metadata {
	
	
    /**
	 * 
	 */
	public IIIF2Metadata() {
		super();
	}

	/**
	 * @param label
	 * @param value
	 */
	public IIIF2Metadata(IMetadataValue label, IMetadataValue value) {
		super(label, value);
	}

	/**
	 * @param label
	 * @param value
	 */
	public IIIF2Metadata(IMetadataValue label, String value) {
		super(label, value);
	}

	/**
	 * @param label
	 * @param value
	 */
	public IIIF2Metadata(String label, IMetadataValue value) {
		super(label, value);
	}

	/**
	 * @param label
	 * @param value
	 */
	public IIIF2Metadata(String label, String value) {
		super(label, value);
	}
	
	public IIIF2Metadata(Metadata source) {
		super(source.getLabel(), source.getValue());
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

package de.intranda.api.annotation;

import de.intranda.metadata.multilanguage.IMetadataValue;

public interface ILabeledResource extends ITypedResource {
	
	public IMetadataValue getLabel();

}

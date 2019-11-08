package de.intranda.api.annotation.wa;

import java.net.URI;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.intranda.api.annotation.ISelector;
import de.intranda.api.serializer.URLOnlySerializer;

@JsonSerialize(using=URLOnlySerializer.class)
public class SpecificResourceURI extends SpecificResource {

    public SpecificResourceURI(URI source, ISelector selector) {
        super(source, selector);
    }

}

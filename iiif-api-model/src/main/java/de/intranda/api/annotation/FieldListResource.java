package de.intranda.api.annotation;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonValue;

import de.intranda.metadata.multilanguage.Metadata;

public class FieldListResource implements IResource {

    private List<Metadata> fieldList = new ArrayList<>();
    
    public FieldListResource(List<Metadata> fieldList) {
        this.fieldList = fieldList;
    }

    /**
     * id is null because this is no web resource and cannot be dereferenced
     */
    @Override
    public URI getId() {
        return null;
    }
    
    @JsonValue
    public List<Metadata> getFieldList() {
        return fieldList;
    }

}

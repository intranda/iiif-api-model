package de.intranda.api.annotation.wa;

import java.awt.Rectangle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.intranda.api.annotation.ISelector;

public class FragmentSelector implements ISelector {

    private static final String TYPE = "FragmentSelector";

    private final Rectangle fragment;

    public FragmentSelector(Rectangle fragment) {
        this.fragment = fragment;
    }
    
    
    public String getType() {
        return TYPE;
    }

    @Override
    public String getValue() {
        return "xywh=" + fragment.x + "," + fragment.y + "," + fragment.width + "," + fragment.height;
    }
    
    public Rectangle getFragment() {
        return fragment;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass().equals(this.getClass())) {
            return ((ISelector) obj).getValue().equals(this.getValue());
        } else {
            return false;
        }
    }
    
    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();        
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
           return super.toString();
        }
    }
    
}

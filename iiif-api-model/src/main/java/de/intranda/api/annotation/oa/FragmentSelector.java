package de.intranda.api.annotation.oa;

import java.awt.Rectangle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.intranda.api.annotation.ISelector;

public class FragmentSelector implements ISelector {

    private static final String TYPE = "oa:FragmentSelector";

    private final Rectangle fragment;

    public FragmentSelector(Rectangle fragment) {
        this.fragment = fragment;
    }
    
    @JsonProperty("@type")
    public String getType() {
        return TYPE;
    }
    
    @JsonIgnore
    public Rectangle getFragment() {
        return fragment;
    }

    @Override
    public String getValue() {
        return "xywh=" + fragment.x + "," + fragment.y + "," + fragment.width + "," + fragment.height;
    }

}

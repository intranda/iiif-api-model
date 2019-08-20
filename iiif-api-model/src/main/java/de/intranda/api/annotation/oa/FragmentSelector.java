package de.intranda.api.annotation.oa;

import java.awt.Rectangle;

import de.intranda.api.annotation.ISelector;

public class FragmentSelector implements ISelector {

    private static final String TYPE = "oa:FragmentSelector";

    private final Rectangle fragment;

    public FragmentSelector(Rectangle fragment) {
        this.fragment = fragment;
    }
    
    public static String getType() {
        return TYPE;
    }

    @Override
    public String getValue() {
        return "xywh=" + fragment.x + "," + fragment.y + "," + fragment.width + "," + fragment.height;
    }

}

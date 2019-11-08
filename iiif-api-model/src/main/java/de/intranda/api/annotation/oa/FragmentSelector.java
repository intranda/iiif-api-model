package de.intranda.api.annotation.oa;

import java.awt.Rectangle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.intranda.api.annotation.ISelector;

public class FragmentSelector implements ISelector {

    private static final String TYPE = "oa:FragmentSelector";
    private static final String FRAGMENT_REGEX = "xywh=(-?\\d+),(-?\\d+),(-?\\d+),(-?\\d+)";


    private final Rectangle fragment;

    public FragmentSelector(Rectangle fragment) {
        this.fragment = fragment;
    }
    
    public FragmentSelector(String fragment) {
        Matcher matcher = Pattern.compile(FRAGMENT_REGEX).matcher(fragment);
        if(matcher.find()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            int w = Integer.parseInt(matcher.group(3));
            int h = Integer.parseInt(matcher.group(4));
            this.fragment = new Rectangle(x, y, w, h);
        } else {
            throw new IllegalArgumentException("Cannot parse '" + fragment + "' as rectangle coordinates");
        }
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

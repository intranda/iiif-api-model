package de.intranda.api.annotation.oa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import de.intranda.api.annotation.ISelector;

@JsonPropertyOrder({"@type"})
@JsonInclude(Include.NON_EMPTY)
public class TextQuoteSelector implements ISelector {

    private static final String TYPE = "oa:TextQuoteSelector";
    
    private String fragment = null;
    private String prefix = null;
    private String suffix = null;
    
    @JsonProperty("exact")
    public String getFragment() {
        return fragment;
    }
    
    public void setFragment(String fragment) {
        this.fragment = fragment;
    }
    
    public String getPrefix() {
        return prefix;
    }
    
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
    
    public String getSuffix() {
        return suffix;
    }
    
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override
    @JsonProperty("@type")
    public String getType() {
        return TYPE;
    }

    @Override
    @JsonIgnore
    public String getValue() {
        return "exact=" + getFragment();
    }

}

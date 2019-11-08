package de.intranda.api.iiif.search;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.intranda.api.annotation.IAnnotation;
import de.intranda.api.annotation.ISelector;
import de.intranda.api.serializer.URLOnlySerializer;

@JsonPropertyOrder({"@type"})
@JsonInclude(Include.NON_EMPTY)
public class SearchHit {

    private static final String TYPE = "search:Hit";
    
    private List<IAnnotation> annotations = new ArrayList<>();
    private String before = null;
    private String after = null;
    private String match = null;
    private List<ISelector> selectors = new ArrayList<>();
    
    @JsonProperty("@type")
    public String getType() {
        return TYPE;
    }

    /**
     * @return the annotations
     */
    @JsonSerialize(using=URLOnlySerializer.class)
    public List<IAnnotation> getAnnotations() {
        return annotations;
    }

    /**
     * @param annotations the annotations to set
     */
    public void setAnnotations(List<IAnnotation> annotations) {
        this.annotations = annotations;
    }
    
    public void addAnnotation(IAnnotation annotation) {
        this.annotations.add(annotation);
    }

    /**
     * @return the before
     */
    public String getBefore() {
        return before;
    }

    /**
     * @param before the before to set
     */
    public void setBefore(String before) {
        this.before = before;
    }

    /**
     * @return the after
     */
    public String getAfter() {
        return after;
    }

    /**
     * @param after the after to set
     */
    public void setAfter(String after) {
        this.after = after;
    }

    /**
     * @return the match
     */
    public String getMatch() {
        return match;
    }

    /**
     * @param match the match to set
     */
    public void setMatch(String match) {
        this.match = match;
    }

    /**
     * @return the selectors
     */
    public List<ISelector> getSelectors() {
        return selectors;
    }

    /**
     * @param selectors the selectors to set
     */
    public void setSelectors(List<ISelector> selectors) {
        this.selectors = selectors;
    }
    
    public void addSelector(ISelector selector) {
        this.selectors.add(selector);
    }
}

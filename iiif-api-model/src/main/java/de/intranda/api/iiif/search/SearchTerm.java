package de.intranda.api.iiif.search;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.intranda.api.annotation.IResource;

public class SearchTerm implements IResource {

    private long count;
    private final String match;
    private final URI id;
    
    public SearchTerm(URI id, String match, int initialCount) {
        this.id = id;
        this.match = match;
        this.count = initialCount;
    }
    
    @Override
    @JsonProperty("url")
    public URI getId() {
        return id;
    }
    
    public String getMatch() {
        return match;
    }

    public long getCount() {
        return count;
    }
    
    public void setCount(long count) {
        this.count = count;
    }
    
    public void incrementCount(long count) {
        this.count += count;
    }
    
    public boolean hasMatches() {
        return this.count > 0;
    }
    
    @Override
    public int hashCode() {
        return this.match.hashCode();
    }
    
    /**
     * SearchTerms are equal if their {@link #getMatch() matches} are equal 
     */
    @Override
    public boolean equals(Object obj) {
        if(obj.getClass().equals(SearchTerm.class)) {
            return ((SearchTerm)obj).getMatch().equalsIgnoreCase(this.getMatch());
        } else {
            return false;
        }
    }

}

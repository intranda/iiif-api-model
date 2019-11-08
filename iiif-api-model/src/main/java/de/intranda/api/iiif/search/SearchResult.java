package de.intranda.api.iiif.search;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import de.intranda.api.PropertyList;
import de.intranda.api.annotation.IAnnotation;
import de.intranda.metadata.multilanguage.IMetadataValue;

@JsonPropertyOrder({ "@context", "@id", "@type", "within"})
@JsonInclude(Include.NON_NULL)
public class SearchResult {

    private static final String TYPE = "sc:AnnotationList";
    
    private final URI id;
    private List<IAnnotation> resources = new ArrayList<>();
    private List<SearchHit> hits = new ArrayList<>();
    private PropertyList<String> context = new PropertyList<>();   
    private SearchResultLayer within;
    private URI next;
    private URI prev;
    private int startIndex = 0;
    
    public SearchResult(URI id) {
       this.id = id;
    }
    
    @JsonProperty("@id")
    public URI getId() {
        return id;
    };
    
    @JsonProperty("@context")
    public PropertyList<String> getContext() {
        return this.context;
    }
    
    @JsonProperty("@type")
    public String getType() {
        return TYPE;
    }
    
    public void setContext(Collection<String> context) {
        this.context = new PropertyList<String>(context);
    }
    
    public void addContext(String context) {
        if(!this.context.contains(context)) {
            this.context.add(context);
        }
    }
    
    public SearchResultLayer getWithin() {
        return within;
    }

    /**
     * @return the next
     */
    public URI getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(URI next) {
        this.next = next;
    }

    /**
     * @return the prev
     */
    public URI getPrev() {
        return prev;
    }

    /**
     * @param prev the prev to set
     */
    public void setPrev(URI prev) {
        this.prev = prev;
    }

    /**
     * The result is not accurate if more than one type (motivation) of annotations is searched, because the page results for all types are combined to one page, skewing the actual indexes
     * 
     * @return the startIndex
     */
    public int getStartIndex() {
        return startIndex;
    }

    /**
     * @param startIndex the startIndex to set
     */
    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * @return the resources
     */
    public List<IAnnotation> getResources() {
        return resources;
    }

    public void setResources(List<IAnnotation> resources) {
        this.resources = resources;
    }
    
    /**
     * @param within the within to set
     */
    public void setWithin(SearchResultLayer within) {
        this.within = within;
    }
    
    public void setHits(List<SearchHit> hits) {
        this.hits = hits;
    }
    
    public List<SearchHit> getHits() {
        return hits;
    }
    
    public void addHit(SearchHit hit) {
        this.hits.add(hit);
    }


     
    
}

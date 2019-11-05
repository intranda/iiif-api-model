package de.intranda.api.iiif.search;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import de.intranda.api.PropertyList;

@JsonPropertyOrder({ "@context", "@id", "@type", "within"})
@JsonInclude(Include.NON_NULL)
public class AutoSuggestResult {
    private static final String TYPE = "search:TermList";
    
    private final URI id;
    private PropertyList<String> context = new PropertyList<>();   
    private List<String> ignored = new ArrayList<>();
    private List<SearchTerm> terms = new ArrayList<>();
    
    
    public AutoSuggestResult(URI id) {
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

    /**
     * @return the ignored
     */
    public List<String> getIgnored() {
        return ignored;
    }
    /**
     * @param ignored the ignored to set
     */
    public void setIgnored(List<String> ignored) {
        this.ignored = ignored;
    }
    
    public void addIgnored(String ignored) {
        this.ignored.add(ignored);
    }
    
    public void setTerms(List<SearchTerm> terms) {
        this.terms = terms;
    }
    
    public List<SearchTerm> getTerms() {
        return terms;
    }
    
    public void addTerm(SearchTerm term) {
        this.terms.add(term);
    }
    
    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }
}

package de.intranda.api.iiif.search;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class SearchResultLayer {

    private static final String TYPE = "sc:Layer";
    
    private long total = 0;
    private URI first;
    private URI last;
    private List<String> ignored = new ArrayList<>();
    /**
     * @return the total
     */
    public long getTotal() {
        return total;
    }
    /**
     * @param total the total to set
     */
    public void setTotal(long total) {
        this.total = total;
    }
    /**
     * @return the first
     */
    public URI getFirst() {
        return first;
    }
    /**
     * @param first the first to set
     */
    public void setFirst(URI first) {
        this.first = first;
    }
    /**
     * @return the last
     */
    public URI getLast() {
        return last;
    }
    /**
     * @param last the last to set
     */
    public void setLast(URI last) {
        this.last = last;
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
    
    /**
     * @return the type
     */
    public static String getType() {
        return TYPE;
    }
    
    
    
}

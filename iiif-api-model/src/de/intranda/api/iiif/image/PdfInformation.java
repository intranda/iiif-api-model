package de.intranda.api.iiif.image;

import java.text.DecimalFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PdfInformation {
    
    private String title;
    private String div;
    private int pages;
    private long size;
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * @return the div
     */
    public String getDiv() {
        return div;
    }
    /**
     * @param div the div to set
     */
    public void setDiv(String div) {
        this.div = div;
    }
    /**
     * @return the pages
     */
    public int getPages() {
        return pages;
    }
    /**
     * @param pages the pages to set
     */
    public void setPages(int pages) {
        this.pages = pages;
    }
    /**
     * @return the size
     */
    public long getSize() {
        return size;
    }
    /**
     * @param size the size to set
     */
    @JsonIgnore
    public void setSize(long size) {
        this.size = size;
    }
    
    @JsonProperty("size")
    private String getDisplaySize() {
        return new DecimalFormat("0.00").format(size/1024.0/1024.0) + " MB";
    }

}

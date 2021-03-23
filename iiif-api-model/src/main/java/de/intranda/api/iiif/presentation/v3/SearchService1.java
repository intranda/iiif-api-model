package de.intranda.api.iiif.presentation.v3;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.intranda.api.iiif.search.SearchService;

/**
 * Wraps a IIIF Search Service 1.0 for a Presentation 3.0 manifest, adding the @type attribute
 * 
 * @author florian
 *
 */
public class SearchService1 extends SearchService {

	public static final String TYPE ="SearchService1";
	
	public SearchService1(URI id) {
		super(id);
	}
	
	@JsonProperty("@type")
	public String getType() {
		return TYPE;
	}

}

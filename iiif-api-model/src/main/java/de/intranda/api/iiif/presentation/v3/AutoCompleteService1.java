package de.intranda.api.iiif.presentation.v3;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.intranda.api.iiif.search.AutoSuggestService;

public class AutoCompleteService1 extends AutoSuggestService {

	public static final String TYPE ="AutoCompleteService1";

	
	public AutoCompleteService1(URI id) {
		super(id);
	}
	
	@JsonProperty("@type")
	public String getType() {
		return TYPE;
	}


}

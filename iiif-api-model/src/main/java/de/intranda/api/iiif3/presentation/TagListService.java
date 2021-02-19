/**
 * This file is part of the Goobi viewer - a content presentation and management application for digitized objects.
 *
 * Visit these websites for more information.
 *          - http://www.intranda.com
 *          - http://digiverso.com
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package de.intranda.api.iiif3.presentation;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.intranda.api.services.Service;
import de.intranda.api.services.TagListDefinition;

/**
 * @author Florian Alpers
 *
 */
@JsonInclude(Include.NON_NULL)
public class TagListService implements Service {

    private static final String CONTEXTPATH = TagListDefinition.URI_PATH;
    private static final String TYPE = "TagList";

    private final String name;
    private List<String> tags = new ArrayList<>();
    private String baseURI = "";
    
    public TagListService(String name) {
        this.name = name;
    }
    
    public TagListService(String name, String baseURI) {
        this.name = name;
        this.baseURI = baseURI;
    }
    
    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.servlets.rest.services.Service#getContext()
     */
    @Override
    public URI getContext() throws URISyntaxException {
        return new URI(baseURI + CONTEXTPATH);
    }
    
    @JsonProperty("@type")
    public static String getType() {
        return TYPE;
    }
    
    public List<String> getTags() {
        return tags;
    }
    
    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    
    public String getName() {
        return name;
    }

    

}

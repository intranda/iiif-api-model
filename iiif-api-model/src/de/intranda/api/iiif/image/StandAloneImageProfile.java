/*
 * This file is part of the ContentServer project.
 * Visit the websites for more information. 
 * 		- http://gdz.sub.uni-goettingen.de 
 * 		- http://www.intranda.com 
 * 		- http://www.digiverso.com
 * 
 * Copyright 2009, Center for Retrospective Digitization, Göttingen (GDZ),
 * intranda software
 * 
 * This is the extended version updated by intranda
 * Copyright 2012, intranda GmbH
 * 
 * Licensed under the Apache License, Version 2.0 (the “License�?);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *  http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an “AS IS�? BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.intranda.api.iiif.image;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Wrapper for a iiif image profile delivered on its own
 * and not as part of a parent object
 * 
 * @author Florian Alpers
 *
 */
public class StandAloneImageProfile extends ImageProfile {

    private static final String JSON_CONTEXT = "http://iiif.io/api/image/2/context.json";
    private static final String JSON_TYPE = "iiif:ImageProfile";
    
    @JsonProperty("@context")
    private String context = JSON_CONTEXT;
    @JsonProperty("@id")
    private String id;
    @JsonProperty("@type")
    private String type = JSON_TYPE;
    

    public StandAloneImageProfile(List<String> formats, List<String> qualities) {
        super(formats, qualities);
    }
    
}

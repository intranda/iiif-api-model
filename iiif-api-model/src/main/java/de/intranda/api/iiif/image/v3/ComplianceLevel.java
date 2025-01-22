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
package de.intranda.api.iiif.image.v3;

import jakarta.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 
 * @author Florian Alpers
 *
 */
@XmlRootElement
public enum ComplianceLevel {
    level0("level0", "http://iiif.io/api/image/3/level0.json"),
    level1("level1", "http://iiif.io/api/image/3/level1.json"),
    level2("level2", "http://iiif.io/api/image/3/level2.json");

    final private String uri;
    final private String label;
    
    private ComplianceLevel(String label, String uri) {
        this.uri = uri;
        this.label = label;
    }
    public String getUri() {
        return this.uri;
    }
    @JsonValue
    public String getLabel() {
        return this.label;
    }
}

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
package de.intranda.api.iiif3.image;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonValue;

import de.intranda.api.iiif.image.IiifProfile;

/**
 * Wraps an instance of compliance level
 * 
 * @author Florian Alpers
 *
 */
@XmlRootElement
public class ComplianceLevelProfile extends IiifProfile {
    
    private final ComplianceLevel level;

    public ComplianceLevelProfile() {
        this.level = ComplianceLevel.level0;
    }
    
    public ComplianceLevelProfile(ComplianceLevel level) {
        this.level = level;
    }
    
    @XmlElement(name = "uri")
    public String getAsString() {
        return level.getUri();
    }
    
    @JsonValue
    public ComplianceLevel getComplianceLevel() {
        return level;
    }

    @Override
    public String toString() {
        return level.getUri();
    }
}

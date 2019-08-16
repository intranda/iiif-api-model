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

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Implementation of the iiif ImageInformation#profile object specified in http://iiif.io/api/image/2.0/#image-information
 * 
 * @author Florian Alpers
 *
 */
@XmlRootElement
@JsonInclude(Include.NON_NULL)
public class ImageProfile extends IiifProfile {

    @JsonProperty("formats")
    private List<String> formats;
    @JsonProperty("qualities")
    private List<String> colorTypes;
    @JsonProperty("supports")
    private List<SupportFeature> supportedFeatures;
    @JsonProperty("maxWidth")
    private Integer maxWidth = null;
    @JsonProperty("maxHeight")
    private Integer maxHeight = null;

    public ImageProfile(List<String> formats, List<String> qualities) {
        this.formats = formats;
        this.colorTypes = qualities;
        this.supportedFeatures = new ArrayList<>(getSupportedFeatures());
    }
    
    public Integer getMaxHeight() {
        return maxHeight;
    }
    
    public Integer getMaxWidth() {
        return maxWidth;
    }
    
    public void setMaxHeight(Integer maxHeight) {
        this.maxHeight = maxHeight;
    }
    
    public void setMaxWidth(Integer maxWidth) {
        this.maxWidth = maxWidth;
    }

    public static EnumSet<SupportFeature> getSupportedFeatures() {
        return EnumSet.of(
                SupportFeature.profileLinkHeader, 
                SupportFeature.baseUriRedirect, 
                SupportFeature.cors, 
                SupportFeature.jsonldMediaType,
                SupportFeature.regionByPct,
                SupportFeature.regionByPx, 
                SupportFeature.regionSquare,
                SupportFeature.rotationArbitrary, 
                SupportFeature.rotationBy90s/*, SupportFeature.sizeAboveFull*/,
                SupportFeature.sizeByForcedWh, 
                SupportFeature.sizeByWhListed, 
                SupportFeature.sizeByConfinedWh,
                SupportFeature.sizeByDistortedWh,
                SupportFeature.sizeByH, 
                SupportFeature.sizeByPct,
                SupportFeature.sizeByW, 
                SupportFeature.sizeByWh, 
                SupportFeature.mirroring);
    }

    public static enum SupportFeature {

        baseUriRedirect,
        canonicalLinkHeader,
        cors,
        jsonldMediaType,
        mirroring,
        profileLinkHeader,
        regionByPct,
        regionByPx,
        regionSquare,
        rotationArbitrary,
        rotationBy90s,
        sizeAboveFull, //Not supported for bitnal images when scaling with JAI
        sizeByConfinedWh,
        sizeByDistortedWh,
        @Deprecated //as of iiif 2.1
        sizeByWhListed,
        @Deprecated //as of iiif 2.1
        sizeByForcedWh,
        sizeByH,
        sizeByPct,
        sizeByW,
        sizeByWh;

    }

}

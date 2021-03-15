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

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import de.intranda.api.iiif.image.IiifProfile;
import de.intranda.api.iiif.image.ImageInformation;
import de.intranda.api.services.Service;
import de.intranda.metadata.multilanguage.IMetadataValue;
import de.intranda.metadata.multilanguage.SimpleMetadataValue;

/**
 * Implementation of the iiif ImageInformation object specified in https://iiif.io/api/image/3.0/#5-image-information
 * 
 * @author Florian Alpers
 *
 */
@JsonPropertyOrder({ "@context", "id", "type", "protocol", "profile", "width", "height", "maxWidth", "maxHeight", "maxArea", "rights", "sizes", "tiles",  "service" })
@JsonInclude(Include.NON_ABSENT)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageInformation3 extends ImageInformation {

    public static final URI JSON_CONTEXT = URI.create("http://iiif.io/api/image/3/context.json");
    public static final String JSON_PROTOCOL = "http://iiif.io/api/image";
    public static final ComplianceLevel IIIF_COMPLIANCE_LEVEL = ComplianceLevel.level2;
    public static final String TYPE = "ImageService3";
    /**
     * All image formats that are supported for all regular images
     */
    private static final List<String> SUPPORTED_FORMATS = Arrays.asList(new String[]{"tif", "png", "jpg"});
    /**
     * All image formats required by compliance level2
     */
    private static final List<String> LEVEL2_FORMATS = Arrays.asList(new String[]{"png", "jpg"});


    private Integer maxWidth = null;
    private Integer maxHeight = null;
    private Integer maxArea = null;
    private IMetadataValue label = null;
    private List<String> preferredFormats = new ArrayList<String>();
    
    public ImageInformation3(URI id) {
        super(id);
    }
    
    public ImageInformation3(String id) {
        super(id);
    }

    public ImageInformation3() {
        this("");
    }
    
    public ImageInformation3(ImageInformation3 source) {
		super(source);
		this.maxWidth = source.maxWidth;
		this.maxHeight = source.maxHeight;
		this.maxArea = source.maxArea;
	}

    public ImageInformation3(ImageInformation source) {
		super(source);
	}

	@Override
    @JsonProperty("id")
    public URI getId() {
    	return super.getId();
    }

    @JsonProperty("type")
    public String getType() {
		return TYPE;
	}
    
    /**
     * Ignore old profiles. This is replaced with {@link #getProfile()}
     */
    @JsonIgnore
    @Override
    public List<IiifProfile> getProfiles() {
        return null;
    }
    
    public IiifProfile getProfile() {
    	return new ComplianceLevelProfile(IIIF_COMPLIANCE_LEVEL);
    }


    @JsonProperty("@context")
    public URI getContext() {
        return JSON_CONTEXT;
    }


    @JsonProperty("protocol")
    public String getProtocol() {
        return JSON_PROTOCOL;
    }

    @Override
    @JsonIgnore
    public String getLogo() {
    	return super.getLogo();
    }
    
    @Override
    @JsonIgnore
    public String getAttribution() {
    	return super.getAttribution();
    }
    
    @Override
    @JsonProperty("rights")
    public String getLicense() {
    	return super.getLicense();
    }

    @Override
    public List<Service> getService() {
    	// always return a json array, even for a single service
    	return new ArrayList<>(super.getService());
    }
    
    /**
     * 
     * @return all iii3 image api features (see https://iiif.io/api/image/3.0/#57-extra-functionality)
     * which are not included in level2 compliance level specification (https://iiif.io/api/image/3/level2.json)
     */
    public Collection<ImageApiFeature> getExtraFeatures() {
    	return ImageApiFeature.getExtraFeatures();
    }
    
    /**
     * "png", "jpg" and "tif" are supported. This method only returns "tif" and "pdf" because the others are included
     * in compliance level specification (https://iiif.io/api/image/3/level2.json)
     * @return Any formats for image delivery not included in compliance level
     */
    public Collection<String> getExtraFormats() {
    	return CollectionUtils.subtract(
    			CollectionUtils.union(SUPPORTED_FORMATS, getPreferredFormats()), 
    			LEVEL2_FORMATS);   
    }
    
    public Collection<String> getExtraQualities() {
    	return Arrays.asList("gray", "bitonal");
    }
    
    public IMetadataValue getLabel() {
		return label;
	}
    
    public void setLabel(IMetadataValue label) {
		this.label = label;
	}
    
    public void setLabel(String label) {
    	this.setLabel(new SimpleMetadataValue(label));
    }
    
    public void setMaxWidth(Integer maxWidth) {
		this.maxWidth = maxWidth;
	}
    
    public Integer getMaxWidth() {
		return maxWidth;
	}
    
    public void setMaxHeight(Integer maxHeight) {
		this.maxHeight = maxHeight;
	}
    
    public Integer getMaxHeight() {
		return maxHeight;
	}
    
    public void setMaxArea(Integer maxArea) {
		this.maxArea = maxArea;
	}
    
    public Integer getMaxArea() {
		return maxArea;
	}
    
    public void setPreferredFormats(List<String> preferredFormats) {
		this.preferredFormats = new ArrayList<>(preferredFormats);
	}
    
    public void setPreferredFormats(String... preferredFormats) {
		this.preferredFormats = Arrays.asList(preferredFormats);
	}
    
    public List<String> getPreferredFormats() {
		return preferredFormats;
	}
    
    
    
}

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


import java.net.URI;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

import de.intranda.api.services.Service;

/**
 * Implementation of the  iiif ImageInformation#services#pyhsdim object
 * specified in http://iiif.io/api/image/2.0/#image-information
 * 
 * @author Florian Alpers
 *
 */
@JsonPropertyOrder({ "@context", "profile", "physicalScale", "physicalUnits" })
@JsonInclude(Include.NON_NULL)
public class PhysicalDimension implements Service {

    private static final URI CONTEXT = URI.create("http://iiif.io/api/annex/services/physdim/1/context.json");
    private static final String PROFILE = "http://iiif.io/api/annex/services/physdim";

    private static final float MILLIMETER_PER_INCH = 25.4f;
    private static final float MILLIMETER_PER_CM = 10f;

    private final float resolution;
    private final ResolutionUnit unit;

    public PhysicalDimension(float resolution, ResolutionUnit unit) {
        this.resolution = resolution;
        this.unit = unit;
    }
    
    public PhysicalDimension() {
        this.resolution = 0f;
        this.unit = null;
    }

    @JsonProperty("@context")
    public URI getContext() {
        return CONTEXT;
    }

    @JsonProperty("profile")
    public String getProfile() {
        return PROFILE;
    }

    public float getResolution() {
        return resolution;
    }

    @JsonProperty("physicalScale")
    public float getPhysicalScale() {
        return 1/resolution;
    }

    @JsonProperty("physicalUnits")
    public ResolutionUnit getUnit() {
        return unit;
    }

    public PhysicalDimension convertToUnit(ResolutionUnit newUnit) {
        float mm = convertToMillimeter(resolution, unit);
        float newRes = convertFromMillimeter(mm, newUnit);
        return new PhysicalDimension(newRes, newUnit);
    }
    
    private float convertFromMillimeter(float res, ResolutionUnit newUnit) {
        switch (newUnit) {
            case centimeter:
                return res / MILLIMETER_PER_CM;
            case inch:
                return res / MILLIMETER_PER_INCH;
            case millimeter:
                return res;
            default:
                throw new IllegalArgumentException(newUnit + " not yet implemented");
        }
    }

    private float convertToMillimeter(float res, ResolutionUnit oldUnit) {
        switch (oldUnit) {
            case centimeter:
                return res * MILLIMETER_PER_CM;
            case inch:
                return res * MILLIMETER_PER_INCH;
            case millimeter:
                return res;
            default:
                throw new IllegalArgumentException(oldUnit + " not yet implemented");
        }
    }

    public static enum ResolutionUnit {

        millimeter("mm"),
        centimeter("cm"),
        inch("in");

        private String label;

        private ResolutionUnit(String label) {
            this.label = label;
        }

        @JsonValue
        public String getLabel() {
            return this.label;
        }
    }
}

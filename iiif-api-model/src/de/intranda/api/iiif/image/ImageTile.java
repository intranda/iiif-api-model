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
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Implementation of the  iiif ImageInformation#tiles/#tile object
 * specified in http://iiif.io/api/image/2.0/#image-information
 * 
 * @author Florian Alpers
 *
 */
@XmlRootElement
@XmlType(propOrder={"width", "height", "scaleFactors"})
@JsonPropertyOrder({"width", "height", "scaleFactors"})
public class ImageTile {
    
    final int width;
    final int height;
    final List<Integer> scaleFactors;
    
    public ImageTile(int width, int height, Integer... scaleFactors) {
        super();
        this.width = width;
        this.height = height;
        this.scaleFactors = Arrays.asList(scaleFactors);
    }
    
    public ImageTile(int width, int height, List<Integer> scaleFactors) {
        super();
        this.width = width;
        this.height = height;
        this.scaleFactors = scaleFactors;
    }
    
    public ImageTile() {
        super();
        this.width = 0;
        this.height = 0;
        this.scaleFactors = new ArrayList<Integer>();
    }

    @XmlElement(name="width")
    @JsonProperty("width")
    public int getWidth() {
        return width;
    }

    @XmlElement(name="height")
    @JsonProperty("height")
    public int getHeight() {
        return height;
    }

    @XmlElementWrapper(name="scaleFactors")
    @XmlElement(name="scaleFactor")
    @JsonProperty("scaleFactors")
    public List<Integer> getScaleFactors() {
        return scaleFactors;
    }

    
    
}

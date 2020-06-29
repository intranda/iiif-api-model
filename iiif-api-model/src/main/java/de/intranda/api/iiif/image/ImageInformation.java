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

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import de.intranda.api.deserializer.ProfileDeserializer;

/**
 * Implementation of the iiif ImageInformation object specified in http://iiif.io/api/image/2.0/#image-information
 * 
 * @author Florian Alpers
 *
 */
@XmlRootElement
@XmlSeeAlso({ ComplianceLevelProfile.class, ImageProfile.class, PhysicalDimension.class })
@JsonPropertyOrder({ "@context", "@id", "protocol", "width", "height", "attribution", "license", "logo", "sizes", "tiles", "profile", "service" })
@JsonInclude(Include.NON_ABSENT)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageInformation extends Service {

    public static final String JSON_CONTEXT = "http://iiif.io/api/image/2/context.json";
    public static final String JSON_PROTOCOL = "http://iiif.io/api/image";
    public static final ComplianceLevel IIIF_COMPLIANCE_LEVEL = ComplianceLevel.level2;

    final private String id;
    private int width;
    private int height;
    private List<IiifProfile> profiles = new ArrayList<>();
    private List<ImageSize> sizes = new ArrayList<>();
    private List<ImageTile> tiles = new ArrayList<>();
    private Service service;
    private String attribution;
    private String license;
    private String logo;

    public ImageInformation(String id) {
        this.id = id;
        addProfile(new ComplianceLevelProfile(IIIF_COMPLIANCE_LEVEL));
    }

    public ImageInformation() {
        this("");
    }

    @JsonProperty("width")
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @JsonProperty("height")
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @JsonProperty("profile")
    @JsonDeserialize(using = ProfileDeserializer.class)
    public List<IiifProfile> getProfiles() {
        return profiles;
    }

    public void addProfile(IiifProfile profile) {
        this.profiles.add(profile);
    }

    public void removeProfile(IiifProfile profile) {
        this.profiles.remove(profile);
    }

    @JsonProperty("sizes")
    public List<ImageSize> getSizes() {
        return sizes;
    }

    public void addSize(ImageSize size) {
        this.sizes.add(size);
    }

    public void setSizesFromDimensions(List<Dimension> sizes) {
        Collections.sort(sizes, new DimensionComparator());
        this.sizes = new ArrayList<>();
        for (Dimension dimension : sizes) {
            addSize(new ImageSize(dimension.width, dimension.height));
        }
    }

    public void removeSize(ImageSize size) {
        this.sizes.remove(size);
    }

    @JsonProperty("tiles")
    public List<ImageTile> getTiles() {
        return tiles;
    }

    public void setTiles(List<ImageTile> tiles) {
        this.tiles = tiles;
    }

    public void addTile(ImageTile tile) {
        this.tiles.add(tile);
    }

    public void removeTile(ImageTile tile) {
        this.tiles.remove(tile);
    }

    @JsonProperty("service")
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @JsonProperty("@context")
    public String getContext() {
        return JSON_CONTEXT;
    }

    @JsonProperty("@id")
    public String getId() {
        return id;
    }

    @JsonProperty("protocol")
    public String getProtocol() {
        return JSON_PROTOCOL;
    }

    @JsonProperty("attribution")
    public String getAttribution() {
        return attribution;
    }

    @JsonProperty("license")
    public String getLicense() {
        return license;
    }

    @JsonProperty("logo")
    public String getLogo() {
        return logo;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(" (").append(width).append("x").append(height).append(")");
        return sb.toString();
    }

    public static class DimensionComparator implements Comparator<Dimension> {

        @Override
        public int compare(Dimension o1, Dimension o2) {
            return Integer.compare(o1.height * o1.width, o2.height * o2.width);
        }

    }

}

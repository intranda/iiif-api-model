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
import java.net.URI;
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
import de.intranda.api.iiif.image.PhysicalDimension.ResolutionUnit;

/**
 * Implementation of the iiif ImageInformation object specified in http://iiif.io/api/image/2.0/#image-information
 * 
 * @author Florian Alpers
 *
 */
@XmlRootElement
@XmlSeeAlso({ ComplianceLevelProfile.class, ImageProfile.class, PhysicalDimension.class })
@XmlType(propOrder = { "context", "id", "protocol", "width", "height", "attribution", "license", "logo", "sizes", "tiles", "profiles", "service" })
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

    public ImageInformation(String id, String attribution, String license, String logo) {
        this(id);
        this.attribution = attribution;
        this.license = license;
        this.logo = logo;
    }

    public ImageInformation() {
        this("");
    }

    /**
     * 
     * @param imageURI
     * @param watermarkConfigUri
     * @param resourceURI
     * @throws WatermarkException
     */
    public ImageInformation(URI imageURI, String resourceURI, int width, int height) {
        this(resourceURI.toString());
        setWidth(width);
        setHeight(height);
        setTiles(calculateFixedTileSizes(512, 0, getWidth(), getHeight()));
    }

    /**
     * 
     * @param imageURI
     * @param watermarkConfigUri
     * @param resourceURI
     * @param resolution dpi
     * @throws WatermarkException
     */
    public ImageInformation(URI imageURI, String resourceURI, int width, int height, float resolution) {
        this(imageURI, resourceURI, width, height);
        setService(new PhysicalDimension(resolution, ResolutionUnit.inch));
    }

    private List<ImageTile> calculateFixedTileSizes(int minSize, int depth, int origWidth, int origHeight) {

        List<ImageTile> tiles = new ArrayList<>();

        //        tiles.add(new ImageTile(2048, 2048, 4));
        //        tiles.add(new ImageTile(1024, 1024, 2));
        tiles.add(new ImageTile(512, 512, 1, 32));

        //        Integer resolution = (int) Math.pow(2, depth);
        //        for (int tileSize = (int) (minSize*Math.pow(2, depth)); tileSize >= minSize; tileSize/=2) {
        //            ImageTile tile = new ImageTile(tileSize, tileSize, resolution, resolution/2, resolution/4);
        //            resolution /= 2;
        //            tiles.add(tile);
        //        }

        return tiles;
    }

    private List<ImageTile> calculateTilesForImage(int width, int height, int depth) {

        List<ImageTile> tiles = new ArrayList<>();

        Integer resolution = 1;
        for (int i = depth; i >= 0; i--) {
            double region = Math.pow(2, i);
            ImageTile tile = new ImageTile((int) (width / region) + 1, (int) (height / region) + 1, resolution);
            resolution *= 2;
            tiles.add(tile);
        }

        return tiles;
    }

    @XmlElement(name = "width")
    @JsonProperty("width")
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @XmlElement(name = "height")
    @JsonProperty("height")
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @XmlElementWrapper(name = "profiles")
    @XmlElement(name = "profile")
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

    @XmlElementWrapper(name = "sizes")
    @XmlElement(name = "size")
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

    @XmlElementWrapper(name = "tiles")
    @XmlElement(name = "tile")
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

    @XmlElement(name = "service")
    @JsonProperty("service")
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @XmlElement(name = "context")
    @JsonProperty("@context")
    public String getContext() {
        return JSON_CONTEXT;
    }

    @XmlElement(name = "id")
    @JsonProperty("@id")
    public String getId() {
        return id;
    }

    @XmlElement(name = "protocol")
    @JsonProperty("protocol")
    public String getProtocol() {
        return JSON_PROTOCOL;
    }

    @XmlElement(name = "attribution")
    @JsonProperty("attribution")
    public String getAttribution() {
        return attribution;
    }

    @XmlElement(name = "license")
    @JsonProperty("license")
    public String getLicense() {
        return license;
    }

    @XmlElement(name = "logo")
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

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
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import de.intranda.api.PropertyList;
import de.intranda.api.deserializer.ProfileDeserializer;
import de.intranda.api.services.Service;

/**
 * Implementation of the iiif ImageInformation object specified in http://iiif.io/api/image/2.0/#image-information
 * 
 * @author Florian Alpers
 *
 */
@JsonPropertyOrder({ "@context", "@id", "protocol", "width", "height", "attribution", "license", "logo", "sizes", "tiles", "profile", "service" })
@JsonInclude(Include.NON_ABSENT)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageInformation implements Service {

    public static final URI JSON_CONTEXT = URI.create("http://iiif.io/api/image/2/context.json");
    public static final String JSON_PROTOCOL = "http://iiif.io/api/image";
    public static final ComplianceLevel IIIF_COMPLIANCE_LEVEL = ComplianceLevel.level2;

    private final URI id;
    private int width;
    private int height;
    private List<IiifProfile> profiles = new ArrayList<>();
    private List<ImageSize> sizes = new ArrayList<>();
    private List<ImageTile> tiles = new ArrayList<>();
    private List<Service> service = new PropertyList<>();
    private String attribution;
    private String license;
    private String logo;
    private boolean addAuthServices = false;

    public ImageInformation(String id) {
        this(URI.create(id));
    }

    public ImageInformation(URI id) {
        this.id = id;
        addProfile(new ComplianceLevelProfile(IIIF_COMPLIANCE_LEVEL));
    }

    public ImageInformation() {
        this(URI.create(""));
    }

    /**
     * Copy constructor
     * 
     * @param source the ImageInformation object to clone
     */
    public ImageInformation(ImageInformation source) {
        this(source.id);
        this.width = source.width;
        this.height = source.height;
        this.profiles = new ArrayList<>(source.profiles);
        this.sizes = source.sizes.stream().map(ImageSize::new).collect(Collectors.toList());
        this.tiles = source.tiles.stream().map(ImageTile::new).collect(Collectors.toList());
        this.service = new PropertyList<>(source.service);
        this.attribution = source.attribution;
        this.license = source.license;
        this.logo = source.logo;
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
    public List<Service> getService() {
        return service;
    }

    /**
     * Add a service to the list of supported services
     * 
     * @param service
     */
    public void addService(Service service) {
        this.service.add(service);
    }

    /**
     * 
     * @param service
     * @deprecated functionally equivalent to {@link #addService(Service)}. Use that method instead for semantic clarity
     */
    @Deprecated
    public void setService(Service service) {
        this.addService(service);
    }

    @JsonProperty("@context")
    public URI getContext() {
        return JSON_CONTEXT;
    }

    @JsonProperty("@id")
    public URI getId() {
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

    /**
     * @return the addAuthServices
     */
    public boolean isAddAuthServices() {
        return addAuthServices;
    }

    /**
     * @param addAuthServices the addAuthServices to set
     * @return this
     */
    public ImageInformation setAddAuthServices(boolean addAuthServices) {
        this.addAuthServices = addAuthServices;
        return this;
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

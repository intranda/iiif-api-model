/**
 * This file is part of the Goobi viewer - a content presentation and management application for digitized objects.
 *
 * Visit these websites for more information.
 *          - http://www.intranda.com
 *          - http://digiverso.com
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package de.intranda.api.iiif.presentation.v2;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.intranda.api.iiif.presentation.IPresentationModelElement;
import de.intranda.api.serializer.ContentLinkSerializer;

/**
 * @author Florian Alpers
 *
 */
@JsonInclude(Include.NON_EMPTY)
public class Collection2 extends AbstractPresentationModelElement2 implements IPresentationModelElement2 {

    public static final String TYPE = "sc:Collection";
    
    @JsonIgnore
    public final List<Collection2> collections = new ArrayList<>();
    @JsonIgnore
    public final List<Manifest2> manifests = new ArrayList<>();
    private Date navDate = null;
    private final String internalName;

    public Collection2() {
        super(null);
        this.internalName = "";
    }
    
    /**
     * @param id
     */
    public Collection2(URI id, String name) {
        super(id);
        this.internalName = name;
    }
    
    /**
     * @return the collections
     */
//    @JsonSerialize(using = URLOnlySerializer.class)
    @JsonSerialize(using = ContentLinkSerializer.class)
    @JsonIgnore
    public List<Collection2> getCollections() {
        return collections.isEmpty() ? null : collections;
    }
    
    public void addCollection(Collection2 collection) {
        this.collections.add(collection);
    }
    
    /**
     * @return the manifests
     */
//    @JsonSerialize(using = URLOnlySerializer.class)
//    @JsonSerialize(using = ContentLinkSerializer.class)
    @JsonIgnore
    public List<Manifest2> getManifests() {
        return manifests.isEmpty() ? null : manifests;
    }
    
    public void addManifest(Manifest2 manifest) {
        this.manifests.add(manifest);
    }
    
//    @JsonSerialize(using = URLOnlySerializer.class)
//    @JsonSerialize(using = ContentLinkSerializer.class)
//    @JsonIgnore
    public List<IPresentationModelElement> getMembers() {
        List<IPresentationModelElement> list = new ArrayList<>();
        list.addAll(collections);
        list.addAll(manifests);
        return list.isEmpty() ? null : list;
    }
    
    /**
     * @return the navDate
     */
    @JsonFormat(pattern = DATETIME_FORMAT)
    public Date getNavDate() {
        return navDate;
    }
    
    /**
     * @param navDate the navDate to set
     */
    public void setNavDate(Date navDate) {
        this.navDate = navDate;
    }
    
    
    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.AbstractPresentationModelElement#getType()
     */
    @Override
    public String getType() {
        return TYPE;
    }

    @JsonIgnore
    public String getInternalName() {
        return internalName;
    }

    public Optional<Collection2> getCollectionByInternalName(String name) {
        if(name.equals(this.getInternalName())) {
            return Optional.of(this);
        } else {
            for (Collection2 collection : collections) {
                Optional<Collection2> match = collection.getCollectionByInternalName(name);
                if(match.isPresent()) {
                    return match;
                }
            }
        }
        return Optional.empty();
    }


}

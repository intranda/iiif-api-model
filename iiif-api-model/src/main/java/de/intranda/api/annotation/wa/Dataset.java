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
package de.intranda.api.annotation.wa;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.intranda.api.annotation.IResource;
import de.intranda.api.annotation.ITypedResource;
import de.intranda.api.deserializer.DatasetDeserializer;
import de.intranda.api.deserializer.ResourceDeserializer;
import de.intranda.api.serializer.DatasetSerializer;

/**
 * @author florian
 *
 */
@JsonDeserialize(using=DatasetDeserializer.class)
@JsonSerialize(using=DatasetSerializer.class)
public class Dataset extends TypedResource {

    public static final String TYPE = "Dataset";
    public static final String FORMAT_VIEWER = "goobi-viewer-index";
    
    private final Map<String, List<String>> data = new LinkedHashMap<>();    
    
    public Dataset(URI id, String format) {
        super(id, TYPE, format);
    }
    
    public Dataset(URI id) {
        this(id, FORMAT_VIEWER);
    }
    
    public Dataset() {
        this(null);
    }
    
    /**
     * 
     * @return an unmodifieable view on the dataset
     */
    public Map<String, List<String>> getData() {
        return Collections.unmodifiableMap(this.data);
    }
    
    public void setData(String field, String... values) {
        this.data.put(field, new ArrayList<>(Arrays.asList(values)));
    }
    
    public void setData(String field, Collection<String> values) {
        this.data.put(field, new ArrayList<>(values));
    }

}

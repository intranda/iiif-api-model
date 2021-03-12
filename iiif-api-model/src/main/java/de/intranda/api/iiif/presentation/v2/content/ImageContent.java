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
package de.intranda.api.iiif.presentation.v2.content;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.intranda.api.iiif.image.ImageInformation;
import de.intranda.api.iiif.presentation.enums.DcType;
import de.intranda.api.iiif.presentation.enums.Format;
import de.intranda.api.serializer.ImageInformationSerializer;
import de.intranda.metadata.multilanguage.IMetadataValue;

/**
 * @author florian
 *
 */
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageContent implements IContent {

    private static final Logger logger = LoggerFactory.getLogger(ImageContent.class);

    private final DcType TYPE = DcType.IMAGE;

    private final URI id;
    private Integer width = null;
    private Integer height = null;
    private Format format;
    private ImageInformation service;

    public ImageContent() {
        this.id = null;
    }

    public ImageContent(URI id) {
        this.id = id;
    }

    public ImageContent(URI id, ImageInformation service) {
        this.id = id;
        this.service = service;
    }

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.content.IContent#getType()
     */
    @Override
    public DcType getType() {
        return TYPE;
    }

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.content.IContent#getWidth()
     */
    @Override
    public Integer getWidth() {
        return width;
    }

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.content.IContent#setWidth(int)
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.content.IContent#getHeight()
     */
    @Override
    public Integer getHeight() {
        return height;
    }

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.content.IContent#setHeight(int)
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.content.IContent#getFormat()
     */
    @Override
    public Format getFormat() {
        return format;
    }

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.content.IContent#setFormat(de.intranda.digiverso.presentation.model.iiif.presentation.enums.Format)
     */
    public void setFormat(Format format) {
        this.format = format;
    }

    /**
     * @return the service
     */
    public ImageInformation getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    @JsonSerialize(using = ImageInformationSerializer.class)
    public void setService(ImageInformation service) {
        this.service = service;
    }

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.content.IContent#getId()
     */
    @Override
    public URI getId() {
        return id;
    }

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.content.IContent#getLabel()
     */
    @Override
    public IMetadataValue getLabel() {
        return null;
    }

}

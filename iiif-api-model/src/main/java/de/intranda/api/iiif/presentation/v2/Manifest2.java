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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

/**
 * @author Florian Alpers
 *
 */
public class Manifest2 extends AbstractPresentationModelElement2 implements IPresentationModelElement2 {

    public static final String TYPE = "sc:Manifest";
    public final List<Sequence> sequences = new ArrayList<>(1);
    public final List<Range2> structures = new ArrayList<>(1);
    public LocalDateTime navDate = null;

    public Manifest2() {
        super();
    }

    /**
     * @param id
     */
    public Manifest2(URI id) {
        super(id);
    }

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.AbstractPresentationModelElement#getType()
     */
    @Override
    public String getType() {
        return TYPE;
    }

    /**
     * @return the sequences
     */
    public List<Sequence> getSequences() {
        return sequences;
    }

    public void setSequence(Sequence sequence) {
        if (this.sequences.isEmpty()) {
            this.sequences.add(sequence);
        } else {
            this.sequences.set(0, sequence);
        }
    }

    /**
     * @return the structures
     */
    public List<Range2> getStructures() {
        return structures;
    }

    public void addStructure(Range2 range) {
        this.structures.add(range);
    }

    /**
     * @return the navDate
     */
    @Override
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_FORMAT)
    public LocalDateTime getNavDate() {
        return navDate;
    }

    /**
     * @param navDate the navDate to set
     */
    @Override
    public void setNavDate(LocalDateTime navDate) {
        this.navDate = navDate;
    }

}

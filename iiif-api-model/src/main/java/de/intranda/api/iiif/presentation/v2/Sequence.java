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
import java.util.Collections;
import java.util.List;

import de.intranda.api.iiif.presentation.IPresentationModelElement;
import de.intranda.api.iiif.presentation.enums.ViewingDirection;
import de.intranda.api.iiif.presentation.enums.ViewingHint;

/**
 * @author Florian Alpers
 *
 */
public class Sequence extends AbstractPresentationModelElement2 {

    private static final String TYPE = "sc:Sequence";

    private final List<Canvas2> canvases = new ArrayList<>();
    private Canvas2 startCanvas = null;

    public Sequence() {
        addViewingHint(ViewingHint.paged);
    }

    /**
     * @param id
     */
    public Sequence(URI id) {
        super(id);
        addViewingHint(ViewingHint.paged);
    }

    /* (non-Javadoc)
     * @see de.intranda.digiverso.presentation.model.iiif.presentation.AbstractPresentationModelElement#getType()
     */
    @Override
    public String getType() {
        return TYPE;
    }

    /**
     * @return the images
     */
    public List<Canvas2> getCanvases() {
        return canvases.isEmpty() ? null : canvases;
    }

    public void addCanvas(Canvas2 image) {
        this.canvases.add(image);
    }


    /*TODO: viewingdirection dependent on configuration or metadata*/
    public ViewingDirection getViewingDirection() {
        return ViewingDirection.LEFT_TO_RIGHT;
    }
    
    public void setStartCanvas(Canvas2 startCanvas) {
		this.startCanvas = startCanvas;
	}
    
    public Canvas2 getStartCanvas() {
		return startCanvas;
	}

}

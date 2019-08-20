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

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Florian Alpers
 *
 */
public class Motivation {

    public static final String PAINTING = "Painting";
    public static final String COMMENTING = "Commenting";
    public static final String LINKING = "Linking";
    public static final String BOOKMARKING = "Bookmarking";
    public static final String CLASSIFYING = "Classifying";
    public static final String DESCRIBING = "Describing";
    public static final String EDITING = "Editing";
    public static final String HIGHLIGHTING = "Highlighting";
    public static final String IDENTIFIYING = "Identifying";
    public static final String MODERATING = "Moderating";
    public static final String QUESTIONING = "Questioning";
    public static final String REPLYING = "Replying";
    public static final String TAGGING = "Tagging";

    private Motivation() {}
}

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

    public static final String PAINTING = "painting";
    public static final String ASSESSING = "assessing";
    public static final String COMMENTING = "commenting";
    public static final String LINKING = "linking";
    public static final String BOOKMARKING = "bookmarking";
    public static final String CLASSIFYING = "classifying";
    public static final String DESCRIBING = "describing";
    public static final String EDITING = "editing";
    public static final String HIGHLIGHTING = "highlighting";
    public static final String IDENTIFIYING = "identifying";
    public static final String MODERATING = "moderating";
    public static final String QUESTIONING = "questioning";
    public static final String REPLYING = "replying";
    public static final String TAGGING = "tagging";

    private Motivation() {}
}

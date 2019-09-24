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
package de.intranda.api.annotation.oa;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Florian Alpers
 *
 */
public class Motivation {

    public static final String PAINTING = "sc:painting";
    public static final String COMMENTING = "oa:commenting";
    public static final String LINKING = "oa:linking";
    public static final String BOOKMARKING = "oa:bookmarking";
    public static final String CLASSIFYING = "oa:classifying";
    public static final String DESCRIBING = "oa:describing";
    public static final String EDITING = "oa:editing";
    public static final String HIGHLIGHTING = "oa:highlighting";
    public static final String IDENTIFIYING = "oa:identifying";
    public static final String MODERATING = "oa:moderating";
    public static final String QUESTIONING = "oa:questioning";
    public static final String REPLYING = "oa:replying";
    public static final String TAGGING = "oa:tagging";

    public static String convertFromWebAnnotationMotivation(String value) {
        if(StringUtils.isNotBlank(value)) {            
            return "oa:" + value.toLowerCase();
        } else {
            return null;
        }
    }
    
    private Motivation() {}
}

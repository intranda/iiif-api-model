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
package de.intranda.api.services;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Florian Alpers
 *
 */
public class ImageReaderDefinition {

    private static final Context SCHEMA = new Context("schema", "https://schema.org/");

    private static final Definition NAME = new Definition(SCHEMA, "name");

    public static final String URI_PATH = "imageReaders/context.json";

    private IContextDefinition context = new IContextDefinition() {

        public Context getSchema() {
            return SCHEMA;
        }

        public Definition getName() {
            return NAME;
        }

    };

    @JsonProperty("@context")
    public IContextDefinition getContext() {
        return context;
    }

}

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
package de.intranda.metadata.multilanguage;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.UnaryOperator;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.intranda.api.deserializer.IMetadataValueDeserializer;
import de.intranda.api.serializer.WebAnnotationMetadataValueSerializer;
import de.intranda.metadata.multilanguage.MultiLanguageMetadataValue.ValuePair;

/**
 * Interface to access both single and multi language metadata. Both implementations of this interface contain simple Strings representing the
 * metadata value. In the case of SimpleMetadataValue there is only a single String, MultiLanguageMetadataValue on the other hand can include a number
 * of different value Strings for different languages, additionall to a default value which is either a String not assciated with a language or the
 * first language bound value to be set
 * 
 * @author Florian Alpers
 *
 */
@JsonDeserialize(using = IMetadataValueDeserializer.class)
@JsonSerialize(using=WebAnnotationMetadataValueSerializer.class)
public interface IMetadataValue {

    /**
     * Set the value for a specific locale
     * 
     * @param value
     * @param locale
     */
    public void setValue(String value, Locale locale);

    /**
     * Set the value for a specific locale
     * 
     * @param value
     * @param locale
     */
    public void setValue(String value, String locale);

    /**
     * Set the value for the default locale {@code _DEFAULT} or the first available locale if no default locale exists
     * 
     * @param value
     * @param locale
     */
    public void setValue(String value);

    /**
     * Get the value for a specific locale
     * 
     * @param value
     * @param locale
     */
    public Optional<String> getValue(Locale language);

    /**
     * Get the value for a specific locale
     * 
     * @param value
     * @param locale
     */
    public Optional<String> getValue(String language);

    /**
     * Get the value for the default locale {@code _DEFAULT}
     * 
     * @param value
     * @param locale
     */
    public Optional<String> getValue();

    /**
     * @return A collection of all languages for which values exist. If no language specific values exist, only {@code _DEFAULT} is returned
     */
    public Collection<String> getLanguages();

    /**
     * Prepend the given string to all values
     *
     * @param prefix
     */
    public void addPrefix(String prefix);

    /**
     * Append the given string to all values
     *
     * @param prefix
     */
    public void addSuffix(String suffix);

    /**
     * Sets each value to the result of the given {@link UnaryOpeator} with the original value as input parameter
     * 
     * @param function
     */
    public void mapEach(UnaryOperator<String> function);

    /**
     * Removed the translation of the given locale from the values
     * 
     * @param locale
     */
    public void removeTranslation(String locale);

    /**
     * @return true if no values are stored in this object
     */
    public boolean isEmpty();

    /**
     * @param locale
     * @return true if no entry is set for the given locale
     */
    public boolean isEmpty(Locale locale);

    /**
     * @param locale
     * @return true if no entry is set for the given locale
     */
    public boolean isEmpty(String locale);

    public List<ValuePair> getValues();

}

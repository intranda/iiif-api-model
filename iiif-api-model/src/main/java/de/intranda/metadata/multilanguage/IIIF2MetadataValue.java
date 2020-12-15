package de.intranda.metadata.multilanguage;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.UnaryOperator;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import de.intranda.api.serializer.IIIF2MetadataSerializer;
import de.intranda.metadata.multilanguage.MultiLanguageMetadataValue.ValuePair;

@JsonSerialize(using=IIIF2MetadataSerializer.class)
public class IIIF2MetadataValue implements IMetadataValue {

    private final IMetadataValue value;
    
    public IIIF2MetadataValue(IMetadataValue value) {
        this.value = value;
    }
    
    @Override
    public void setValue(String value, Locale locale) {
        this.value.setValue(value, locale);
    }

    @Override
    public void setValue(String value, String locale) {
        this.value.setValue(value, locale);
    }

    @Override
    public void setValue(String value) {
        this.value.getValue(value);
    }

    @Override
    public Optional<String> getValue(Locale language) {
        return this.value.getValue(language);
    }

    @Override
    public Optional<String> getValue(String language) {
        return this.value.getValue(language);
    }

    @Override
    public Optional<String> getValue() {
        return this.value.getValue();

    }

    @Override
    public Collection<String> getLanguages() {
        return this.value.getLanguages();

    }

    @Override
    public void addPrefix(String prefix) {
        this.value.addPrefix(prefix);
    }

    @Override
    public void addSuffix(String suffix) {
        this.value.addSuffix(suffix);
    }

    @Override
    public void mapEach(UnaryOperator<String> function) {
        this.value.mapEach(function);
    }

    @Override
    public void removeTranslation(String locale) {
        this.value.removeTranslation(locale);
    }

    @Override
    public boolean isEmpty() {
        return this.value.isEmpty();
    }

    @Override
    public boolean isEmpty(Locale locale) {
        return this.value.isEmpty(locale);
    }

    @Override
    public boolean isEmpty(String locale) {
        return this.value.isEmpty();
    }

    @Override
    public List<ValuePair> getValues() {
        return this.value.getValues();
    }
  
}

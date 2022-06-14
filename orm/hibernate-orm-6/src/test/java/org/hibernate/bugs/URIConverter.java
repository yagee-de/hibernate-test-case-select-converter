package org.hibernate.bugs;

import java.net.URI;
import java.util.Optional;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class URIConverter implements AttributeConverter<URI, String> {

    @Override
    public String convertToDatabaseColumn(URI attribute) {
        return Optional.ofNullable(attribute)
            .map(URI::toString)
            .orElse(null);
    }

    @Override
    public URI convertToEntityAttribute(String dbData) {
        return Optional.ofNullable(dbData)
            .map(URI::create)
            .orElse(null);
    }

}

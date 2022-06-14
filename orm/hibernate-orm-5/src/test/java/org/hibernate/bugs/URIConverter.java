package org.hibernate.bugs;

import java.net.URI;
import java.util.Optional;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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

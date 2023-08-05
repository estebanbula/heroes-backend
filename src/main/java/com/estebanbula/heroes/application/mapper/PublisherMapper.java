package com.estebanbula.heroes.application.mapper;

import com.estebanbula.heroes.domain.model.Publisher;
import com.estebanbula.heroes.infraestructure.persistence.document.PublisherDocument;

public class PublisherMapper {

    private PublisherMapper() {}

    public static Publisher toDomain(PublisherDocument document) {
        return Publisher.builder()
                .id(document.getId())
                .name(document.getName())
                .build();
    }

    public static PublisherDocument toDocument(Publisher model) {
        return PublisherDocument.builder()
                .id(model.getId())
                .name(model.getName())
                .build();
    }
}

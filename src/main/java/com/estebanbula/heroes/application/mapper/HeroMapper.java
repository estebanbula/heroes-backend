package com.estebanbula.heroes.application.mapper;

import com.estebanbula.heroes.domain.model.Hero;
import com.estebanbula.heroes.infraestructure.persistence.document.HeroDocument;

public class HeroMapper {

    private HeroMapper() {
    }

    public static Hero toDomain(HeroDocument entity) {
        return Hero.builder()
                .id(entity.getId())
                .superhero(entity.getSuperhero())
                .publisher(PublisherMapper.toDomain(entity.getPublisher()))
                .alterEgo(entity.getAlterEgo())
                .characters(entity.getCharacters())
                .firstAppearance(entity.getFirstAppearance())
                .altImage(entity.getAltImage())
                .description(entity.getDescription())
                .state(entity.getState())
                .build();
    }

    public static HeroDocument toDocument(Hero model) {
        return HeroDocument.builder()
                .id(model.getId())
                .superhero(model.getSuperhero())
                .publisher(PublisherMapper.toDocument(model.getPublisher()))
                .alterEgo(model.getAlterEgo())
                .characters(model.getCharacters())
                .firstAppearance(model.getFirstAppearance())
                .altImage(model.getAltImage())
                .description(model.getDescription())
                .state(model.getState())
                .build();
    }
}

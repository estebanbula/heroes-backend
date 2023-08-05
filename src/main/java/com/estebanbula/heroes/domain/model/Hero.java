package com.estebanbula.heroes.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hero {

    private String id;
    private String superhero;
    private Publisher publisher;
    private String alterEgo;
    private String firstAppearance;
    private String characters;
    private String altImage;
    private String description;
    private String state;
}
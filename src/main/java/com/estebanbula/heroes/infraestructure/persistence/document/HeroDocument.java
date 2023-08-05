package com.estebanbula.heroes.infraestructure.persistence.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "heroes")
public class HeroDocument {

    @Id
    private String id;
    private String superhero;
    private PublisherDocument publisher;
    private String alterEgo;
    private String firstAppearance;
    private String characters;
    private String altImage;
    private String description;
    private String state;
}

package com.estebanbula.heroes.infraestructure.persistence.repository;

import com.estebanbula.heroes.infraestructure.persistence.document.PublisherDocument;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends ReactiveCrudRepository<PublisherDocument, String> {
}

package com.estebanbula.heroes.infraestructure.persistence.repository;

import com.estebanbula.heroes.infraestructure.persistence.document.UserDocument;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveCrudRepository<UserDocument, String> {
}

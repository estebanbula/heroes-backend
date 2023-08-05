package com.estebanbula.heroes.domain.usecase;

import com.estebanbula.heroes.application.mapper.PublisherMapper;
import com.estebanbula.heroes.domain.model.Publisher;
import com.estebanbula.heroes.infraestructure.persistence.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class PublisherUseCase {

    private final PublisherRepository repository;

    public Flux<Publisher> retrievePublishers() {
        return repository.findAll()
                .map(PublisherMapper::toDomain);
    }

}

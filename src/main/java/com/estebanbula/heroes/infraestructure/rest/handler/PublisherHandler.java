package com.estebanbula.heroes.infraestructure.rest.handler;

import com.estebanbula.heroes.domain.model.Publisher;
import com.estebanbula.heroes.domain.usecase.PublisherUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PublisherHandler {

    private final PublisherUseCase useCase;

    public Mono<ServerResponse> retrievePublishers(ServerRequest serverRequest) {
        return ServerResponse.ok().body(useCase.retrievePublishers(), Publisher.class);
    }
}

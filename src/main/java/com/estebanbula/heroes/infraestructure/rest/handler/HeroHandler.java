package com.estebanbula.heroes.infraestructure.rest.handler;

import com.estebanbula.heroes.domain.model.Hero;
import com.estebanbula.heroes.domain.usecase.HeroUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class HeroHandler {

    private final HeroUseCase useCase;

    public Mono<ServerResponse> retrieveHeroes(ServerRequest serverRequest) {
        return ServerResponse.ok().body(useCase.retrieveHeroes(), Hero.class);
    }

    public Mono<ServerResponse> saveNewHero(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Hero.class)
                .flatMap(useCase::saveHero)
                .flatMap(ServerResponse.status(HttpStatus.CREATED)::bodyValue);
    }

    public Mono<ServerResponse> deleteHero(ServerRequest serverRequest) {
        return useCase.deleteHero(serverRequest.pathVariable("id"))
                .flatMap(ServerResponse.status(HttpStatus.OK)::bodyValue);
    }

    public Mono<ServerResponse> updateHero(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Hero.class)
                .flatMap(hero -> useCase.updateHero(serverRequest.pathVariable("id"), hero))
                .flatMap(ServerResponse.status(HttpStatus.OK)::bodyValue);
    }

    public Mono<ServerResponse> retrieveById(ServerRequest serverRequest) {
        return useCase.retrieveHeroById(serverRequest.pathVariable("id"))
                .flatMap(ServerResponse.status(HttpStatus.OK)::bodyValue);
    }
}

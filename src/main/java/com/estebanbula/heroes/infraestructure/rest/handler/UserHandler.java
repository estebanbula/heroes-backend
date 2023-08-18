package com.estebanbula.heroes.infraestructure.rest.handler;

import com.estebanbula.heroes.domain.model.User;
import com.estebanbula.heroes.domain.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserHandler {

    private final UserUseCase useCase;

    public Mono<ServerResponse> retrieveById(ServerRequest serverRequest) {
        return useCase.searchUserById(serverRequest.pathVariable("id"))
                .flatMap(ServerResponse.status(HttpStatus.OK)::bodyValue);
    }

    public Mono<ServerResponse> saveUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(User.class)
                .flatMap(useCase::saveNewUser)
                .flatMap(ServerResponse.status(HttpStatus.CREATED)::bodyValue);
    }

    public Mono<ServerResponse> updateUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(User.class)
                .flatMap(user -> useCase.updateUser(serverRequest.pathVariable("id"), user))
                .flatMap(ServerResponse.status(HttpStatus.OK)::bodyValue);
    }
}

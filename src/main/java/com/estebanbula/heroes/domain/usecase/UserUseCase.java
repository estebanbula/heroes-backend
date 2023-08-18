package com.estebanbula.heroes.domain.usecase;

import com.estebanbula.heroes.application.mapper.UserMapper;
import com.estebanbula.heroes.domain.model.User;
import com.estebanbula.heroes.infraestructure.persistence.document.UserDocument;
import com.estebanbula.heroes.infraestructure.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository repository;

    public Mono<User> searchUserById(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new NoSuchElementException("The user doesn't exists!")))
                .map(UserMapper::toModel);
    }

    public Mono<User> saveNewUser(User user) {
        return repository.save(UserMapper.toDocument(user))
                .map(UserMapper::toModel);
    }

    public Mono<User> updateUser(String id, User user) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new NoSuchElementException("The user doesn't exist!")))
                .map(currentUser -> {
                    currentUser.setId(user.getId());
                    currentUser.setUser(user.getUser());
                    currentUser.setEmail(user.getEmail());
                    return currentUser;
                }).flatMap(repository::save)
                .map(UserMapper::toModel);
    }
}

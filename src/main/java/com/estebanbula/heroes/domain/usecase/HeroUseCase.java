package com.estebanbula.heroes.domain.usecase;

import com.estebanbula.heroes.application.mapper.HeroMapper;
import com.estebanbula.heroes.application.mapper.PublisherMapper;
import com.estebanbula.heroes.domain.model.Hero;
import com.estebanbula.heroes.infraestructure.persistence.repository.HeroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HeroUseCase {

    private final HeroRepository repository;

    public Flux<Hero> retrieveHeroes() {
        return repository.findAll()
                .map(HeroMapper::toDomain);
    }

    public Mono<Hero> retrieveHeroById(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new NoSuchElementException("There is no hero for this id")))
                .map(HeroMapper::toDomain);
    }

    @Transactional
    public Mono<Hero> saveHero(Hero hero) {
        hero.setId(UUID.randomUUID().toString());
        hero.setState("A");
        return repository.save(HeroMapper.toDocument(hero))
                .switchIfEmpty(Mono.error(new NoSuchElementException("We have an error saving the hero. Please try latter")))
                .map(HeroMapper::toDomain);
    }

    public Mono<Hero> updateHero(String id, Hero hero) {
        return repository.findById(id)
                .map(currentHero -> {
                    currentHero.setSuperhero(hero.getSuperhero());
                    currentHero.setPublisher(PublisherMapper.toDocument(hero.getPublisher()));
                    currentHero.setAlterEgo(hero.getAlterEgo());
                    currentHero.setFirstAppearance(hero.getFirstAppearance());
                    currentHero.setCharacters(hero.getCharacters());
                    currentHero.setAltImage(hero.getAltImage());
                    currentHero.setDescription(hero.getDescription());
                    currentHero.setState(hero.getState());
                    return currentHero;
                })
                .flatMap(repository::save)
                .map(HeroMapper::toDomain)
                .switchIfEmpty(Mono.error(new NoSuchElementException("We have an error updating the hero. Please try latter")));
    }

    public Mono<Hero> deleteHero(String id) {
        return repository.findById(id)
                .map(currentHero -> {
                    currentHero.setState("D");
                    return currentHero;
                })
                .flatMap(repository::save)
                .map(HeroMapper::toDomain)
                .switchIfEmpty(Mono.error(new NoSuchElementException("We have an error deleting the hero. Please try latter")));
    }
}

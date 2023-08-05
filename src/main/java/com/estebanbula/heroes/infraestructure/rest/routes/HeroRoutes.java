package com.estebanbula.heroes.infraestructure.rest.routes;

import com.estebanbula.heroes.infraestructure.rest.handler.HeroHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class HeroRoutes {

    @Bean
    public RouterFunction<ServerResponse> heroRoute(HeroHandler handler) {
        return nest(path("/api/heroes"),
                route()
                        .GET("", handler::retrieveHeroes)
                        .POST("", handler::saveNewHero)
                        .GET("{id}", handler::retrieveById)
                        .DELETE("{id}", handler::deleteHero)
                        .PUT("{id}", handler::updateHero).build());
    }
}

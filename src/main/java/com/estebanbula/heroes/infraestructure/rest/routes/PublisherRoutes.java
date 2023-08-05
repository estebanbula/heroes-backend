package com.estebanbula.heroes.infraestructure.rest.routes;

import com.estebanbula.heroes.infraestructure.rest.handler.PublisherHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class PublisherRoutes {

    @Bean
    public RouterFunction<ServerResponse> publisherRoute(PublisherHandler handler) {
        return nest(path("/api/publisher"),
                route()
                        .GET("", handler::retrievePublishers).build());
    }
}

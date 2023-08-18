package com.estebanbula.heroes.infraestructure.rest.routes;

import com.estebanbula.heroes.infraestructure.rest.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserRoutes {

    @Bean
    public RouterFunction<ServerResponse> userRoute(UserHandler handler) {
        return nest(path("/api/users"),
                route()
                        .GET("{id}", handler::retrieveById)
                        .POST("", handler::saveUser)
                        .PUT("{id}", handler::updateUser).build());
    }
}

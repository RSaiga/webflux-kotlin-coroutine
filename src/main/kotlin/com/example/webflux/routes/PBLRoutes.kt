package com.example.webflux.routes

import com.example.webflux.handlers.PBLHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class PBLRoutes {
    @Bean
    fun router(handler: PBLHandler) = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            GET("/todos", handler::search)
        }
    }
}

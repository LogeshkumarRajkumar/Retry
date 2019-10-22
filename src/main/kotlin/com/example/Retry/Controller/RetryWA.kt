package com.example.Retry.Controller

import io.github.resilience4j.retry.annotation.Retry
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Controller
class Retry4jControllerWA{

    @GetMapping("/retryWA")
    @Retry(name="retryTest")
    fun retryWA(): Mono<String> {
        val client = WebClient
                .builder()
                .baseUrl("http://localhost:8000")
                .build()

        return client.get().uri("/failure")
                .retrieve().bodyToMono(String::class.java)
    }
}
package com.example.Retry.Controller

import io.github.resilience4j.reactor.retry.RetryOperator
import io.github.resilience4j.retry.Retry
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Controller
class Retry4jController{
    @GetMapping("/retry")
    fun retry(): Mono<String> {
        println("hai")
        val client = WebClient
                .builder()
                .baseUrl("http://localhost:8000")
                .build()

        val response: Mono<String> =  client.get().uri("/failure")
                .retrieve().bodyToMono(String::class.java)

        return response.compose(RetryOperator.of(Retry.ofDefaults("test")))

    }
}
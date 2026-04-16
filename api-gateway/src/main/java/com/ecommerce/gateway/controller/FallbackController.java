package com.ecommerce.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/{service}")
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public Mono<Map<String, String>> fallback(@PathVariable String service) {
        return Mono.just(Map.of(
                "status", "error",
                "message", "Serviço " + service + " temporariamente indisponível.",
                "hint", "Tente novamente em alguns instantes."
        ));
    }

}

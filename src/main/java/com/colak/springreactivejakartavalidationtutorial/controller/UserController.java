package com.colak.springreactivejakartavalidationtutorial.controller;

import com.colak.springreactivejakartavalidationtutorial.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/v1/user")

public class UserController {

    // http://localhost:8080/v1/user/hello
    @GetMapping(path = "hello")
    public Mono<String> hello() {
        return Mono.just("Hello");
    }

    // curl -X POST http://localhost:8080/v1/user/create -H "Content-Type: application/json" -d "{\"id\": 0, \"name\": \"string\", \"age\": 1}"
    @PostMapping(path = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<String> createUser(@Valid @RequestBody UserDTO userDTO) {
        return Mono.just("Created user successfully " + userDTO.toString());
    }
}

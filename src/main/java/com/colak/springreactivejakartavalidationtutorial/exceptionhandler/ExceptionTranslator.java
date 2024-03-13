package com.colak.springreactivejakartavalidationtutorial.exceptionhandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)

@Slf4j
@RequiredArgsConstructor
public class ExceptionTranslator {

    private final ObjectMapper objectMapper;

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<List<String>> handleException(WebExchangeBindException exception) {
        List<String> errors = exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        return ResponseEntity.badRequest().body(errors);
    }


    // See https://github.com/sreedeviPayikkad/user-management-webflux/blob/main/src/main/java/io/springwebflux/usermanagement/exception/UserManagementControllerAdvice.java
    @ExceptionHandler({Exception.class})
    public Mono<Void> handleException(ServerWebExchange exchange, Exception exception) throws JsonProcessingException {
        log.info("runtime exception: {}", exception.getMessage());

        exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return writeResponse(exchange, objectMapper.writeValueAsBytes(Map.of("msg", exception.getMessage())));
    }

    private Mono<Void> writeResponse(ServerWebExchange exchange, final byte[] responseBytes) {
        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(responseBytes)));
    }
}

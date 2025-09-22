/**
 * Handles JWT validation errors globally in the API Gateway.
 *
 * <p>Catches {@link WebClientResponseException.Unauthorized} from the Auth Service
 * and returns a 401 Unauthorized response without exposing internal details.</p>
 *
 * <p>Ensures consistent error handling for invalid or expired tokens.</p>
 */

package com.carvalho.api_gateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class JwtValidationException {

    @ExceptionHandler(WebClientResponseException.Unauthorized.class)
    public Mono<Void> handleUnauthorizedException(ServerWebExchange exchange){
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

}
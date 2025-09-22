/**
 * JwtValidationGatewayFilterFactory
 *
 * A custom Spring Cloud Gateway filter for validating JWT tokens before allowing requests
 * to pass through to downstream services.
 *
 * <p>This filter intercepts incoming requests, checks for the presence of an Authorization header
 * with a valid Bearer token, and forwards the token to the Authentication Service for validation.
 * If the token is missing or invalid, the request is immediately rejected with a 401 Unauthorized status.</p>
 *
 * <h2>Workflow:</h2>
 * <ol>
 *   <li>Extract the Authorization header from the request.</li>
 *   <li>Verify the header exists and starts with "Bearer ".</li>
 *   <li>Send a validation request to the Authentication Service's <code>/validate</code> endpoint.</li>
 *   <li>If the validation succeeds, forward the request to the target route.</li>
 *   <li>If validation fails or the header is missing, return HTTP 401 Unauthorized.</li>
 * </ol>
 *
 * <h2>Configuration:</h2>
 * <ul>
 *   <li><b>authServiceUrl</b> – Base URL of the Authentication Service (injected via @Value).</li>
 *   <li>Uses Spring's {@link WebClient} for non-blocking HTTP calls.</li>
 * </ul>
 *
 * <h2>Usage in application.properties:</h2>
 * <pre>
 * spring.cloud.gateway.routes[0].filters[0]=JwtValidation
 * </pre>
 *
 * <h2>Example Gateway Route with JWT Validation:</h2>
 * <pre>
 * spring.cloud.gateway.routes[1].id=secure-service
 * spring.cloud.gateway.routes[1].uri=http://localhost:8081
 * spring.cloud.gateway.routes[1].predicates[0]=Path=/secure/**
 * spring.cloud.gateway.routes[1].filters[0]=JwtValidation
 * </pre>
 *
 * @author
 * Pedro Carvalho
 */

package com.carvalho.api_gateway.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class JwtValidationGatewayFilterFactory extends AbstractGatewayFilterFactory{

    private final WebClient webClient;

    public JwtValidationGatewayFilterFactory(WebClient.Builder webClientBuilder, @Value("http://localhost:8087/api/v1/authentication") String authServiceUrl) {
        this.webClient = webClientBuilder.baseUrl(authServiceUrl).build();
    }

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            String token =
                    exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            if(token == null || !token.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            return webClient.get()
                    .uri("/validate")
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .retrieve()
                    .toBodilessEntity()
                    .then(chain.filter(exchange));
        };
    }
}

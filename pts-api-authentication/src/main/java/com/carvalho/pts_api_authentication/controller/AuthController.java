package com.carvalho.pts_api_authentication.controller;

import com.carvalho.pts_api_authentication.dto.LoginRequestDto;
import com.carvalho.pts_api_authentication.dto.LoginResponseDto;
import com.carvalho.pts_api_authentication.dto.RegisterRequestDto;
import com.carvalho.pts_api_authentication.service.AuthService;
import io.jsonwebtoken.JwtException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequestDto request) {
        authService.register(request);
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Generate token on user login")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        Optional<String> tokenOptional = authService.authenticate(loginRequestDto);

        if (tokenOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = tokenOptional.get();
        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @GetMapping("/validate")
    public ResponseEntity<Map<String, Object>> validateToken(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        Map<String, Object> response = new HashMap<>();

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.put("valid", false);
            response.put("message", "Authorization header is missing or does not start with Bearer");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        String token = authHeader.substring(7);

        try {
            boolean valid = authService.validateToken(token);
            if (valid) {
                response.put("valid", true);
                response.put("message", "Token is valid");
                return ResponseEntity.ok(response);
            } else {
                response.put("valid", false);
                response.put("message", "Token is invalid");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } catch (JwtException e) {
            response.put("valid", false);
            response.put("message", "Token validation error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }


}

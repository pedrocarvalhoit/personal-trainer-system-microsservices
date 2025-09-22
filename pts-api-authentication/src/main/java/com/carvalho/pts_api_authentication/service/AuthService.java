package com.carvalho.pts_api_authentication.service;

import com.carvalho.pts_api_authentication.dto.LoginRequestDto;
import com.carvalho.pts_api_authentication.dto.RegisterRequestDto;
import com.carvalho.pts_api_authentication.dto.UserCreatedEventDto;
import com.carvalho.pts_api_authentication.entity.AuthUser;
import com.carvalho.pts_api_authentication.repository.AuthUserRepository;
import com.carvalho.pts_api_authentication.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Slf4j
@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthUserRepository authUserRepository;
    private final AuthUserEventPublisherService publisher;

    public AuthService(PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil, AuthUserRepository authUserRepository, AuthUserEventPublisherService publisher) {
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authUserRepository = authUserRepository;
        this.publisher = publisher;
    }

    //If is a Athlete Registration, angular puts the Athlete on Role and send the Personal Trainer Id
    public void register(RegisterRequestDto request) {
        AuthUser entity = AuthUser.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        authUserRepository.save(entity);

        log.info(entity.getId().toString());
        publisher.publish(UserCreatedEventDto.builder()
                .id(entity.getId().toString())
                .email(entity.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateOfBirth(request.getDateOfBirth())
                .phone(request.getPhone())
                .gender(request.getGender())
                .role(request.getRole())
                .personalTrainerId(request.getPersonalTrainerId())
                .build());
    }

    public Optional<String> authenticate(LoginRequestDto loginRequestDto) {
        Optional<String> token = findByEmail(loginRequestDto.getEmail())
                .filter(u -> passwordEncoder.matches(loginRequestDto.getPassword(), u.getPassword()))
                .map(u -> jwtUtil.generateToken(u.getEmail(), u.getId().toString(), u.getRole()));

        return token;
    }

    public boolean validateToken(String token) {
        try {
            jwtUtil.validateToken(token);
            return true;
        } catch (JwtException e){
            return false;
        }
    }

    public Optional<AuthUser> findByEmail(String email) {
        return authUserRepository.findByEmail(email);
    }
}

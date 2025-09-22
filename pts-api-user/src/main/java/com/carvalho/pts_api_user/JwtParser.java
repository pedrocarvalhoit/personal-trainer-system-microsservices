package com.carvalho.pts_api_user;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtParser {

    @Value("${jwt.secret}")
    private String secret;

    private Key secretKey;

    @PostConstruct
    public void init(){
        byte[] KeyBites = Decoders.BASE64.decode(secret);
        this.secretKey = Keys.hmacShaKeyFor(KeyBites);
    }

    public String extractUsername(String token) {
        if(token.startsWith("Bearer ")){
            token = token.substring(7);
        }
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}

package com.codequest.security;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY = "CodeQuestSuperSecretKeyForJwtAuthentication2026";

    private static final long ExPIRATION_TIME = 1000 * 60 * 60 * 24; // 24 hours

    private final SecretKey key;

    public JwtService() {
        this.key = Keys.hmacShaKeyFor(
                SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + ExPIRATION_TIME);

        return Jwts.builder()
                .subject(email)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(key)
                .compact();

    }

    public String extractEmail(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean isTokenValid(String token) {

    try {

        Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);

        System.out.println("JWT VALID");

        return true;

    } catch (Exception e) {

        System.out.println("JWT INVALID: " + e.getClass().getName());
        System.out.println("JWT ERROR: " + e.getMessage());

        return false;
    }

}

}
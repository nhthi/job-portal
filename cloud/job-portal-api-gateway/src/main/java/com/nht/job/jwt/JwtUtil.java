package com.nht.job.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.function.ServerRequest;

import javax.crypto.SecretKey;

@Service
public class JwtUtil {

    private final SecretKey secretKey = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public Claims extractAllClaims(String token){
        System.out.println(4);
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractEmail(String token){
        return extractAllClaims(token).get("email",String.class);
    }

    public String extractAuthorities(String token){
        return extractAllClaims(token).get("authorities",String.class);
    }

    public Long extractUserId(String token){
        return extractAllClaims(token).get("userId",Long.class);
    }

    public boolean isTokenValid(String token){

        try {
            extractAllClaims(token);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }

    }


}

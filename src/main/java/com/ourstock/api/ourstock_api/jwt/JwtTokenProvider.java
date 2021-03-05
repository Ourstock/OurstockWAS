package com.ourstock.api.ourstock_api.jwt;


import com.ourstock.api.ourstock_api.handler.user.JwtTokenException;
import com.ourstock.api.ourstock_api.model.UserEntity;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtTokenProvider {

    private final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    public final static long TOKEN_VALIDATION_SECOND = 1000L * 60 * 30 * 2;
    public final static long REFRESH_TOKEN_VALIDATION_SECOND = 1000L * 60 * 90 * 2;


    @Value("${jwt.secret}")
    private String secretKey;

    private Key getSigningKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey(secretKey))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public UUID getUserId(String token) {
        return extractAllClaims(token).get("userId", UUID.class);
    }

    public Boolean isTokenExpired(String token) {
        final Date expiration = extractAllClaims(token).getExpiration();
        return expiration.after(new Date());
    }

    public String generateToken(UserEntity userEntity) {
        return doGenerateToken(userEntity.getUserId(), TOKEN_VALIDATION_SECOND);
    }

    public String generateRefreshToken(UserEntity userEntity) {
        return doGenerateToken(userEntity.getUserId(), REFRESH_TOKEN_VALIDATION_SECOND);
    }

    public String doGenerateToken(UUID userId, long expireTime) {
        Claims claims = Jwts.claims();
        claims.put("userId", userId);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expireTime))
                .signWith(getSigningKey(secretKey), SignatureAlgorithm.HS256)
                .compact();
    }

//    private void getAuthentication(HttpServletRequest request) {
//        String authorization = request.getHeader("Authorization");
//        try {
//            isTokenExpired(authorization);
//        } catch (MalformedJwtException | ExpiredJwtException e) {
//            e.printStackTrace();
//            request.setAttribute("Unauthorized", HttpStatus.UNAUTHORIZED);
//        }
//    }

}

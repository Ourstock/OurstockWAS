package com.ourstock.api.ourstock_api.jwt;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtToken implements InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(JwtToken.class);

    private final String secret;

    private final long tokenValidityInMilliseconds;

    private Key key;

    public JwtToken(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.token-validity-in-seconds}") long tokenValidityInMilliseconds) {
        this.secret = secret;
        this.tokenValidityInMilliseconds = tokenValidityInMilliseconds;
    }

    public String createToken(String callNumber) {
        long now = (new Date().getTime());
        Date validityTime = new Date(now + this.tokenValidityInMilliseconds);
        return Jwts.builder()
                .setSubject(callNumber)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validityTime)
                .compact();
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }
}

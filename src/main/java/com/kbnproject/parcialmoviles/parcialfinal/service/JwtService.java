package com.kbnproject.parcialmoviles.parcialfinal.service;

import com.kbnproject.parcialmoviles.parcialfinal.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import io.jsonwebtoken.security.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.expiration}")
    private long EXPIRATION_MINUTES;

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public String generateToken(User user, Map<String, Object> stringObjectMap) {
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuedAt.getTime() + (EXPIRATION_MINUTES * 60 * 1000));
        return Jwts.builder()
                .claims(stringObjectMap)
                .subject(user.getEmail())
                .issuedAt(issuedAt)
                .expiration(expiration)
                .signWith(generateKey())
                .compact();
    }

    private SecretKey generateKey() {
        byte[] secretAsBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(secretAsBytes);
    }

    public String extractEmail(String authorizationHeader) {
        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(authorizationHeader)
                .getPayload()
                .getSubject();
/*        return Jwts.parser()
            .setSigningKey(generateKey())
            .build()
            .parseClaimsJws(authorizationHeader)
            .getBody()
            .getSubject();*/
    }
}

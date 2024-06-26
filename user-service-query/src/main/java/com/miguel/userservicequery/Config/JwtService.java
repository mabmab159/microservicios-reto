package com.miguel.userservicequery.Config;

import com.miguel.userservicequery.Model.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${miguel.security.key}")
    private String mitocodeKey;

    public String generateToken(UserDetails userDetails) {
        UserEntity userEntity = (UserEntity) userDetails;
        Map<String, Object> customClaims = new HashMap<>();
        customClaims.put("username", userEntity.getUsername());
        customClaims.put("password", userEntity.getPassword());
        customClaims.put("authorities", userEntity.getAuthorities());
        return Jwts.builder()
                .setIssuedAt(new Date())
                .signWith(getSignKey())
                .setExpiration(new Date(System.currentTimeMillis() + (3600 * 1000L)))
                .addClaims(customClaims)
                .compact();
    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(Base64.getEncoder().encode(mitocodeKey.getBytes()));
    }
}
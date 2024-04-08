package com.miguel.userservice.Configuration;

import com.miguel.userservice.Model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    public String generateToken(UserDetails userDetails) {
        User user = (User) userDetails;
        Map<String, Object> customClaims = new HashMap<>();
        customClaims.put("username", user.getUsername());
        customClaims.put("password", user.getPassword());
        customClaims.put("roles", user.getRoles());
        return Jwts.builder()
                .setSubject("USERNAME")
                .setIssuedAt(new Date())
                .signWith(getSignKey())
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000L))
                .addClaims(customClaims)
                .compact();
    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(Base64.getEncoder().encode("miguelBerrio".getBytes()));
    }
}

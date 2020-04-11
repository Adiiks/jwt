package com.adrian.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class LoginController {

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        long currentTime = System.currentTimeMillis();
        String token = Jwts.builder()
                .setSubject(user.getLogin())
                .claim("roles", "user")
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(currentTime + 20000))
                .signWith(SignatureAlgorithm.HS512, user.getPassword())
                .compact();

        return token;
    }
}

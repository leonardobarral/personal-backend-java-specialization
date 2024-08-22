package com.genesisforhealth.questionarios.usuarios.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.genesisforhealth.questionarios.usuarios.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public Instant gerarDataDeExpiration() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String gerarToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT
                    .create()
                    .withIssuer("users")
                    .withSubject(user.getEmail())
                    .withExpiresAt(gerarDataDeExpiration())
                    .withClaim("authorities", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException erro) {
            throw new RuntimeException("error while generating token", erro);
        }
    }

    public String validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("users")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException erro) {
            System.out.println("Erro no Tokem");
            return "";
        }
    }
}

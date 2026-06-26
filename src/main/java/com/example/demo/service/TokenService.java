/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

import com.example.demo.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;



@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String gerarToken(Usuario user) {
        if ((user.getId() == 0 || user.getId() == null) ||
                user.getNome().isEmpty() ||
                user.getEmail().isEmpty() ||
                user.getSenha().isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Campos nao´preenchidos");
        }
        return Jwts.builder()
                .subject(user.getNome())
                .claim("id", user.getId())
                .claim("nome", user.getNome())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3000000))
                .signWith(getSignKey())
                .compact();
    }

    public Usuario extrairClaim(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        Usuario user = new Usuario();
        user.setId(claims.get("id", Long.class));
        user.setNome(claims.get("nome", String.class));
        return user;
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}

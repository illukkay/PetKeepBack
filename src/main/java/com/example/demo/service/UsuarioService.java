/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Aluno
 */
@Service
public class UsuarioService {
@Autowired

private UsuarioRepository usuarioR;
private TokenService Token;

public String Logar (String email, String senha){
    Usuario user = usuarioR.findByEmail(email).orElseThrow(() ->
     new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email invalido")
    );
    
    if (!senha.equals(user.getSenha())){
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha invalido");
    }
    
    return Token.gerarToken(user);
    
    
}
    
}

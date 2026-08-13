/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

import com.example.demo.model.Prestador;
import com.example.demo.model.Usuario;
import com.example.demo.repository.PrestadorRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrestadorService {

    @Autowired
    private PrestadorRepository prestadorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private TokenService tokenservice;
    
    public Prestador cadastrar(String token, Prestador prestador) {
    Usuario usuario = tokenservice.extrairClaim(token);

    Usuario usuarioBanco = usuarioRepository.findById(usuario.getId())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

    if (usuarioBanco.getTipoUsuario() != Usuario.TipoUsuario.PRESTADOR
            && usuarioBanco.getTipoUsuario() != Usuario.TipoUsuario.AMBOS) {
        throw new RuntimeException("Este usuário não está cadastrado como prestador!");
    }

    if (prestadorRepository.existsByUsuario(usuarioBanco)) {
        throw new RuntimeException("Este usuário já possui um perfil de prestador!");
    }

    prestador.setUsuario(usuarioBanco);

    return prestadorRepository.save(prestador);
}
}
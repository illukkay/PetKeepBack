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

    public Prestador cadastrar(Prestador prestador) {

        Usuario usuario = usuarioRepository
                .findById(prestador.getUsuario().getId())
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado!")
                );

        if (usuario.getTipoUsuario() != Usuario.TipoUsuario.PRESTADOR
                && usuario.getTipoUsuario() != Usuario.TipoUsuario.AMBOS) {

            throw new RuntimeException(
                    "Este usuário não está cadastrado como prestador!"
            );
        }

        if (prestadorRepository.existsByUsuario(usuario)) {
            throw new RuntimeException(
                    "Este usuário já possui um perfil de prestador!"
            );
        }

        prestador.setUsuario(usuario);

        return prestadorRepository.save(prestador);
    }
}
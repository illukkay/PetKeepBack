/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.repository;

import com.example.demo.model.Pet;
import com.example.demo.model.Prestador;
import com.example.demo.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Aluno
 */
public interface PrestadorRepository extends JpaRepository<Prestador, Long> {

    boolean existsByUsuario(Usuario usuario);

    Optional<Prestador> findByUsuarioId(Long id);

}

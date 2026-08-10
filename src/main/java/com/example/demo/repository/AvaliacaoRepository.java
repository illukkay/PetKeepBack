/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.repository;

import com.example.demo.model.Avaliacao;
import com.example.demo.model.Tarefa;
import com.example.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aluno
 */
@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long>{

    @Query("SELECT AVG(a.nota) FROM Avaliacao a WHERE a.avaliado.id = :id")
    Double calcularMedia(@Param("id") Long id);
    
    boolean existsByTarefaAndAvaliador(Tarefa tarefa, Usuario avaliador);
}

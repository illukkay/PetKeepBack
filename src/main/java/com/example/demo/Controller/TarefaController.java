/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Controller;

import com.example.demo.model.Tarefa;
import com.example.demo.service.TarefaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aluno
 */
@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService service;

    @PostMapping("/cadastro")
    public Tarefa cadastrar(@RequestBody Tarefa tarefa){
        return service.cadastrar(tarefa);
    }

    @GetMapping("/disponiveis/{usuarioId}")
    public List<Tarefa> listar(@PathVariable Long usuarioId){
        return service.listarDisponiveis(usuarioId);
    }

    @PutMapping("/aceitar/{tarefaId}/{usuarioId}")
    public Tarefa aceitar(@PathVariable Long tarefaId,
                          @PathVariable Long usuarioId){

        return service.aceitar(tarefaId, usuarioId);
    }

}

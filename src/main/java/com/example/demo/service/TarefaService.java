/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

import com.example.demo.model.Pet;
import com.example.demo.model.Prestador;
import com.example.demo.model.Tarefa;
import com.example.demo.model.Usuario;
import com.example.demo.repository.PetRepository;
import com.example.demo.repository.PrestadorRepository;
import com.example.demo.repository.TarefaRepository;
import com.example.demo.repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aluno
 */
@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PrestadorRepository prestadorRepository;

    public Tarefa cadastrar(Tarefa tarefa){

        Usuario tutor = usuarioRepository.findById(
                tarefa.getTutor().getId())
                .orElseThrow(() ->
                new RuntimeException("Tutor não encontrado"));

        Pet pet = petRepository.findById(
                tarefa.getPet().getId())
                .orElseThrow(() ->
                new RuntimeException("Pet não encontrado"));

        if(!pet.getTutor().getId().equals(tutor.getId())){
            throw new RuntimeException("Este pet não pertence ao tutor.");
        }

        tarefa.setTutor(tutor);
        tarefa.setPet(pet);
        tarefa.setPrestador(null);
        tarefa.setStatus(Tarefa.Status.ABERTA);

        return tarefaRepository.save(tarefa);
    }
     public List<Tarefa> listarDisponiveis(Long usuarioId){

        Prestador prestador = prestadorRepository
                .findByUsuarioId(usuarioId)
                .orElseThrow(() ->
                new RuntimeException("Prestador não encontrado."));

        List<Tarefa> tarefas =
                tarefaRepository.findByStatus(Tarefa.Status.ABERTA);

        tarefas.removeIf(t -> {

            boolean aceitaPorte;

            switch (t.getPet().getPorte()) {

                case PEQUENO:
                    aceitaPorte = prestador.getAceitaPequeno();
                    break;

                case MEDIO:
                    aceitaPorte = prestador.getAceitaMedio();
                    break;

                case GRANDE:
                    aceitaPorte = prestador.getAceitaGrande();
                    break;

                default:
                    aceitaPorte = prestador.getAceitaGigante();
            }

            boolean aceitaServico;

            switch (t.getTipoServico()) {

                case PASSEIO:
                    aceitaServico = prestador.getAceitaPasseio();
                    break;

                case BANHO:
                    aceitaServico = prestador.getAceitaBanho();
                    break;

                case HOSPEDAGEM:
                    aceitaServico = prestador.getAceitaHospedagem();
                    break;

                default:
                    aceitaServico = true;
            }

            return !(aceitaPorte && aceitaServico);

        });

        return tarefas;

    }
     public Tarefa aceitar(Long tarefaId, Long usuarioId){

        Tarefa tarefa = tarefaRepository.findById(tarefaId)
                .orElseThrow(() ->
                new RuntimeException("Tarefa não encontrada"));

        if(tarefa.getStatus()!=Tarefa.Status.ABERTA){
            throw new RuntimeException("Esta tarefa já foi aceita.");
        }

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() ->
                new RuntimeException("Usuário não encontrado"));

        tarefa.setPrestador(usuario);
        tarefa.setStatus(Tarefa.Status.EM_ANDAMENTO);

        return tarefaRepository.save(tarefa);

    }

}

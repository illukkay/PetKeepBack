/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

import com.example.demo.model.Pet;
import com.example.demo.model.Prestador;
import com.example.demo.model.Tarefa;
import com.example.demo.model.TarefaMatch;
import com.example.demo.model.Usuario;
import com.example.demo.repository.PetRepository;
import com.example.demo.repository.PrestadorRepository;
import com.example.demo.repository.TarefaRepository;
import com.example.demo.repository.UsuarioRepository;
import java.util.ArrayList;
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

    public Tarefa cadastrar(Tarefa tarefa) {

        Usuario tutor = usuarioRepository.findById(tarefa.getTutor().getId()).orElseThrow(() -> new RuntimeException("Tutor não encontrado"));
        Pet pet = petRepository.findById(tarefa.getPet().getId()).orElseThrow(() -> new RuntimeException("Pet não encontrado"));

        if (!pet.getTutor().getId().equals(tutor.getId())) {
            throw new RuntimeException("Este pet não pertence ao tutor.");
        }

        tarefa.setTutor(tutor);
        tarefa.setPet(pet);
        tarefa.setPrestador(null);
        tarefa.setStatus(Tarefa.Status.ABERTA);
        return tarefaRepository.save(tarefa);
    }

    public List<TarefaMatch> listarDisponiveis(Long usuarioId) {
        Prestador prestador = prestadorRepository.findByUsuarioId(usuarioId).orElseThrow(() -> new RuntimeException("Prestador não encontrado."));
        Usuario usuarioPrestador = prestador.getUsuario();

        List<Tarefa> tarefas = tarefaRepository.findByStatus(Tarefa.Status.ABERTA);
        List<TarefaMatch> resultados = new ArrayList<>();

        for (Tarefa tarefa : tarefas) {
            boolean aceitaPorte;
            switch (tarefa.getPet().getPorte()) {

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
                    break;
            }
            if (!aceitaPorte) {
                continue;
            }

            boolean aceitaServico;

            switch (tarefa.getTipoServico()) {

                case PASSEIO:
                    aceitaServico = prestador.getAceitaPasseio();
                    break;

                case BANHO:
                    aceitaServico = prestador.getAceitaBanho();
                    break;

                case HOSPEDAGEM:
                    aceitaServico = prestador.getAceitaHospedagem();
                    break;

                case CUIDADO_DOMICILIAR:
                    aceitaServico = true;
                    break;

                default:
                    aceitaServico = false;
            }
            if (!aceitaServico) {
                continue;
            }

            double proximidade = calcularProximidade(usuarioPrestador, tarefa.getTutor());
            double reputacao = calcularReputacao(tarefa.getTutor());
            double espaco = calcularCompatibilidadeEspaco(usuarioPrestador, tarefa.getTutor());
            double score = (proximidade * 0.40) + (reputacao * 0.30) + (espaco * 0.30);
            resultados.add(new TarefaMatch(tarefa, score));
        }

        return resultados;
    }

    private double calcularProximidade(Usuario prestador, Usuario tutor) {

        if (prestador.getCidade() != null
                && tutor.getCidade() != null
                && prestador.getCidade().equalsIgnoreCase(tutor.getCidade())) {
            return 10.0;
        }

        if (prestador.getEstado() != null
                && tutor.getEstado() != null
                && prestador.getEstado().equalsIgnoreCase(tutor.getEstado())) {
            return 5.0;
        }
        return 0.0;
    }

    private double calcularReputacao(Usuario tutor) {

        if (tutor.getReputacaoMedia() == null) {
            return 10.0;
        }
        return tutor.getReputacaoMedia().doubleValue() * 2;
    }

    private double calcularCompatibilidadeEspaco(
            Usuario prestador,
            Usuario tutor
    ) {

        if (prestador.getTipoResidencia()
                == tutor.getTipoResidencia()) {
            return 10.0;
        }
        return 5.0;
    }

    public Tarefa aceitar(Long tarefaId, Long usuarioId) {

        Tarefa tarefa = tarefaRepository.findById(tarefaId).orElseThrow(()
                -> new RuntimeException("Tarefa não encontrada"));

        if (tarefa.getStatus() != Tarefa.Status.ABERTA) {
            throw new RuntimeException("Esta tarefa já foi aceita.");
        }

        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(()
                -> new RuntimeException("Usuário não encontrado"));
        tarefa.setPrestador(usuario);
        tarefa.setStatus(Tarefa.Status.EM_ANDAMENTO);
        return tarefaRepository.save(tarefa);
    }

    public Tarefa concluir(Long tarefaId, Long prestadorId) {

        Tarefa tarefa = tarefaRepository.findById(tarefaId).orElseThrow(()
                -> new RuntimeException("Tarefa não encontrada"));
        if (tarefa.getPrestador() == null) {
            throw new RuntimeException("Essa tarefa ainda não possui um prestador");
        }

        if (!tarefa.getPrestador().getId().equals(prestadorId)) {
            throw new RuntimeException("Você não é o prestador dessa tarefa");
        }

        if (tarefa.getStatus()
                != Tarefa.Status.EM_ANDAMENTO) {
            throw new RuntimeException("A tarefa precisa estar em andamento para ser concluída");
        }
        tarefa.setStatus(Tarefa.Status.CONCLUIDA);
        return tarefaRepository.save(tarefa);
    }
}

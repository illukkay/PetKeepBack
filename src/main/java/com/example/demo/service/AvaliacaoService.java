/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

import com.example.demo.model.Avaliacao;
import com.example.demo.model.Tarefa;
import com.example.demo.model.Usuario;
import com.example.demo.repository.AvaliacaoRepository;
import com.example.demo.repository.TarefaRepository;
import com.example.demo.repository.UsuarioRepository;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    public Avaliacao cadastrar(Avaliacao avaliacao) {

        Tarefa tarefa = tarefaRepository.findById(avaliacao.getTarefa().getId())
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Tarefa não encontrada."));

        Usuario avaliador = usuarioRepository.findById(avaliacao.getAvaliador().getId())
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Avaliador não encontrado."));

        Usuario avaliado = usuarioRepository.findById(avaliacao.getAvaliado().getId())
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Usuário avaliado não encontrado."));

        if (avaliacao.getNota() < 1 || avaliacao.getNota() > 5) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "A nota deve estar entre 1 e 5.");
        }

        avaliacao.setTarefa(tarefa);
        avaliacao.setAvaliador(avaliador);
        avaliacao.setAvaliado(avaliado);

        Avaliacao nova = avaliacaoRepository.save(avaliacao);

        Double media = avaliacaoRepository.calcularMedia(avaliado.getId());

        avaliado.setReputacaoMedia(BigDecimal.valueOf(media));

        usuarioRepository.save(avaliado);

        return nova;
    }

}

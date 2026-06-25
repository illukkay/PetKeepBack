/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.sql.Timestamp;

@Entity
@Table(name = "tarefas")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tutor_id", nullable = false)
    private Usuario tutor;

    @ManyToOne
    @JoinColumn(name = "prestador_id") 
    private Usuario prestador; 

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet; 

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_servico", nullable = false)
    private TipoServico tipoServico;

    @Column(name = "data_agendamento", nullable = false)
    private LocalDateTime dataAgendamento;

    @Column(name = "preco_ofertado", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoOfertado;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ABERTA;

    @Column(name = "descricao_detalhes", columnDefinition = "TEXT")
    private String descricaoDetalhes;

    @Column(name = "data_criacao", insertable = false, updatable = false)
    private Timestamp dataCriacao;

    public enum TipoServico { PASSEIO, BANHO, HOSPEDAGEM, CUIDADO_DOMICILIAR }
    public enum Status { ABERTA, EM_ANDAMENTO, CONCLUIDA, CANCELADA }

    public Tarefa() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getTutor() {
        return tutor;
    }

    public void setTutor(Usuario tutor) {
        this.tutor = tutor;
    }

    public Usuario getPrestador() {
        return prestador;
    }

    public void setPrestador(Usuario prestador) {
        this.prestador = prestador;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public TipoServico getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(TipoServico tipoServico) {
        this.tipoServico = tipoServico;
    }

    public LocalDateTime getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDateTime dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public BigDecimal getPrecoOfertado() {
        return precoOfertado;
    }

    public void setPrecoOfertado(BigDecimal precoOfertado) {
        this.precoOfertado = precoOfertado;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescricaoDetalhes() {
        return descricaoDetalhes;
    }

    public void setDescricaoDetalhes(String descricaoDetalhes) {
        this.descricaoDetalhes = descricaoDetalhes;
    }

    public Timestamp getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Timestamp dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    
    
}

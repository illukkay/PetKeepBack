
package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "prestadores")
public class Prestador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;

    @Column(nullable = false)
    private Boolean aceitaHospedagem;

    @Column(nullable = false)
    private Boolean aceitaPasseio;

    @Column(nullable = false)
    private Boolean aceitaBanho;

    @Column(nullable = false)
    private Boolean aceitaPequeno;

    @Column(nullable = false)
    private Boolean aceitaMedio;

    @Column(nullable = false)
    private Boolean aceitaGrande;

    @Column(nullable = false)
    private Boolean aceitaGigante;

    @Column(length = 500)
    private String descricao;

    @Column(nullable = false)
    private Double valorHora;

    public Prestador() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Boolean getAceitaHospedagem() {
        return aceitaHospedagem;
    }

    public void setAceitaHospedagem(Boolean aceitaHospedagem) {
        this.aceitaHospedagem = aceitaHospedagem;
    }

    public Boolean getAceitaPasseio() {
        return aceitaPasseio;
    }

    public void setAceitaPasseio(Boolean aceitaPasseio) {
        this.aceitaPasseio = aceitaPasseio;
    }

    public Boolean getAceitaBanho() {
        return aceitaBanho;
    }

    public void setAceitaBanho(Boolean aceitaBanho) {
        this.aceitaBanho = aceitaBanho;
    }

    public Boolean getAceitaPequeno() {
        return aceitaPequeno;
    }

    public void setAceitaPequeno(Boolean aceitaPequeno) {
        this.aceitaPequeno = aceitaPequeno;
    }

    public Boolean getAceitaMedio() {
        return aceitaMedio;
    }

    public void setAceitaMedio(Boolean aceitaMedio) {
        this.aceitaMedio = aceitaMedio;
    }

    public Boolean getAceitaGrande() {
        return aceitaGrande;
    }

    public void setAceitaGrande(Boolean aceitaGrande) {
        this.aceitaGrande = aceitaGrande;
    }

    public Boolean getAceitaGigante() {
        return aceitaGigante;
    }

    public void setAceitaGigante(Boolean aceitaGigante) {
        this.aceitaGigante = aceitaGigante;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValorHora() {
        return valorHora;
    }

    public void setValorHora(Double valorHora) {
        this.valorHora = valorHora;
    }

    
}
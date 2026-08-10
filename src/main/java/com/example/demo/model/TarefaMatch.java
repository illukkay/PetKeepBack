/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.model;

public class TarefaMatch {

    private Tarefa tarefa;
    private Double score;

    public TarefaMatch() {
    }

    public TarefaMatch(Tarefa tarefa, Double score) {
        this.tarefa = tarefa;
        this.score = score;
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
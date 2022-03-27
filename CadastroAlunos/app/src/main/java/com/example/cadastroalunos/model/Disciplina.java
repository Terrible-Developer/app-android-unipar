package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

import java.util.Objects;

public class Disciplina extends SugarRecord {
    private String nome;
    private int totalAulas;

    public Disciplina() {
    }

    public Disciplina(String nome, int totalAulas) {
        this.nome = nome;
        this.totalAulas = totalAulas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disciplina that = (Disciplina) o;
        return totalAulas == that.totalAulas && Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, totalAulas);
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "nome='" + nome + '\'' +
                ", totalAulas=" + totalAulas +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTotalAulas() {
        return totalAulas;
    }

    public void setTotalAulas(int totalAulas) {
        this.totalAulas = totalAulas;
    }
}

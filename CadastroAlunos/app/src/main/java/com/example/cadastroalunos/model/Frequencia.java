package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

import java.util.Objects;

public class Frequencia extends SugarRecord {
    private String disciplina;
    private long idAluno;
    private long idTurma;
    private String listaFrequencia;

    public Frequencia() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frequencia that = (Frequencia) o;
        return idAluno == that.idAluno && idTurma == that.idTurma && Objects.equals(disciplina, that.disciplina) && Objects.equals(listaFrequencia, that.listaFrequencia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(disciplina, idAluno, idTurma, listaFrequencia);
    }

    @Override
    public String toString() {
        return "Frequencia{" +
                "disciplina='" + disciplina + '\'' +
                ", idAluno=" + idAluno +
                ", idTurma=" + idTurma +
                ", listaFrequencia='" + listaFrequencia + '\'' +
                '}';
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(long idAluno) {
        this.idAluno = idAluno;
    }

    public long getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(long idTurma) {
        this.idTurma = idTurma;
    }

    public String getListaFrequencia() {
        return listaFrequencia;
    }

    public void setListaFrequencia(String listaFrequencia) {
        this.listaFrequencia = listaFrequencia;
    }
}

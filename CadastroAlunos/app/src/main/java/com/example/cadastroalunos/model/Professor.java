package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

import java.util.Objects;

public class Professor extends SugarRecord {
    private static long idProfessor;
    private String nome;
    private String cpf;
    private String dtNasc;
    private String curso;
    private Turma turma;

    public Professor() {
    }

    public Professor(String nome, String cpf, String dtNasc, String curso, Turma turma) {
        this.nome = nome;
        this.cpf = cpf;
        this.dtNasc = dtNasc;
        this.curso = curso;
        this.turma = turma;
    }

    public static long getIdProfessor() {
        return idProfessor;
    }

    public static void setIdProfessor(long idProfessor) {
        Professor.idProfessor = idProfessor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(String dtNasc) {
        this.dtNasc = dtNasc;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return Objects.equals(nome, professor.nome) && Objects.equals(cpf, professor.cpf) && Objects.equals(dtNasc, professor.dtNasc) && Objects.equals(curso, professor.curso) && Objects.equals(turma, professor.turma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cpf, dtNasc, curso, turma);
    }

    @Override
    public String toString() {
        return "Professor{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dtNasc='" + dtNasc + '\'' +
                ", curso='" + curso + '\'' +
                ", turma=" + turma +
                '}';
    }
}

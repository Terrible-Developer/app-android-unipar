package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

import java.util.Objects;

public class Aluno extends SugarRecord {

    private int ra;
    private String nome;
    private String cpf;
    private String dtNasc;
    private String dtMatricula;
    private String curso;
    private String periodo;
    private String regime;
    private Turma turma;

    public Aluno() {
    }

    public Aluno(int ra, String nome, String cpf, String dtNasc, String dtMatricula, String curso, String periodo, String regime, Turma turma) {
        this.ra = ra;
        this.nome = nome;
        this.cpf = cpf;
        this.dtNasc = dtNasc;
        this.dtMatricula = dtMatricula;
        this.curso = curso;
        this.periodo = periodo;
        this.regime = regime;
        this.turma = turma;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "ra=" + ra +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dtNasc='" + dtNasc + '\'' +
                ", dtMatricula='" + dtMatricula + '\'' +
                ", curso='" + curso + '\'' +
                ", periodo='" + periodo + '\'' +
                ", regime='" + regime + '\'' +
                ", turma=" + turma +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return ra == aluno.ra && Objects.equals(nome, aluno.nome) && Objects.equals(cpf, aluno.cpf) && Objects.equals(dtNasc, aluno.dtNasc) && Objects.equals(dtMatricula, aluno.dtMatricula) && Objects.equals(curso, aluno.curso) && Objects.equals(periodo, aluno.periodo) && Objects.equals(regime, aluno.regime) && Objects.equals(turma, aluno.turma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ra, nome, cpf, dtNasc, dtMatricula, curso, periodo, regime, turma);
    }

    public int getRa() {
        return ra;
    }

    public void setRa(int ra) {
        this.ra = ra;
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

    public String getDtMatricula() {
        return dtMatricula;
    }

    public void setDtMatricula(String dtMatricula) {
        this.dtMatricula = dtMatricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }
}

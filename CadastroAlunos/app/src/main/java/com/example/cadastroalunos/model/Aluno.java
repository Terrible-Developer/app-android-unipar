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
    private Long idTurma;
    //private String regime;

    public Aluno() {
    }

    public Aluno(int ra, String nome, String cpf, String dtNasc, String dtMatricula, String curso, String periodo, Long idTurma/*,String regime,*/) {
        this.ra = ra;
        this.nome = nome;
        this.cpf = cpf;
        this.dtNasc = dtNasc;
        this.dtMatricula = dtMatricula;
        this.curso = curso;
        this.periodo = periodo;
        this.idTurma = idTurma;
        //this.regime = regime;
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
                ", idTurma='" + idTurma + '\'' +
                //", regime='" + regime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return ra == aluno.ra && Objects.equals(nome, aluno.nome) && Objects.equals(cpf, aluno.cpf) && Objects.equals(dtNasc, aluno.dtNasc) && Objects.equals(dtMatricula, aluno.dtMatricula) && Objects.equals(curso, aluno.curso) && Objects.equals(periodo, aluno.periodo) && Objects.equals(idTurma, aluno.idTurma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ra, nome, cpf, dtNasc, dtMatricula, curso, periodo, idTurma /*,regime,*/ );
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

    public Long getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Long idTurma) {
        this.idTurma = idTurma;
    }

    /*public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }*/
}

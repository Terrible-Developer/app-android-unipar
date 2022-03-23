package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

import java.util.Objects;

public class Professor extends SugarRecord {
    private static long idProfessor;
    private String nome;
    private String cpf;
    private String dtNasc;
    private String periodo;
    private String curso;
    //private String regime;
    private Turma turma;
    String disciplina;

    public Professor() {
    }

    public Professor(String nome, String cpf, String dtNasc, String periodo, String curso, /*String regime,*/ Turma turma, String disciplina) {
        this.nome = nome;
        this.cpf = cpf;
        this.dtNasc = dtNasc;
        this.periodo = periodo;
        this.curso = curso;
        //this.regime = regime;
        this.turma = turma;
        this.disciplina = disciplina;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dtNasc='" + dtNasc + '\'' +
                ", periodo='" + periodo + '\'' +
                ", curso='" + curso + '\'' +
                //", regime=" + regime +
                ", turma=" + turma +
                ", disciplina=" + disciplina +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return Objects.equals(nome, professor.nome) && Objects.equals(cpf, professor.cpf) && Objects.equals(dtNasc, professor.dtNasc) && Objects.equals(periodo, professor.periodo) && Objects.equals(curso, professor.curso) /*&& Objects.equals(regime, professor.regime)*/ && Objects.equals(turma, professor.turma) && Objects.equals(disciplina, professor.disciplina);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cpf, dtNasc, periodo, curso, /*regime,*/ turma, disciplina);
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

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    /*public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }*/

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }
}

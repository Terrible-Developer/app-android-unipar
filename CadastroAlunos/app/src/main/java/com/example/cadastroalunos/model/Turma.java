package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

import java.util.List;
import java.util.Objects;

public class Turma extends SugarRecord {
    private int idTurma;
    private String apelido;
    private String regime;
    private String periodo;
    private List<Aluno> alunos;
    private Professor professor;

    public Turma() {}

    public Turma(String apelido, String regime, String periodo, List<Aluno> alunos, Professor professor) {
        this.apelido = apelido;
        this.regime = regime;
        this.periodo = periodo;
        this.alunos = alunos;
        this.professor = professor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turma turma = (Turma) o;
        return idTurma == turma.idTurma && Objects.equals(apelido, turma.apelido) && Objects.equals(regime, turma.regime) && Objects.equals(periodo, turma.periodo) && Objects.equals(alunos, turma.alunos) && Objects.equals(professor, turma.professor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTurma, apelido, regime, periodo, alunos, professor);
    }

    @Override
    public String toString() {
        return "Turma{" +
                "idTurma=" + idTurma +
                ", apelido='" + apelido + '\'' +
                ", regime='" + regime + '\'' +
                ", periodo='" + periodo + '\'' +
                ", alunos=" + alunos +
                ", professor=" + professor +
                '}';
    }

    public int getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}

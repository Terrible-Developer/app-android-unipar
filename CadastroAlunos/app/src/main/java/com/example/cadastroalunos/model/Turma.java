package com.example.cadastroalunos.model;

import com.example.cadastroalunos.enums.Regime;
import com.orm.SugarRecord;

import java.util.List;
import java.util.Objects;

public class Turma extends SugarRecord {
    private int idTurma;
    private String apelido;
    private Regime regime;
    private List<Aluno> alunos;
    private Professor professor;

    public Turma() {}

    public Turma(Regime regime, List<Aluno> alunos, Professor professor) {
        this.apelido = "Turma #"+idTurma;
        this.regime = regime;
        this.alunos = alunos;
        this.professor = professor;
    }

    public long getIdTurma() {
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

    public Regime getRegime() {
        return regime;
    }

    public void setRegime(Regime regime) {
        this.regime = regime;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turma turma = (Turma) o;
        return Objects.equals(apelido, turma.apelido) && regime == turma.regime && Objects.equals(alunos, turma.alunos) && Objects.equals(professor, turma.professor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apelido, regime, alunos, professor);
    }

    @Override
    public String toString() {
        return "Turma{" +
                "idTurma=" + idTurma +
                ", apelido='" + apelido + '\'' +
                ", regime=" + regime +
                ", alunos=" + alunos +
                ", professor=" + professor +
                '}';
    }
}

package com.example.cadastroalunos.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.List;
import java.util.Objects;

public class Turma extends SugarRecord {
    private int idTurma;
    private String apelido;
    private String regime;
    private String periodo;
    private String curso;
    private /*List<Aluno>*/String alunos;
    private Professor professor;

    public Turma() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turma turma = (Turma) o;
        return idTurma == turma.idTurma && Objects.equals(apelido, turma.apelido) && Objects.equals(regime, turma.regime) && Objects.equals(periodo, turma.periodo) && Objects.equals(curso, turma.curso) && Objects.equals(alunos, turma.alunos) && Objects.equals(professor, turma.professor);
    }

    @Override
    public String toString() {
        return "Turma{" +
                "idTurma=" + idTurma +
                ", apelido='" + apelido + '\'' +
                ", regime='" + regime + '\'' +
                ", periodo='" + periodo + '\'' +
                ", curso='" + curso + '\'' +
                ", alunos=" + alunos +
                ", professor=" + professor +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTurma, apelido, regime, periodo, curso, alunos, professor);
    }

    public Turma(String regime, String periodo, String curso, /*List<Aluno>*/String alunos, Professor professor) {
        this.apelido = "Turma #"+idTurma;
        this.regime = regime;
        this.periodo = periodo;
        this.curso = curso;
        this.alunos = alunos;
        this.professor = professor;
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

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    /*public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }*/

    public String getAlunos() {
        return alunos;
    }

    public void setAlunos(String alunos) {
        this.alunos = alunos;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}

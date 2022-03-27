package com.example.cadastroalunos.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.List;
import java.util.Objects;

public class Turma extends SugarRecord {
    private int idTurma;
    private String regime;
    private String periodo;
    private String curso;
    private /*List<Aluno>*/String alunos;
    private Long idProfessor;

    public Turma() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turma turma = (Turma) o;
        return Objects.equals(regime, turma.regime) && Objects.equals(periodo, turma.periodo) && Objects.equals(curso, turma.curso) && Objects.equals(alunos, turma.alunos) && Objects.equals(idProfessor, turma.idProfessor);
    }

    @Override
    public String toString() {
        return "Turma{" +
                ", regime='" + regime + '\'' +
                ", periodo='" + periodo + '\'' +
                ", curso='" + curso + '\'' +
                ", alunos=" + alunos +
                ", idProfessor=" + idProfessor +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(regime, periodo, curso, alunos, idProfessor);
    }

    public Turma(String regime, String periodo, String curso, /*List<Aluno>*/String alunos, Long idProfessor) {
        this.regime = regime;
        this.periodo = periodo;
        this.curso = curso;
        this.alunos = alunos;
        this.idProfessor = idProfessor;
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

    public Long getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(Long idProfessor) {
        this.idProfessor = idProfessor;
    }
}

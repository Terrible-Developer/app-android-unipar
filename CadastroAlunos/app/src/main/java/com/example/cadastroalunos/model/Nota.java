package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

import java.util.Objects;

public class Nota extends SugarRecord {
    private int notaID;
    private long idAluno;
    private long idTurma;
    private String notas;

    public Nota(int notaID, long idAluno, long idTurma, String notas) {
        this.notaID = notaID;
        this.idAluno = idAluno;
        this.idTurma = idTurma;
        this.notas = notas;
    }

    public Nota() {
    }

    public int getNotaID() {
        return notaID;
    }

    public void setNotaID(int notaID) {
        this.notaID = notaID;
    }

    public long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(long idAluno) {
        this.idAluno = idAluno;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public long getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(long idTurma) {
        this.idTurma = idTurma;
    }

    @Override
    public String toString() {
        return "Nota{" +
                "notaID=" + notaID +
                ", idAluno=" + idAluno +
                ", idTurma=" + idTurma +
                ", notas='" + notas + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nota nota = (Nota) o;
        return notaID == nota.notaID && idAluno == nota.idAluno && Objects.equals(notas, nota.notas) && Objects.equals(idTurma, nota.idTurma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notaID, idAluno, idTurma, notas);
    }
}

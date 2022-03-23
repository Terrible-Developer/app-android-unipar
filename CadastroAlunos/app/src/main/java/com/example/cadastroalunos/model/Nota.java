package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

import java.util.Objects;

public class Nota extends SugarRecord {
    private int notaID;
    private long idAluno;
    private String notas;

    public Nota(int notaID, long idAluno, String notas) {
        this.notaID = notaID;
        this.idAluno = idAluno;
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

    @Override
    public String toString() {
        return "Nota{" +
                "notaID=" + notaID +
                ", idAluno=" + idAluno +
                ", notas='" + notas + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nota nota = (Nota) o;
        return notaID == nota.notaID && idAluno == nota.idAluno && Objects.equals(notas, nota.notas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notaID, idAluno, notas);
    }
}

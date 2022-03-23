package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

import java.util.Objects;

public class Nota extends SugarRecord {
    private int notaID;
    private Long alunoID;
    private String notas;

    public Nota() {
    }

    public Nota(Long alunoID, String notas) {
        this.alunoID = alunoID;
        this.notas = notas;
    }

    public int getNotaID() {
        return notaID;
    }

    public void setNotaID(int notaID) {
        this.notaID = notaID;
    }

    public Long getAlunoID() {
        return alunoID;
    }

    public void setAlunoID(Long alunoID) {
        this.alunoID = alunoID;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nota nota = (Nota) o;
        return notaID == nota.notaID && Objects.equals(alunoID, nota.alunoID) && Objects.equals(notas, nota.notas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notaID, alunoID, notas);
    }

    @Override
    public String toString() {
        return "Nota{" +
                "notaID=" + notaID +
                ", alunoID=" + alunoID +
                ", notas='" + notas + '\'' +
                '}';
    }
}

package com.example.cadastroalunos.enums;

public enum Cursos {
    SISTEMAS("Análise e Desenv. Sistemas"),
        ADMINISTRACAO("Administração"),
            CIENCIAS_CONTABEIS("Ciências Contábeis"),
                DIREITO("Direito"),
                    FARMACIA("Farmácia"),
                        NUTRICAO("Nutrição");

    private String cursoAlias;

    Cursos(String cursoAlias) {
        this.cursoAlias = cursoAlias;
    }

    public String getCursoAlias() {
        return cursoAlias;
    }

    public void setCursoAlias(String cursoAlias) {
        this.cursoAlias = cursoAlias;
    }
}

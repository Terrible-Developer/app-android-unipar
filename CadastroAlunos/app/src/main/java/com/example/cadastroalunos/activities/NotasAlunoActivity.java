package com.example.cadastroalunos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.dao.DisciplinaDAO;
import com.example.cadastroalunos.dao.NotaDAO;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.Disciplina;
import com.example.cadastroalunos.model.Nota;
import com.example.cadastroalunos.util.Util;

import java.util.ArrayList;
import java.util.List;

public class NotasAlunoActivity extends AppCompatActivity {
    private Aluno aluno;

    private TableLayout tlNotasAluno;

    private List<Nota> notas;

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno_notas);

        Intent intent = getIntent();
        String alunoID = intent.getStringExtra("alunoID");
        Aluno alunoObj = AlunoDAO.getAluno(Integer.parseInt(alunoID));

        notas = NotaDAO.retornaNotas("ID_ALUNO = ?", new String[] {alunoID}, "");

        tlNotasAluno = findViewById(R.id.tlNotasAluno);

        setAluno(alunoObj);

        // Seta o conteúdo da víew
        setTitle(aluno.getNome());

        showEverything();
    }

    private void showEverything() {

        notas.forEach((nota-> {
            String idDisciplina = Util.splitString(nota.getNotas(), 0, " - ");
            Disciplina disc = DisciplinaDAO.getDisciplina(Integer.parseInt(idDisciplina));

            TableRow tableRow = new TableRow(this);
            LinearLayout.LayoutParams nomeTableRowParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tableRow.setLayoutParams(nomeTableRowParams);

            // Cria nome aluno
            LinearLayout coluna = new LinearLayout(this);
            TableRow.LayoutParams colunaParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            colunaParams.setMargins(5, 5, 5, 5);
            coluna.setLayoutParams(colunaParams);

            TextView nomeAluno = new TextView(this);
            nomeAluno.setText(disc.getNome());

            coluna.addView(nomeAluno);
            tableRow.addView(coluna);

            // Cria nota aluno
            LinearLayout colunaNota = new LinearLayout(this);
            TableRow.LayoutParams colunaNotaParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            colunaNotaParams.setMargins(5, 5, 5, 5);
            colunaNota.setLayoutParams(colunaNotaParams);

            TextView notaAluno = new TextView(this);
            List<Nota> notas = NotaDAO.retornaNotas("ID_ALUNO = ?", new String[]{""+aluno.getId()}, "");
            notaAluno.setText(disc.getNome());

            coluna.addView(nomeAluno);
            tableRow.addView(colunaNota);

            tlNotasAluno.addView(tableRow);
        }));

    }
}

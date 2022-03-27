package com.example.cadastroalunos.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.dao.DisciplinaDAO;
import com.example.cadastroalunos.dao.FrequenciaDAO;
import com.example.cadastroalunos.dao.NotaDAO;
import com.example.cadastroalunos.dao.ProfessorDAO;
import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.Disciplina;
import com.example.cadastroalunos.model.Frequencia;
import com.example.cadastroalunos.model.Nota;
import com.example.cadastroalunos.model.Professor;
import com.example.cadastroalunos.model.Turma;
import com.example.cadastroalunos.util.Util;

import java.util.ArrayList;
import java.util.List;

public class SituacaoAlunoActivity extends AppCompatActivity {
    private Aluno aluno;

    private TextView tvFrequenciaAluno;
    private TextView tvNotasAluno;

    private TextView tvNotasVazia;
    private TextView tvFrequenciaVazia;

    private TableLayout tlNotasAluno;

    private TableLayout tlFrequenciaAluno;

    private List<Nota> notas;
    private List<Frequencia> frequencias;

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
        String turmaID = intent.getStringExtra("turmaID");
        String disciplina = intent.getStringExtra("disciplina");

        Aluno alunoObj = AlunoDAO.getAluno(Integer.parseInt(alunoID));

        notas = NotaDAO.retornaNotas("ID_ALUNO = ? and ID_TURMA = ?", new String[]{alunoID, turmaID}, "");
        frequencias = FrequenciaDAO.retornaFrequencias("ID_ALUNO = ? and ID_TURMA = ? and DISCIPLINA = ?", new String[]{alunoID, turmaID, disciplina}, "");

        tvNotasVazia = findViewById(R.id.tvNotasVazia);
        tvFrequenciaVazia = findViewById(R.id.tvFrequenciaVazia);

        tlNotasAluno = findViewById(R.id.tlNotasAluno);
        tlFrequenciaAluno = findViewById(R.id.tlFrequenciaAluno);

        tvNotasAluno = findViewById(R.id.tvNotasAluno);
        tvFrequenciaAluno = findViewById(R.id.tvFrequenciaAluno);

        setAluno(alunoObj);

        // Seta o conteúdo da víew
        setTitle("Situação de " + aluno.getNome());

        showEverything();
    }

    @SuppressLint("NewApi")
    private void showEverything() {
        // Adiciona as notas
        if (!notas.isEmpty()) {
            tvNotasAluno.setVisibility(View.VISIBLE);
            tvNotasVazia.setVisibility(View.GONE);
            notas.forEach(nota -> {
                String idDisciplina = Util.splitString(nota.getNotas(), 0, " - ");
                Disciplina disc = DisciplinaDAO.getDisciplina(Integer.parseInt(idDisciplina));

                // Cria uma linha de tabela
                TableRow tableRow = new TableRow(this);
                LinearLayout.LayoutParams nomeTableRowParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                tableRow.setLayoutParams(nomeTableRowParams);

                // Cria nome da disciplina
                LinearLayout coluna = new LinearLayout(this);
                TableRow.LayoutParams colunaParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                colunaParams.setMargins(5, 5, 5, 5);
                colunaParams.weight = 5;
                coluna.setLayoutParams(colunaParams);

                TextView nomeDisciplina = new TextView(this);
                nomeDisciplina.setText(disc.getNome());

                coluna.addView(nomeDisciplina);
                tableRow.addView(coluna);

                // Cria nota aluno
                LinearLayout colunaNota = new LinearLayout(this);
                TableRow.LayoutParams colunaNotaParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                colunaNotaParams.setMargins(10, 5, 10, 5);
                colunaNotaParams.weight = 1;
                colunaNota.setLayoutParams(colunaNotaParams);

                TextView notaAluno = new TextView(this);
                notaAluno.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                String[] idsNotas = nota.getNotas().split(",");

                // Loop nas notas
                for (String nt : idsNotas) {
                    String disciplinaNotaId = Util.splitString(nt, 0, " - ");
                    String notaDoAluno = Util.splitString(nt, 1, " - ");

                    if (disciplinaNotaId.equals(idDisciplina)) {
                        notaAluno.setText(notaDoAluno);
                        colunaNota.addView(notaAluno);
                        tableRow.addView(colunaNota);

                        // Criar coluna avisando se passou ou não
                        LinearLayout colunaEstado = new LinearLayout(this);
                        TableRow.LayoutParams colunaEstadoParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        colunaEstadoParams.setMargins(10, 5, 10, 5);
                        colunaEstadoParams.weight = 2;
                        colunaEstado.setLayoutParams(colunaEstadoParams);

                        TextView estadoAluno = new TextView(this);
                        estadoAluno.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                        // Se a nota for maior q 60
                        estadoAluno.setText(Integer.parseInt(notaDoAluno) >= 60 ? "APROVADO" : "REPROVADO");
                        estadoAluno.setTextColor(estadoAluno.getText().equals("APROVADO") ? Color.GREEN : Color.RED);
                        colunaEstado.addView(estadoAluno);

                        tableRow.addView(colunaEstado);
                    }
                }

                tlNotasAluno.addView(tableRow);
            });
        }

        // Adiciona as frequencias
        if (!frequencias.isEmpty()) {
            tvFrequenciaAluno.setVisibility(View.VISIBLE);
            tvFrequenciaVazia.setVisibility(View.GONE);
            frequencias.forEach(frequencia -> {

                // Cria uma linha de tabela
                TableRow tableRow = new TableRow(this);
                LinearLayout.LayoutParams nomeTableRowParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                tableRow.setLayoutParams(nomeTableRowParams);

                // Cria nome da disciplina
                LinearLayout coluna = new LinearLayout(this);
                TableRow.LayoutParams colunaParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                colunaParams.setMargins(5, 5, 5, 5);
                colunaParams.weight = 5;
                coluna.setLayoutParams(colunaParams);

                TextView nomeDisciplina = new TextView(this);
                nomeDisciplina.setText(frequencia.getDisciplina());

                coluna.addView(nomeDisciplina);
                tableRow.addView(coluna);

                // Cria frequencia aluno
                LinearLayout colunaNota = new LinearLayout(this);
                TableRow.LayoutParams colunaNotaParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                colunaNotaParams.setMargins(10, 5, 10, 5);
                colunaNotaParams.weight = 1;
                colunaNota.setLayoutParams(colunaNotaParams);

                TextView notaAluno = new TextView(this);
                notaAluno.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                String[] listFrequencias = frequencia.getListaFrequencia().split(",");

                Log.e("", listFrequencias.toString());

                // Loop nas frequencias
                notaAluno.setText(listFrequencias.length+"");
                colunaNota.addView(notaAluno);
                tableRow.addView(colunaNota);

                // Criar coluna avisando se passou ou não
                LinearLayout colunaEstado = new LinearLayout(this);
                TableRow.LayoutParams colunaEstadoParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                colunaEstadoParams.setMargins(10, 5, 10, 5);
                colunaEstadoParams.weight = 2;
                colunaEstado.setLayoutParams(colunaEstadoParams);

                TextView estadoAluno = new TextView(this);
                estadoAluno.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                // Se faltou demais
                Disciplina disciplina = DisciplinaDAO.getDisciplinaByName(frequencia.getDisciplina());
                if (disciplina != null) {
                    int percentFaltas = (listFrequencias.length / 100) * disciplina.getTotalAulas();
                    estadoAluno.setText(percentFaltas >= 30 ? "APROVADO" : "REPROVADO");
                    estadoAluno.setTextColor(estadoAluno.getText().equals("APROVADO") ? Color.GREEN : Color.GRAY);
                    colunaEstado.addView(estadoAluno);

                    tableRow.addView(colunaEstado);

                    tlFrequenciaAluno.addView(tableRow);
                }
            });
        }
    }
}

package com.example.cadastroalunos.activities.cadastros;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import fr.ganfra.materialspinner.MaterialSpinner;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.dao.ProfessorDAO;
import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.Professor;
import com.example.cadastroalunos.model.Turma;
import com.example.cadastroalunos.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CadastroTurmaActivity extends AppCompatActivity {
    private LinearLayout lnPrincipal;

    private MaterialSpinner spRegime;

    private MaterialSpinner spPeriodo;

    private AutoCompleteTextView atProfessor;
    private AutoCompleteTextView atAlunos;

    // Lista de alunos
    private LinearLayout lnAlunos;

    private Button adicionarAluno;

    private List<Aluno> listaAlunosParaAdd;

    private Aluno alunoASerAdicionado;


    public Aluno getAlunoASerAdicionado() {
        return alunoASerAdicionado;
    }

    public void setAlunoASerAdicionado(Aluno alunoASerAdicionado) {
        this.alunoASerAdicionado = alunoASerAdicionado;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_turma);

        atProfessor = findViewById(R.id.atProfessor);

        // Pegar as views
        lnPrincipal = findViewById(R.id.lnPrincipal);
        lnAlunos = findViewById(R.id.lnAlunos);
        lnAlunos.setBackgroundColor(Color.TRANSPARENT);
        spPeriodo = findViewById(R.id.spPeriodo);
        spRegime = findViewById(R.id.spRegime);
        adicionarAluno = findViewById(R.id.adicionarAluno);
        listaAlunosParaAdd = new ArrayList<>();

        // Inicia os spinners
        iniciaBehaviors();

        super.setTitle("Cadastro de Turmas");
    }


    private void iniciaBehaviors() {

        // Criar spinner de regimes
        String regimes[] = new String[]{"Matinal", "Diurno", "Noturno"};

        ArrayAdapter adapterRegimes = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, regimes);

        spRegime.setAdapter(adapterRegimes);

        // Criar spinner de período
        String periodos[] = new String[]{"1a Série",
                "2a Série", "3a Série", "4a Série"};

        ArrayAdapter adapterPeriodo = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, periodos);

        spPeriodo.setAdapter(adapterPeriodo);

        // Add evento ao clicar em adicionar aluno
        adicionarAluno.setOnClickListener((View view) -> {
            adicionaAluno();
        });

        // Add evento de regime
        spRegime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                criarACProfessores();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /**
     * Criando um campo com lista de alunos cadastrados
     * ao digitar ele vai exibindo uma lista contendo os
     * alunos pelas letras digitadas
     **/
    private void criarACProfessores() {
        if (spPeriodo.getSelectedItem() != null && spRegime.getSelectedItem() != null) {
            atProfessor.setVisibility(View.VISIBLE);

            String periodo = spPeriodo.getSelectedItem().toString();
            String regime = spRegime.getSelectedItem().toString();

            List<Professor> listaProfessores = new ArrayList<>(ProfessorDAO.retornaProfessores("periodo = ? and regime = ?", new String[]{periodo, regime}, ""));

            // Depois de retornado os alunos, é necessário criar um vetor para atribuir ao adapter
            String[] nomes = new String[listaProfessores.size()];

            for (int i = 0; i < listaProfessores.size(); i++) {
                Professor professor = listaProfessores.get(i);
                nomes[i] = professor.getId() + " - " + professor.getNome();
            }

            //Criando o adapter
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_dropdown_item_1line,
                    nomes);

            //Setando o adapter ao componente AutoCompleteTextView
            atProfessor.setAdapter(adapter);

            // Add evento
            atProfessor.setOnItemClickListener((parent, view, position, rowId) -> {
                criarACAlunos();
            });
        }
    }

    /**
     * Criando um campo com lista de alunos cadastrados
     * ao digitar ele vai exibindo uma lista contendo os
     * alunos pelas letras digitadas
     **/
    private void criarACAlunos() {
        atAlunos = findViewById(R.id.atAlunos);
        lnAlunos.setVisibility(View.VISIBLE);

        int idProfessor = Integer.parseInt(Util.splitString(atProfessor.getText().toString(), 0, " - "));
        Professor professor = ProfessorDAO.getProfessor(idProfessor);

        // Se o professor existir
        if (professor != null) {
            List<Aluno> listaAlunos = AlunoDAO.retornaAlunos("periodo = ? and curso = ? and regime = ?", new String[]{professor.getPeriodo(), professor.getCurso(), professor.getRegime()}, "nome");

            // Depois de retornado os alunos, é necessário criar um vetor para atribuir ao adapter
            String[] nomes = new String[listaAlunos.size()];

            for (int i = 0; i < listaAlunos.size(); i++) {
                Aluno aluno = listaAlunos.get(i);
                nomes[i] = aluno.getRa() + " - " + aluno.getNome();
            }

            //Criando o adapter
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_dropdown_item_1line,
                    nomes);

            //Setando o adapter ao componente AutoCompleteTextView
            atAlunos.setAdapter(adapter);

            // Add evento
            atAlunos.setOnItemClickListener((parent, view, position, rowId) -> {
                String selection = (String) parent.getItemAtPosition(position);
                //TODO Do something with the selected text

                // Pega o aluno baseado no RA
                int RA = Integer.parseInt(Util.splitString(selection, 0, " - "));
                Aluno aluno = AlunoDAO.getAlunoByRA(RA);

                if (aluno != null) {
                    setAlunoASerAdicionado(aluno);
                }
            });
        }
    }


    /**
     * Adiciona aluno na lista
     */
    private void adicionaAluno() {
        Aluno aluno = getAlunoASerAdicionado();
        // Se aluno não existir na lista, pode adicioná-lo
        if (listaAlunosParaAdd.contains(aluno)) {
            return;
        }

        lnAlunos.setBackgroundColor(Color.TRANSPARENT);

        // Criar o linear layout
        LinearLayout linha = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 5, 10, 5);
        linha.setOrientation(LinearLayout.HORIZONTAL);
        linha.setLayoutParams(params);

        // Cria a view do aluno
        TextView novoAluno = new TextView(this);
        novoAluno.setText(aluno.getNome());

        // Add aluno na linha
        linha.addView(novoAluno);
        listaAlunosParaAdd.add(aluno);

        // Cria botões de exclusão ao lado do nome do aluno
        Button excluir = new Button(this);
        excluir.setText("X");
        excluir.setTextColor(Color.WHITE);
        excluir.setBackgroundColor(Color.RED);
        excluir.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        // Add clique no botão de deletar
        excluir.setOnClickListener((View view) -> {
            Optional<Aluno> optAluno = listaAlunosParaAdd.stream().filter(a -> a.equals(aluno)).findFirst();
            if (optAluno.isPresent()) {
                listaAlunosParaAdd.remove(aluno);
                lnAlunos.removeView(linha);
            }
        });

        // Add botões de controle na linha
        linha.addView(excluir);

        // Add linha pro layout
        lnAlunos.addView(linha);
    }


    // Validação dos campos
    private void validaCampos() {
        // Valida o campo de regime
        if (spRegime.getSelectedItem() == null) {
            spRegime.setError("Informe o Regime da turma!");
            spRegime.requestFocus();
            return;
        }

        // Valida o campo de Professor
        if (atProfessor.getText().toString().equals("")) {
            atProfessor.setError("Informe o Professor da turma!");
            atProfessor.requestFocus();
            return;
        }

        // Valida o campo de CPF da Turma
        if (listaAlunosParaAdd.isEmpty()) {
            atAlunos.setError("Informe os alunos da turma!");
            atAlunos.requestFocus();
            return;
        }

        // Valida o campo de Periodo da Turma
        if (spPeriodo.getSelectedItem() == null) {
            spPeriodo.setError("Informe os alunos da turma!");
            spPeriodo.requestFocus();
            return;
        }

        salvarTurma();
    }

    /**
     * Salva a turma no banco
     */
    public void salvarTurma() {
        Turma turma = new Turma();
        turma.setRegime(spRegime.getSelectedItem().toString());
        turma.setPeriodo(spPeriodo.getSelectedItem().toString());

        int idProfessor = Integer.parseInt(Util.splitString(atProfessor.getText().toString(), 0, " - "));
        Professor professor = ProfessorDAO.getProfessor(idProfessor);
        turma.setProfessor(professor);

        turma.setAlunos(listaAlunosParaAdd);

        if (TurmaDAO.salvar(turma) > 0) {
            setResult(RESULT_OK);
            finish();
        } else
            Util.customSnackBar(lnPrincipal, "Erro ao salvar a turma (" + turma.getApelido() + ") " + " verifique o log", 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_cadastro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_limpar:
                limparCampos();
                return true;
            case R.id.mn_salvar:
                validaCampos();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void limparCampos() {
        spRegime.setSelection(0);
        atProfessor.clearListSelection();
        atAlunos.clearListSelection();
    }
}
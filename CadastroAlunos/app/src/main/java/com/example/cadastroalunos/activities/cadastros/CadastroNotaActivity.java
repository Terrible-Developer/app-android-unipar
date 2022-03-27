package com.example.cadastroalunos.activities.cadastros;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import fr.ganfra.materialspinner.MaterialSpinner;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
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
import com.example.cadastroalunos.dao.DisciplinaDAO;
import com.example.cadastroalunos.dao.NotaDAO;
import com.example.cadastroalunos.dao.ProfessorDAO;
import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.Disciplina;
import com.example.cadastroalunos.model.Nota;
import com.example.cadastroalunos.model.Professor;
import com.example.cadastroalunos.model.Turma;
import com.example.cadastroalunos.util.InputFilterMinMax;
import com.example.cadastroalunos.util.Util;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CadastroNotaActivity extends AppCompatActivity {
    private LinearLayout lnPrincipalNotas;

    private String disciplina;

    private AutoCompleteTextView atDisciplinaNota;

    private TextInputEditText edNota;

    private List<String> listaDisciplinasParaAdd;

    private Button adicionarNota;

    private LinearLayout lnNotas;

    private Disciplina disciplinaASerAdicionada;

    public void setDisciplinaASerAdicionada(Disciplina disciplinaASerAdicionada) {
        this.disciplinaASerAdicionada = disciplinaASerAdicionada;
    }

    public List<String> getListaDisciplinasParaAdd() {
        return listaDisciplinasParaAdd;
    }

    public void setListaDisciplinasParaAdd(List<String> listaDisciplinasParaAdd) {
        this.listaDisciplinasParaAdd = listaDisciplinasParaAdd;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    private Aluno aluno;

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    List<LinearLayout> listaNotasComponents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_nota);

        atDisciplinaNota = findViewById(R.id.atDisciplinaNota);

        // Pegar as views
        lnPrincipalNotas = findViewById(R.id.lnPrincipalNotas);
        lnPrincipalNotas.setBackgroundColor(Color.TRANSPARENT);
        edNota = findViewById(R.id.edNota);
        lnNotas = findViewById(R.id.lnNotas);
        listaDisciplinasParaAdd = new ArrayList<>();

        adicionarNota = findViewById(R.id.adicionarNota);

        listaNotasComponents = new ArrayList<>();

        // Startar objetos
        String idAluno = intent.getStringExtra("alunoID");
        if (idAluno != null) {
            Aluno alunoObject = AlunoDAO.getAluno(Integer.parseInt(idAluno));
            Log.e("CadastroNota", alunoObject.toString());
            setAluno(alunoObject);
            super.setTitle("Notas do "+aluno.getNome());
        }

        // Inicia os spinner
        iniciaBehaviors();
    }


    /**
     * Inicia todos os comportamentos dos campos
     */
    private void iniciaBehaviors() {
        criarACDisciplinas();

        // Add filtro para não ultrapassar o valor de 100
        edNota.setFilters(new InputFilter[]{new InputFilterMinMax(0, 100)});

        // Add evento ao clicar em adicionar aluno
        adicionarNota.setOnClickListener((View view) -> {
            adicionaNota();
        });
    }

    /**
     * Criando um campo com lista de alunos cadastrados
     * ao digitar ele vai exibindo uma lista contendo os
     * alunos pelas letras digitadas
     **/
    private void criarACDisciplinas() {
        atDisciplinaNota.setVisibility(View.VISIBLE);

        Turma turma = TurmaDAO.getTurma(aluno.getIdTurma());

        if (turma != null) {
            Professor professor = ProfessorDAO.getProfessor(turma.getIdProfessor());

            if (professor != null) {

                // Lista de disciplinas
                List<Disciplina> disc = new ArrayList(DisciplinaDAO.retornaDisciplinas("nome = ?", new String[]{professor.getDisciplina()}, ""));

                String[] disciplinas = new String[disc.size()];

                for (int i = 0; i < disciplinas.length; i++) {
                    Disciplina disciplina = disc.get(i);
                    disciplinas[i] = disciplina.getId() + " - " + disciplina.getNome();
                }

                ArrayAdapter adapterDisciplinas = new ArrayAdapter(this,
                        android.R.layout.simple_list_item_1, disciplinas);

                atDisciplinaNota.setAdapter(adapterDisciplinas);

                // Add evento
                atDisciplinaNota.setOnItemClickListener((parent, view, position, rowId) -> {
                    String selection = (String) parent.getItemAtPosition(position);

                    // Pega o aluno baseado no RA
                    int idDisciplina = Integer.parseInt(Util.splitString(selection, 0, " - "));
                    Disciplina disciplina = DisciplinaDAO.getDisciplina(idDisciplina);

                    if (disciplina != null && edNota.getText() != null) {
                        setDisciplinaASerAdicionada(disciplina);
                        listaDisciplinasParaAdd.add(disciplina.getId() + " - " + edNota.getText().toString()+ " - "+disciplina.getNome());
                    }
                });
            }
        }
    }

    /**
     * Adiciona nota na lista
     */
    private void adicionaNota() {
        if (edNota.getText().toString().equals("")) {
            edNota.setError("Selecione uma nota antes");
            return;
        }

        if (getListaDisciplinasParaAdd().isEmpty()) {
            atDisciplinaNota.setError("Selecione a matéria antes de atribuir a nota");
            return;
        }

        // Se aluno não existir na lista, pode adicioná-lo
        if (getListaDisciplinasParaAdd().contains(disciplina)) {
            atDisciplinaNota.setError("Essa nota já foi atribuída a esse aluno");
            return;
        }

        // Criar o linear layout
        LinearLayout linha = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(20, 5, 20, 5);
        linha.setLayoutParams(params);
        linha.setOrientation(LinearLayout.HORIZONTAL);

        // Cria a view da disciplina
        TextView novaDisciplina = new TextView(this);
        novaDisciplina.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 5));
        novaDisciplina.setText(disciplinaASerAdicionada.getNome()+ " - "+edNota.getText().toString());

        // Add disciplina na linha
        linha.addView(novaDisciplina);

        // Cria botões de exclusão ao lado do nome do aluno
        /*FloatingActionButton excluir = new FloatingActionButton(this);
        LinearLayout.LayoutParams excluirLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0.5f);
        excluirLayoutParams.setMargins(0, 0, 0, 0);
        excluir.setLayoutParams(excluirLayoutParams);
        excluir.setPadding(0, 0, 0, 0);
        excluir.setClickable(true);
        excluir.setImageResource(R.drawable.ic_clear);
        excluir.setSize(FloatingActionButton.SIZE_MINI);*/

        // Add clique no botão de deletar
        /*excluir.setOnClickListener((View view) -> {
            getListaDisciplinasParaAdd().forEach(nt-> {
                Disciplina ntDsc = DisciplinaDAO.getDisciplina(Integer.parseInt(Util.splitString(nt, 2, " - ")));
            });

            Optional<String> optDisciplina = getListaDisciplinasParaAdd().stream().filter(a -> Util.splitString(a, 1, " - ").contains(Util.splitString(disciplina, 1, " - "))).findFirst();
            if (optDisciplina.isPresent()) {
                lnNotas.removeView(linha);
                listaNotasComponents.remove(linha);
                setDisciplina(null);
                getListaDisciplinasParaAdd().remove(disciplina);
            }
        });*/

        // Add botões de controle na linha
        //linha.addView(excluir);

        // Add linha pro layout
        lnNotas.addView(linha);
        listaNotasComponents.add(linha);
    }


    /**
     * Validação dos campos
     */
    private void validaCampos() {
        // Valida o campo de curso da turma
        if (edNota.getText().toString().trim().isEmpty() || Integer.parseInt(edNota.getText().toString()) > 100) {
            edNota.setError("Informe a nota da disciplina!");
            edNota.requestFocus();
            return;
        }

        // Valida o campo de CPF da Turma
        if (getListaDisciplinasParaAdd().isEmpty()) {
            atDisciplinaNota.setError("Informe a disciplina !");
            atDisciplinaNota.requestFocus();
            return;
        }

        salvarNotas();
    }

    /**
     * Salva a nota no banco
     */
    public void salvarNotas() {
        List<Nota> notasAluno = NotaDAO.retornaNotas("ID_ALUNO = ? and ID_TURMA = ?", new String[]{aluno.getId().toString(), aluno.getIdTurma().toString()}, "");

        if (!notasAluno.isEmpty()) {
            Util.customSnackBar(lnNotas, "O aluno (" + aluno.getNome() + ") já possui notas cadastradas!", 0);
            return;
        }

        Nota nota = new Nota();
        nota.setIdAluno(aluno.getId());
        nota.setIdTurma(aluno.getIdTurma());

        // Lista de alunos de forma CANSADA kkkkk
        StringBuilder listaNotas = new StringBuilder();

        // Adiciona as notas do aluno em uma string única para salvar
        getListaDisciplinasParaAdd().forEach((disciplina) -> {
            int indexLoop = getListaDisciplinasParaAdd().indexOf(disciplina);
            int idDisciplina = Integer.parseInt(Util.splitString(disciplina, 0, " - "));
            int notaDisciplina = Integer.parseInt(Util.splitString(disciplina, 1, " - "));

            if (indexLoop > 0) {
                listaNotas.append(",");
            }
            listaNotas.append(idDisciplina).append(" - ").append(notaDisciplina);
        });

        Log.e("Lista de notas", listaNotas.toString());
        nota.setNotas(listaNotas.toString());

        if (NotaDAO.salvar(nota) > 0) {
            setResult(RESULT_OK);
            finish();
        } else
            Util.customSnackBar(lnNotas, "Erro ao salvar a nota (" + aluno.getCurso() + ") de (" + aluno.getNome() + ") " + " verifique o log", 0);
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
        atDisciplinaNota.setText("");
        edNota.setText("");
        getListaDisciplinasParaAdd().clear();
        listaNotasComponents.forEach(component-> {
            lnNotas.removeView(component);
        });
    }
}
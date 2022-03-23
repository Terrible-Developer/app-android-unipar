package com.example.cadastroalunos.activities.cadastros;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import fr.ganfra.materialspinner.MaterialSpinner;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
    private Integer notaASerAdicionada;

    public Integer getNotaASerAdicionada() {
        return notaASerAdicionada;
    }

    public void setNotaASerAdicionada(Integer notaASerAdicionada) {
        this.notaASerAdicionada = notaASerAdicionada;
    }

    public Disciplina getDisciplinaASerAdicionada() {
        return disciplinaASerAdicionada;
    }

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

        // Inicia os spinner
        iniciaBehaviors();

        // Startar objetos
        String idAluno = intent.getStringExtra("alunoID");
        if (idAluno != null) {
            Aluno alunoObject = AlunoDAO.getAluno(Integer.parseInt(idAluno));
            Log.e("CadastroNota", alunoObject.toString());
            setAluno(alunoObject);
            super.setTitle("Notas do "+aluno.getNome());
        }
    }


    private void iniciaBehaviors() {
        criarACDisciplinas();

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

        // Kusta de disciplionas
        List<Disciplina> disc = new ArrayList(DisciplinaDAO.retornaDisciplinas("", new String[]{}, ""));

        String[] disciplinas = new String[disc.size()];

        for (int i = 0; i < disciplinas.length; i++) {
            Disciplina disciplina = disc.get(i);
            disciplinas[i] = disciplina.getId()+" - "+disciplina.getNome();
        }

        ArrayAdapter adapterDisciplinas = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,  disciplinas);

        atDisciplinaNota.setAdapter(adapterDisciplinas);

        // Add evento
        atDisciplinaNota.setOnItemClickListener((parent, view, position, rowId) -> {
            String selection = (String) parent.getItemAtPosition(position);
            //TODO Do something with the selected text

            // Pega o aluno baseado no RA
            int idDisciplina = Integer.parseInt(Util.splitString(selection, 0, " - "));
            Disciplina disciplina = DisciplinaDAO.getDisciplina(idDisciplina);

            if (disciplina != null && edNota.getText() != null) {
                setDisciplinaASerAdicionada(disciplina);
                //setNotaASerAdicionada(Integer.valueOf(edNota.getText().toString()));
                listaDisciplinasParaAdd.add(disciplina.getId()+ " - "+disciplina.getNome()+ " - "+ edNota.getText().toString());
            }
        });
    }

    /**
     * Adiciona nota na lista
     */
    private void adicionaNota() {

        if (edNota.getText().toString().equals("")) {
            edNota.setError("Selecione uma nota antes");
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
        novaDisciplina.setText(disciplinaASerAdicionada.getNome());

        // Add disciplina na linha
        linha.addView(novaDisciplina);
        getListaDisciplinasParaAdd().add(disciplinaASerAdicionada.getId() +" - "+ disciplinaASerAdicionada.getNome() +" - "+edNota.getText().toString());

        // Cria botões de exclusão ao lado do nome do aluno
        FloatingActionButton excluir = new FloatingActionButton(this);
        LinearLayout.LayoutParams excluirLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0.5f);
        excluirLayoutParams.setMargins(0, 0, 0, 0);
        excluir.setLayoutParams(excluirLayoutParams);
        excluir.setPadding(0, 0, 0, 0);
        excluir.setClickable(true);
        excluir.setImageResource(R.drawable.ic_clear);
        excluir.setSize(FloatingActionButton.SIZE_MINI);

        // Add clique no botão de deletar
        excluir.setOnClickListener((View view) -> {
            Optional<String> optDisciplina = getListaDisciplinasParaAdd().stream().filter(a -> a.equals(disciplina)).findFirst();
            if (optDisciplina.isPresent()) {
                getListaDisciplinasParaAdd().remove(disciplina);
                setDisciplina(null);
                lnNotas.removeView(linha);
            }
        });

        // Add botões de controle na linha
        linha.addView(excluir);

        // Add linha pro layout
        lnNotas.addView(linha);
    }


    // Validação dos campos
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
     * Salva a turma no banco
     */
    public void salvarNotas() {
        Nota nota = new Nota();
        nota.setAlunoID(aluno.getId());

        // Lista de alunos de forma CANSADA kkkkk
        StringBuilder listaNotas = new StringBuilder();

        getListaDisciplinasParaAdd().forEach((disciplina) -> {
            int id = getListaDisciplinasParaAdd().indexOf(disciplina);
            int idDisciplina = Integer.parseInt(Util.splitString(disciplina, 0, " - "));
            int notaDisciplina = Integer.parseInt(Util.splitString(disciplina, 2, " - "));

            //Disciplina disc = DisciplinaDAO.getDisciplina(idDisciplina);

            Log.e("idDisciplina", String.valueOf(idDisciplina));
            Log.e("notaDisciplina", String.valueOf(notaDisciplina));

            if (id > 0) {
                listaNotas.append(",");
            }
            listaNotas.append(idDisciplina+" - "+notaDisciplina);
        });

        nota.setNotas(listaNotas.toString());

        Log.e("xota", listaNotas.toString());
        // TODO - E/xota: 1 - 1001 - 100  - corrigir na hr de salvar no banco


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
        edNota.setText("");
        atDisciplinaNota.setSelection(0);
    }
}
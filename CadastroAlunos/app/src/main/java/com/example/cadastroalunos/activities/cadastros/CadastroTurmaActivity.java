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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.dao.ProfessorDAO;
import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.enums.Regime;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.Professor;
import com.example.cadastroalunos.model.Turma;
import com.example.cadastroalunos.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CadastroTurmaActivity extends AppCompatActivity {
    private LinearLayout lnPrincipal;

    private MaterialSpinner spRegime;


    //private MaterialSpinner spProfessor;
    //private MaterialSpinner spAlunos;

    private AutoCompleteTextView atProfessor;
    private AutoCompleteTextView atAlunos;

    // Lista de alunos
    private LinearLayout lnAlunos;

    private Button adicionarAluno;

    private List<Aluno> listaAlunosParaAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_turma);

        // Pegar as views
        lnPrincipal = findViewById(R.id.lnPrincipal);
        lnAlunos = findViewById(R.id.lnAlunos);
        lnAlunos.setBackgroundColor(Color.TRANSPARENT);

        adicionarAluno = findViewById(R.id.adicionarAluno);

        listaAlunosParaAdd = new ArrayList<>();

        // Inicia os spinners
        iniciaBehaviors();
    }


    private void iniciaBehaviors(){
        spRegime = findViewById(R.id.spRegime);

        String regimes[] = new String[] {"Matinal, Diurno, Noturno"};


        ArrayAdapter adapterRegimes = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,  regimes);

        spRegime.setAdapter(adapterRegimes);

        // Cria os AutoCompletes
        criarACProfessores();
        criarACAlunos();


        // Add evento ao clicar em adicionar aluno
        adicionarAluno.setOnClickListener((View view) -> {
            //adicionaAluno();
        });

    }

    /**
     * Criando um campo com lista de alunos cadastrados
     * ao digitar ele vai exibindo uma lista contendo os
     * alunos pelas letras digitadas
     * **/
    private void criarACProfessores() {
        atProfessor = findViewById(R.id.atProfessor);

        List<Professor> listaProfessores = new ArrayList<>(ProfessorDAO.retornaProfessores("", new String[]{}, ""));

        // Depois de retornado os alunos, é necessário criar um vetor para atribuir ao adapter
        String[] nomes = new String[listaProfessores.size()];

        for (int i=0; i < listaProfessores.size(); i++) {
            Professor professor = listaProfessores.get(i);
            nomes[i] = professor.getId()+ " - "+professor.getNome();
        }

        //Criando o adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line,
                nomes);

        //Setando o adapter ao componente AutoCompleteTextView
        atProfessor.setAdapter(adapter);
    }

    /**
     * Criando um campo com lista de alunos cadastrados
     * ao digitar ele vai exibindo uma lista contendo os
     * alunos pelas letras digitadas
     * **/
    private void criarACAlunos() {
        atAlunos = findViewById(R.id.atAlunos);

        List<Aluno> listaAlunos;
        listaAlunos = AlunoDAO.retornaAlunos("", new String[]{}, "");

        // Depois de retornado os alunos, é necessário criar um vetor para atribuir ao adapter
        String[] nomes = new String[listaAlunos.size()];

        for (int i=0; i < listaAlunos.size(); i++) {
            Aluno aluno = listaAlunos.get(i);
            nomes[i] = aluno.getRa()+ " - "+aluno.getNome();
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
            int RA = Integer.parseInt(getParameter(selection, 0, " - "));
            Aluno aluno = AlunoDAO.getAlunoByRA(RA);

            if (aluno != null) {
                adicionaAluno(aluno);
            }

            System.out.println(aluno);
        });
    }

    /**
     * Separa string e pega informações importantes
     * @param string Texto a ser splitado
     * @param index Qual posição desejamos retornar
     * @param separator Separador
     */
    private String getParameter(String string, int index, String separator) {
        String[] partes = string.split(separator);
        System.out.println(Arrays.toString(partes));
        return partes[index];
    }

    /**
     * Adiciona aluno na lista
     * @param aluno O aluno a ser adicionado
     */
    private void adicionaAluno(Aluno aluno) {
        // Se aluno não existir na lista, pode adicioná-lo
        if (listaAlunosParaAdd.contains(aluno)) {
            return;
        }

        lnAlunos.setBackgroundColor(Color.TRANSPARENT);

        // Criar o linear layout
        LinearLayout linha = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10,5,10,5);
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



    //Validação dos campos
    private void validaCampos(){
        //Valida o campo de regime
        if(spRegime.getSelectedItem().toString().equals("")){
            spRegime.setError("Informe o Regimee da turma!");
            spRegime.requestFocus();
            return;
        }

        //Valida o campo de Professor
        if(atProfessor.toString().equals("")){
            atProfessor.setError("Informe o Professor da turma!");
            atProfessor.requestFocus();
            return;
        }

        //Valida o campo de CPF do Aluno
        if(listaAlunosParaAdd.isEmpty()) {
            atAlunos.setError("Informe os alunos da turma!");
            atAlunos.requestFocus();
            return;
        }

        salvarTurma();
    }

    /**
     * Salva a turma no banco
     */
    public void salvarTurma(){
        Turma turma = new Turma();
        turma.setRegime(Regime.ANUAL);

        int idProfessor = Integer.parseInt(getParameter(atProfessor.getText().toString(), 0, " - "));
        Professor professor = ProfessorDAO.getProfessor(idProfessor);
        turma.setProfessor(professor);

        turma.setAlunos(listaAlunosParaAdd);

        if(TurmaDAO.salvar(turma) > 0) {

            setResult(RESULT_OK);
            finish();
        } else
            Util.customSnackBar(lnPrincipal, "Erro ao salvar a turma ("+turma.getApelido()+") " +
                    "verifique o log", 0);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_cadastro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mn_limpar:
                //TODO: adicionar método  de limpar dados
                limparCampos();
                return true;
            case R.id.mn_salvar:
                //TODO: adicionar método  de salvar dados
                validaCampos();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void limparCampos() {
        spRegime.setSelection(0);
        atProfessor.setSelection(0);
        atAlunos.setSelection(0);
    }
}
package com.example.cadastroalunos.activities.cadastros;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import fr.ganfra.materialspinner.MaterialSpinner;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.example.cadastroalunos.util.InputFilterMinMax;
import com.example.cadastroalunos.util.Util;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class CadastroFrequenciaActivity extends AppCompatActivity {
    private LinearLayout lnPrincipalFrequencia;

    private AutoCompleteTextView atDisciplinaFrequencia;

    private TextInputEditText edDataFrequencia;

    private Aluno aluno;

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }


    private int vAno;
    private int vMes;
    private int vDia;
    private View dataSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_frequencia);

        atDisciplinaFrequencia = findViewById(R.id.atDisciplinaFrequencia);
        edDataFrequencia = findViewById(R.id.edDataFrequencia);
        lnPrincipalFrequencia = findViewById(R.id.lnPrincipalFrequencia);

        edDataFrequencia.setFocusable(false);

        // Startar objetos
        String idAluno = intent.getStringExtra("alunoID");
        if (idAluno != null) {
            Aluno alunoObject = AlunoDAO.getAluno(Integer.parseInt(idAluno));
            Log.e("CadastroFrequencia", alunoObject.toString());
            setAluno(alunoObject);
            super.setTitle("Frequencia de "+aluno.getNome());
        }

        // Inicia os spinner
        iniciaBehaviors();

        setDataAtual();
    }


    private void setDataAtual() {
        Calendar calendar = Calendar.getInstance();
        vDia = calendar.get(Calendar.DAY_OF_MONTH);
        vMes = calendar.get(Calendar.MONTH);
        vAno = calendar.get(Calendar.YEAR);
    }


    /**
     * Inicia todos os comportamentos dos campos
     */
    private void iniciaBehaviors() {
        criarACDisciplinas();
    }

    /**
     * Criando um campo com lista de alunos cadastrados
     * ao digitar ele vai exibindo uma lista contendo os
     * alunos pelas letras digitadas
     **/
    private void criarACDisciplinas() {
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

                atDisciplinaFrequencia.setAdapter(adapterDisciplinas);
            }
        }
    }

    /**
     * Validação dos campos
     */
    private void validaCampos() {
        // Valida o campo de curso da turma
        if (edDataFrequencia.getText().toString().equals("")) {
            edDataFrequencia.setError("Informe a data da presença!");
            edDataFrequencia.requestFocus();
            return;
        }

        // Valida o campo de CPF da Turma
        if (atDisciplinaFrequencia.getText().toString().equals("")) {
            atDisciplinaFrequencia.setError("Informe a disciplina !");
            atDisciplinaFrequencia.requestFocus();
            return;
        }

        salvarNotas();
    }

    /**
     * Salva a nota no banco
     */
    public void salvarNotas() {
        Frequencia frequencia = new Frequencia();
        frequencia.setIdAluno(aluno.getId());

        Turma turma = TurmaDAO.getTurma(aluno.getIdTurma());
        if (turma != null) {
            frequencia.setIdTurma(turma.getId());
            Professor professor = ProfessorDAO.getProfessor(turma.getIdProfessor());
            if (professor != null) {
                frequencia.setDisciplina(professor.getDisciplina());

                List<Frequencia> frequenciasAluno = FrequenciaDAO.retornaFrequencias("ID_ALUNO = ? and DISCIPLINA = ? and ID_TURMA", new String[]{aluno.getId().toString(), professor.getDisciplina(), String.valueOf(aluno.getIdTurma())}, "");

                if (frequenciasAluno.size() <= 1) {
                    frequencia.setListaFrequencia(edDataFrequencia.getText().toString());
                } else {
                    // Lista de frequencia de forma CANSADA kkkkk
                    StringBuilder listaFrequencia = new StringBuilder();
                    listaFrequencia.append(frequenciasAluno.get(0).getListaFrequencia());
                    listaFrequencia.append(","+edDataFrequencia.getText().toString());
                    frequencia.setListaFrequencia(listaFrequencia.toString());
                }
            }
        }

        if (FrequenciaDAO.salvar(frequencia) > 0) {
            setResult(RESULT_OK);
            finish();
        } else
            Util.customSnackBar(lnPrincipalFrequencia, "Erro ao salvar a frequencia de (" + aluno.getNome() + ") " + " verifique o log", 0);
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

    public void selecionarData(View view) {
        dataSelecionada = view;
        showDialog(0);
    }

    private DatePickerDialog.OnDateSetListener setDatePicker =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    vAno = year;
                    vMes = month;
                    vDia = day;

                    atualizaData();
                }
            };

    private void atualizaData() {
        TextInputEditText edit = (TextInputEditText)dataSelecionada;
        edit.setText(new StringBuilder().append(vDia).append("/")
                .append(vMes + 1).append("/")
                .append(vAno));
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        setDataAtual();
        return new DatePickerDialog(this, setDatePicker,
                vAno, vMes, vDia);
    }

    private void limparCampos() {
        atDisciplinaFrequencia.setText("");
        edDataFrequencia.setText("");
    }
}
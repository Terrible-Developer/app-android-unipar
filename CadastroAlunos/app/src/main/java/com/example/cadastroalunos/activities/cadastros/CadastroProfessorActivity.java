package com.example.cadastroalunos.activities.cadastros;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import fr.ganfra.materialspinner.MaterialSpinner;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.dao.DisciplinaDAO;
import com.example.cadastroalunos.dao.ProfessorDAO;
import com.example.cadastroalunos.model.Disciplina;
import com.example.cadastroalunos.model.Professor;
import com.example.cadastroalunos.util.CpfMask;
import com.example.cadastroalunos.util.Util;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CadastroProfessorActivity extends AppCompatActivity {

    private TextInputEditText edNomeProfessor;
    private TextInputEditText edCpfProfessor;
    private TextInputEditText edDtNascProfessor;
    private MaterialSpinner spCursoProfessor;
    private MaterialSpinner spPeriodoProfessor;
    private MaterialSpinner spDisciplinaProfessor;
    //private MaterialSpinner spRegimeProfessor;

    private LinearLayout lnPrincipal;


    private int vAno;
    private int vMes;
    private int vDia;
    private View dataSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_professor);

        edNomeProfessor = findViewById(R.id.edNomeProfessor);
        edCpfProfessor = findViewById(R.id.edCpfProfessor);
        edDtNascProfessor = findViewById(R.id.edDtNascProfessor);

        spCursoProfessor = findViewById(R.id.spCursoProfessor);
        spPeriodoProfessor = findViewById(R.id.spPeriodoProfessor);
        spDisciplinaProfessor = findViewById(R.id.spDisciplinaProfessor);

        //spRegimeProfessor = findViewById(R.id.spRegimeProfessor);

        lnPrincipal = findViewById(R.id.lnPrincipal);
        lnPrincipal = findViewById(R.id.lnPrincipal);

        edDtNascProfessor.setFocusable(false);

        edCpfProfessor.addTextChangedListener(CpfMask.insert(edCpfProfessor));

        iniciaSpinners();

        setDataAtual();

        super.setTitle("Cadastro de Professor");
    }

    private void setDataAtual() {
        Calendar calendar = Calendar.getInstance();
        vDia = calendar.get(Calendar.DAY_OF_MONTH);
        vMes = calendar.get(Calendar.MONTH);
        vAno = calendar.get(Calendar.YEAR);
    }

    private void iniciaSpinners(){

        // Kusta de disciplionas
        List<Disciplina> disc = new ArrayList(DisciplinaDAO.retornaDisciplinas("", new String[]{}, ""));

        String[] disciplinas = new String[disc.size()];

        for (int i = 0; i < disciplinas.length; i++) {
            Disciplina disciplina = disc.get(i);
            disciplinas[i] = disciplina.getNome();
        }

        ArrayAdapter adapterDisciplinas = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,  disciplinas);

        spDisciplinaProfessor.setAdapter(adapterDisciplinas);


        // Lista de cursos
        String[] cursos = new String[]{"An??lise e Desenv. Sistemas",
                "Administra????o", "Ci??ncias Cont??beis", "Direito",
                "Farm??cia", "Nutri????o"};

        ArrayAdapter adapterCursos = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,  cursos);

        spCursoProfessor.setAdapter(adapterCursos);

        // Lista de per??odos
        String[] periodos = new String[]{"1a S??rie",
                "2a S??rie", "3a S??rie", "4a S??rie"};

        ArrayAdapter adapterPeriodo = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,  periodos);

        spPeriodoProfessor.setAdapter(adapterPeriodo);

        // Criar spinner de regimes
        /*String regimes[] = new String[] {"Matinal", "Diurno", "Noturno"};

        ArrayAdapter adapterRegimes = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, regimes);

        spRegimeProfessor.setAdapter(adapterRegimes);*/

        //A????o ao selecionar o item da lista
        spCursoProfessor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){

                    /*Button btADS = new Button(getBaseContext());
                    btADS.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                             LinearLayout.LayoutParams.WRAP_CONTENT));
                    btADS.setText("Botao ADS");
                    btADS.setBackgroundColor(getColor(R.color.teal_200));

                    llPrincipal.addView(btADS);*/
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    //Valida????o dos campos
    private void validaCampos(){
        // Valida o campo de nome do Professor
        if(edNomeProfessor.getText().toString().equals("")){
            edNomeProfessor.setError("Informe o Nome do Professor!");
            edNomeProfessor.requestFocus();
            return;
        }

        // Valida o campo de CPF do Professor
        if(edCpfProfessor.getText().toString().equals("")){
            edCpfProfessor.setError("Informe o CPF do Professor!");
            edCpfProfessor.requestFocus();
            return;
        }

        // Valida o campo de CPF do Professor
        if(edDtNascProfessor.getText().toString().equals("")){
            edDtNascProfessor.setError("Informe a data de nascimento do Professor!");
            edDtNascProfessor.requestFocus();
            return;
        }

        // Valida o campo de per??odo do Professor
        if(spPeriodoProfessor.getSelectedItem() == null){
            spPeriodoProfessor.setError("Informe o per??odo do Professor!");
            spPeriodoProfessor.requestFocus();
            return;
        }

        // Valida o campo de per??odo do Professor
        if(spCursoProfessor.getSelectedItem() == null){
            spCursoProfessor.setError("Informe o curso do Professor!");
            spCursoProfessor.requestFocus();
            return;
        }

        // Valida a disciplina do Professor
        if(spDisciplinaProfessor.getSelectedItem() == null){
            spDisciplinaProfessor.setError("Informe a disciplina do Professor!");
            spDisciplinaProfessor.requestFocus();
            return;
        }

        // Valida o campo de regime do Professor
        /*if(spRegimeProfessor.getSelectedItem() == null){
            spRegimeProfessor.setError("Informe o regime do Professor!");
            spRegimeProfessor.requestFocus();
            return;
        }*/

        salvarProfessor();
    }

    public void salvarProfessor(){
        Professor professor = new Professor();

        professor.setNome(edNomeProfessor.getText().toString());
        professor.setCpf(edCpfProfessor.getText().toString());
        professor.setDtNasc(edDtNascProfessor.getText().toString());
        professor.setCurso(spCursoProfessor.getSelectedItem().toString());
        professor.setPeriodo(spPeriodoProfessor.getSelectedItem().toString());
        professor.setDisciplina(spDisciplinaProfessor.getSelectedItem().toString());
        //professor.setRegime(spRegimeProfessor.getSelectedItem().toString());

        if(ProfessorDAO.salvar(professor) > 0) {
            setResult(RESULT_OK);
            finish();
        }else
            Util.customSnackBar(lnPrincipal, "Erro ao salvar o professor ("+professor.getNome()+") " +
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
                //TODO: adicionar m??todo  de limpar dados
                limparCampos();
                return true;
            case R.id.mn_salvar:
                //TODO: adicionar m??todo  de salvar dados
                validaCampos();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void limparCampos() {
        edNomeProfessor.setText("");
        edCpfProfessor.setText("");
        edDtNascProfessor.setText("");
        spCursoProfessor.setSelection(0);
        spDisciplinaProfessor.setSelection(0);
        //spRegimeProfessor.setSelection(0);
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
}
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
import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.util.CpfMask;
import com.example.cadastroalunos.util.Util;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class CadastroAlunoActivity extends AppCompatActivity {

    private TextInputEditText edRaAluno;
    private TextInputEditText edNomeAluno;
    private TextInputEditText edCpfAluno;
    private TextInputEditText edDtNascAluno;
    private TextInputEditText edDtMatAluno;
    //private MaterialSpinner spRegimeAluno;
    private MaterialSpinner spCursoAluno;
    private MaterialSpinner spPeriodoAluno;
    private LinearLayout lnPrincipal;


    private int vAno;
    private int vMes;
    private int vDia;
    private View dataSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);

        edRaAluno = findViewById(R.id.edRaAluno);
        edNomeAluno = findViewById(R.id.edNomeAluno);
        edCpfAluno = findViewById(R.id.edCpfAluno);
        edDtNascAluno = findViewById(R.id.edDtNascAluno);
        edDtMatAluno = findViewById(R.id.edDtMatAluno);
        //spRegimeAluno = findViewById(R.id.spRegimeAluno);
        lnPrincipal = findViewById(R.id.lnPrincipal);


        edDtNascAluno.setFocusable(false);
        edDtMatAluno.setFocusable(false);

        edCpfAluno.addTextChangedListener(CpfMask.insert(edCpfAluno));
        iniciaSpinners();

        setDataAtual();
    }

    private void setDataAtual() {
        Calendar calendar = Calendar.getInstance();
        vDia = calendar.get(Calendar.DAY_OF_MONTH);
        vMes = calendar.get(Calendar.MONTH);
        vAno = calendar.get(Calendar.YEAR);
    }

    private void iniciaSpinners(){
        spCursoAluno = findViewById(R.id.spCursoAluno);
        spPeriodoAluno = findViewById(R.id.spPeriodoAluno);

        String cursos[] = new String[]{"Análise e Desenv. Sistemas",
                "Administração", "Ciências Contábeis", "Direito",
                "Farmácia", "Nutrição"};

        String periodos[] = new String[]{"1a Série",
                "2a Série", "3a Série", "4a Série"};


        ArrayAdapter adapterCursos = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,  cursos);
        ArrayAdapter adapterPeriodo = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,  periodos);

        spCursoAluno.setAdapter(adapterCursos);
        spPeriodoAluno.setAdapter(adapterPeriodo);

        // Criar spinner de regimes
        /*String regimes[] = new String[] {"Matinal", "Diurno", "Noturno"};

        ArrayAdapter adapterRegimes = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, regimes);

        spRegimeAluno.setAdapter(adapterRegimes);*/

        //Ação ao selecionar o item da lista
        spCursoAluno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

    //Validação dos campos
    private void validaCampos(){
        //Valida o campo Ra Aluno
        if(edRaAluno.getText().toString().equals("")){
            edRaAluno.setError("Informe o RA do Aluno!");
            edRaAluno.requestFocus();
            return;
        }

        //Valida o campo de nome do Aluno
        if(edNomeAluno.getText().toString().equals("")){
            edNomeAluno.setError("Informe o Nome do Aluno!");
            edNomeAluno.requestFocus();
            return;
        }

        //Valida o campo de CPF do Aluno
        if(edCpfAluno.getText().toString().equals("")){
            edCpfAluno.setError("Informe o CPF do Aluno!");
            edCpfAluno.requestFocus();
            return;
        }

        //Valida o campo de CPF do Aluno
        if(edDtNascAluno.getText().toString().equals("")){
            edDtNascAluno.setError("Informe a data de nascimento do Aluno!");
            edDtNascAluno.requestFocus();
            return;
        }

        //Valida o campo de CPF do Aluno
        if(edDtMatAluno.getText().toString().equals("")){
            edDtMatAluno.setError("Informe a data de matricula do Aluno!");
            edDtMatAluno.requestFocus();
            return;
        }

        // Valida o campo de período do Aluno
        if(spPeriodoAluno.getSelectedItem() == null){
            spPeriodoAluno.setError("Informe o periodo do Aluno!");
            spPeriodoAluno.requestFocus();
            return;
        }

        // Valida o campo de regime do Aluno
        /*if(spRegimeAluno.getSelectedItem() == null){
            spRegimeAluno.setError("Informe o regime do Aluno!");
            spRegimeAluno.requestFocus();
            return;
        }*/

        salvarAluno();
    }

    public void salvarAluno(){
        Aluno aluno = new Aluno();
        aluno.setRa(Integer.parseInt(edRaAluno.getText().toString()));
        aluno.setNome(edNomeAluno.getText().toString());
        aluno.setCpf(edCpfAluno.getText().toString());
        aluno.setDtNasc(edDtNascAluno.getText().toString());
        aluno.setDtMatricula(edDtMatAluno.getText().toString());
        aluno.setPeriodo(spPeriodoAluno.getSelectedItem().toString());
        aluno.setCurso(spCursoAluno.getSelectedItem().toString());
        //aluno.setRegime(spRegimeAluno.getSelectedItem().toString());

        if(AlunoDAO.salvar(aluno) > 0) {
            setResult(RESULT_OK);
            finish();
        }else
            Util.customSnackBar(lnPrincipal, "Erro ao salvar o aluno ("+aluno.getNome()+") " +
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
        edRaAluno.setText("");
        edNomeAluno.setText("");
        edCpfAluno.setText("");
        edDtNascAluno.setText("");
        edDtMatAluno.setText("");
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
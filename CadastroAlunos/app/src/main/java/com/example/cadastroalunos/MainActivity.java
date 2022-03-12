package com.example.cadastroalunos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView atAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Criando um campo com lista de alunos cadastrados
         * ao digitar ele vai exibindo uma lista contendo os
         * alunos pelas letras digitadas
         * **/
        atAlunos = findViewById(R.id.atAlunos);
        List<Aluno> listaAlunos = new ArrayList<>();
        listaAlunos = AlunoDAO.retornaAlunos("", new String[]{}, "");

        //Depois de retornado os alunos, é necessário criar um vetor
        //para atribuir ao adapter
        String[]nomes = new String[listaAlunos.size()];
        for (int i=0; i < listaAlunos.size(); i++) {
            Aluno aluno = listaAlunos.get(i);
            nomes[i] = aluno.getRa()+" - "+aluno.getNome();
        }
        //Criando o adapter
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_dropdown_item_1line,
                        nomes);
        //Setando o adapter ao componente AutoCompleteTextView
        atAlunos.setAdapter(adapter);
    }


    public void cadastrarAluno(View view) {
        Intent intent = new Intent(this, ListaAlunoActivity.class);
        startActivity(intent);
    }
}
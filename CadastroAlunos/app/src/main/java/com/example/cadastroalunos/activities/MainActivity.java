package com.example.cadastroalunos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.activities.lista.ListaAlunoActivity;
import com.example.cadastroalunos.activities.lista.ListaDisciplinaActivity;
import com.example.cadastroalunos.activities.lista.ListaProfessorActivity;
import com.example.cadastroalunos.activities.lista.ListaTurmaActivity;
import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.util.DeleteAll;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    boolean deleteAllData = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (deleteAllData)
            DeleteAll.deleteAllrecords(this);

        // Seta o conteúdo da víew
        setContentView(R.layout.activity_main);
    }


    public void cadastrarAluno(View view) {
        Intent intent = new Intent(this, ListaAlunoActivity.class);
        startActivity(intent);
    }

    public void cadastrarProfessor(View view) {
        Intent intent = new Intent(this, ListaProfessorActivity.class);
        startActivity(intent);
    }

    public void cadastrarTurma(View view) {
        Intent intent = new Intent(this, ListaTurmaActivity.class);
        startActivity(intent);
    }

    public void cadastrarDisciplina(View view) {
        Intent intent = new Intent(this, ListaDisciplinaActivity.class);
        startActivity(intent);
    }
}
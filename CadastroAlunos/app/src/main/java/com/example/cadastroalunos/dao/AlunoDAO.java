package com.example.cadastroalunos.dao;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.cadastroalunos.model.Aluno;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlunoDAO {

    public static long salvar(Aluno aluno){
        try{
            return aluno.save();
        }catch (Exception ex){
            Log.e("Erro", "Erro ao salvar o aluno: "+ex.getMessage());
            return -1;
        }
    }

    public static Aluno getAluno(int id){
         try{
            return Aluno.findById(Aluno.class, id);
         }catch (Exception ex){
             Log.e("Erro", "Erro ao retornar o aluno: "+ex.getMessage());
             return null;
         }
    }

    public static Aluno getAlunoByRA(int RA) {
        try {
            List<Aluno> alunos = Aluno.find(Aluno.class, "ra = ?", ""+RA);
            Optional<Aluno> aluno = alunos.stream().filter(a-> a.getRa() == RA).findFirst();
            if (aluno.isPresent())
                return aluno.get();
        } catch (Exception ex) {
            return null;
        }
        return null;
    }

    public static List<Aluno> retornaAlunos(String where, String[]whereArgs, String orderBy){
        List<Aluno> list = new ArrayList<>();
        try{
            list = Aluno.find(Aluno.class, where, whereArgs, "", orderBy, "");
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar lista de alunos: "+ex.getMessage());
        }
        return list;
    }

    public static boolean delete(Aluno aluno){
        try{
            return Aluno.delete(aluno);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao apagar o aluno: "+ex.getMessage());
            return false;
        }

    }
}

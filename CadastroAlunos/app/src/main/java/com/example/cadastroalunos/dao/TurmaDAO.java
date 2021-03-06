package com.example.cadastroalunos.dao;

import android.util.Log;

import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.Turma;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TurmaDAO {

    public static long salvar(Turma turma){
        try{
            return turma.save();
        }catch (Exception ex){
            Log.e("Erro", "Erro ao salvar o turma: "+ex.getMessage());
            return -1;
        }
    }

    public static Turma getTurma(int id){
        try{
            return Turma.findById(Turma.class, id);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar o turma: "+ex.getMessage());
            return null;
        }
    }

    public static Turma getTurma(Long id){
        try{
            return Turma.findById(Turma.class, id);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar o turma: "+ex.getMessage());
            return null;
        }
    }

    public static List<Turma> retornaTurmas(String where, String[] whereArgs, String orderBy){
        List<Turma> list = new ArrayList<>();
        try{
            list = Turma.find(Turma.class, where, whereArgs, "", orderBy, "");
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar lista de turmas: "+ex.getMessage());
        }
        return list;
    }

    public static boolean delete(Turma turma){
        try{
            return Turma.delete(turma);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao apagar o turma: "+ex.getMessage());
            return false;
        }

    }




}

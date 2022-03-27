package com.example.cadastroalunos.dao;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.Disciplina;
import com.example.cadastroalunos.model.Frequencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FrequenciaDAO {

    public static long salvar(Frequencia frequencia){
        try{
            return frequencia.save();
        }catch (Exception ex){
            Log.e("Erro", "Erro ao salvar o frequencia: "+ex.getMessage());
            return -1;
        }
    }

    public static Frequencia getFrequencia(int id){
        try{
            return Frequencia.findById(Frequencia.class, id);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar o frequencia: "+ex.getMessage());
            return null;
        }
    }


    public static List<Frequencia> retornaFrequencias(String where, String[]whereArgs, String orderBy){
        List<Frequencia> list = new ArrayList<>();
        try{
            list = Frequencia.find(Frequencia.class, where, whereArgs, "", orderBy, "");
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar lista de frequencias: "+ex.getMessage());
        }
        return list;
    }

    public static boolean delete(Frequencia frequencia){
        try{
            return Frequencia.delete(frequencia);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao apagar o frequencia: "+ex.getMessage());
            return false;
        }

    }
}

package com.example.cadastroalunos.dao;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.Nota;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NotaDAO {

    public static long salvar(Nota nota){
        try{
            return nota.save();
        }catch (Exception ex){
            Log.e("Erro", "Erro ao salvar o nota: "+ex.getMessage());
            return -1;
        }
    }

    public static Nota getNota(int id){
        try{
            return Nota.findById(Nota.class, id);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar o nota: "+ex.getMessage());
            return null;
        }
    }

    public static List<Nota> retornaNotas(String where, String[]whereArgs, String orderBy){
        List<Nota> list = new ArrayList<>();
        try{
            list = Nota.find(Nota.class, where, whereArgs, "", orderBy, "");
        }catch (Exception ex){
            Log.e("Erro", "Erro ao retornar lista de notas: "+ex.getMessage());
        }
        return list;
    }

    public static boolean delete(Nota nota){
        try{
            return Nota.delete(nota);
        }catch (Exception ex){
            Log.e("Erro", "Erro ao apagar o nota: "+ex.getMessage());
            return false;
        }

    }
}

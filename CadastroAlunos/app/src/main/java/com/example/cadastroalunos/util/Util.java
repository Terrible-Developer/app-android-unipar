package com.example.cadastroalunos.util;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.cadastroalunos.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;

public class Util {

    /***
     * SnackBar customizado
     * @param view: Onde será exibido
     * @param msg: Mensagem
     * @param tipo: tipo do icone 0-Erro, 1-Sucesso, 2-Atenção
     */
    public static void customSnackBar(View view, String msg, int tipo){
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        View viewSnack = snackbar.getView();
        TextView tv = (TextView) viewSnack.findViewById(R.id.snackbar_text);
        if(tipo == 0)
            tv.setCompoundDrawablesRelativeWithIntrinsicBounds(R.mipmap.ic_cancel,
                    0,0,0);
        if(tipo == 1)
            tv.setCompoundDrawablesRelativeWithIntrinsicBounds(R.mipmap.ic_confirm,
                    0,0,0);

        snackbar.show();
    }

    /**
     * Separa string e pega informações importantes
     * @param string Texto a ser splitado
     * @param index Qual posição desejamos retornar
     * @param separator Separador
     */
    public static String splitString(String string, int index, String separator) {
        String[] partes = string.split(separator);
        System.out.println(Arrays.toString(partes));
        return partes[index];
    }
}

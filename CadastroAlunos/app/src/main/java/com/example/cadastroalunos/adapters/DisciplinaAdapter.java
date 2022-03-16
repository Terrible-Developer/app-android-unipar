package com.example.cadastroalunos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.model.Disciplina;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DisciplinaAdapter extends RecyclerView.Adapter<DisciplinaAdapter.DisciplinaViewHolder> {

    private List<Disciplina> listaDisciplina;
    private Context context;

    public DisciplinaAdapter(List<Disciplina> listaDisciplina, Context context) {
        this.listaDisciplina = listaDisciplina;
        this.context = context;
    }

    public static class DisciplinaViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edDisciplinaNome;

        public DisciplinaViewHolder(@NonNull View itemView) {
            super(itemView);

            edDisciplinaNome = (TextInputEditText)itemView.findViewById(R.id.edDisciplinaNome);

        }
    }

    @Override
    public DisciplinaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_disciplina, parent, false);

        DisciplinaAdapter.DisciplinaViewHolder viewHolder = new DisciplinaViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DisciplinaViewHolder holder, int position) {
        Disciplina disciplina = listaDisciplina.get(position);

        holder.edDisciplinaNome.setText(String.valueOf(disciplina.getNome()));

    }

    @Override
    public int getItemCount() {
        return listaDisciplina.size();
    }


}

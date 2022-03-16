package com.example.cadastroalunos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.model.Turma;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TurmaAdapter extends RecyclerView.Adapter<TurmaAdapter.TurmaViewHolder> {

    private Context context;

    private List<Turma> listaTurmas;

    public TurmaAdapter(List<Turma> listaTurmas, Context context) {
        this.listaTurmas = listaTurmas;
        this.context = context;
    }

    public static class TurmaViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edTurmaID;
        TextInputEditText edTurmaProfessor;
        TextInputEditText edTurmaCurso;
        TextInputEditText edTurmaRegime;
        TextInputEditText edTurmaPeriodo;

        public TurmaViewHolder(@NonNull View itemView) {
            super(itemView);

            edTurmaID = itemView.findViewById(R.id.edTurmaID);
            edTurmaCurso = itemView.findViewById(R.id.edTurmaCurso);
            edTurmaProfessor = itemView.findViewById(R.id.edTurmaProfessor);
            edTurmaRegime = itemView.findViewById(R.id.edTurmaRegime);
            edTurmaPeriodo = itemView.findViewById(R.id.edTurmaPeriodo);

        }
    }

    @Override
    public TurmaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_turma, parent, false);

        TurmaAdapter.TurmaViewHolder viewHolder = new TurmaViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TurmaViewHolder holder, int position) {
        Turma turma = listaTurmas.get(position);

        holder.edTurmaID.setText(String.valueOf(turma.getId()));
        holder.edTurmaProfessor.setText(String.valueOf(turma.getProfessor().getNome()));
        holder.edTurmaRegime.setText(String.valueOf(turma.getRegime()));
        holder.edTurmaCurso.setText(String.valueOf(turma.getCurso()));
        holder.edTurmaPeriodo.setText(String.valueOf(turma.getPeriodo()));

    }

    @Override
    public int getItemCount() {
        return listaTurmas.size();
    }


}

package com.example.cadastroalunos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.model.Professor;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ProfessorAdapter extends RecyclerView.Adapter<ProfessorAdapter.ProfessorViewHolder> {

    private List<Professor> listaProfessores;
    private Context context;

    public ProfessorAdapter(List<Professor> listaProfessores, Context context) {
        this.listaProfessores = listaProfessores;
        this.context = context;
    }

    public static class ProfessorViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edNomeProfessor;
        TextInputEditText edCpfProfessor;
        TextInputEditText edDtNasc;
        TextInputEditText edCursoProfessor;
        TextInputEditText edPeriodoProfessor;
        TextInputEditText edRegimeProfessor;

        public ProfessorViewHolder(@NonNull View itemView) {
            super(itemView);

            edNomeProfessor = (TextInputEditText)itemView.findViewById(R.id.edNomeProfessor);
            edCpfProfessor =  (TextInputEditText)itemView.findViewById(R.id.edCpfProfessor);
            edCursoProfessor = (TextInputEditText)itemView.findViewById(R.id.edCursoProfessor);
            edDtNasc = (TextInputEditText)itemView.findViewById(R.id.edDtNascProfessor);
            edPeriodoProfessor = (TextInputEditText)itemView.findViewById(R.id.edPeriodoProfessor);
            edRegimeProfessor = (TextInputEditText)itemView.findViewById(R.id.edRegimeProfessor);
        }
    }

    @Override
    public ProfessorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_professor, parent, false);

        ProfessorAdapter.ProfessorViewHolder viewHolder = new ProfessorViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfessorViewHolder holder, int position) {
        Professor professor = listaProfessores.get(position);

        holder.edCpfProfessor.setText(professor.getCpf());
        holder.edNomeProfessor.setText(professor.getNome());
        holder.edDtNasc.setText(professor.getDtNasc());
        holder.edCursoProfessor.setText(professor.getCurso());
        holder.edPeriodoProfessor.setText(professor.getPeriodo());
        holder.edRegimeProfessor.setText(professor.getRegime());

    }

    @Override
    public int getItemCount() {
        return listaProfessores.size();
    }


}

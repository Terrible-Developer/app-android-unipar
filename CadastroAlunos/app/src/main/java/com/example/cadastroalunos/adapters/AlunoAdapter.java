package com.example.cadastroalunos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.model.Aluno;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AlunoAdapter extends RecyclerView.Adapter<AlunoAdapter.AlunoViewHolder> {

    private List<Aluno> listaAlunos;
    private Context context;

    public AlunoAdapter(List<Aluno> listaAlunos, Context context) {
        this.listaAlunos = listaAlunos;
        this.context = context;
    }

    public static class AlunoViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edRaAluno;
        TextInputEditText edNomeAluno;
        TextInputEditText edCpfAluno;
        TextInputEditText edCursoAluno;
        TextInputEditText edRegimeAluno;
        TextInputEditText edPeriodoAluno;
        TextInputEditText edDtMatriculaAluno;
        TextInputEditText edDtNascAluno;

        public AlunoViewHolder(@NonNull View itemView) {
            super(itemView);

            edRaAluno = (TextInputEditText)itemView.findViewById(R.id.edRaAluno);
            edNomeAluno = (TextInputEditText)itemView.findViewById(R.id.edNomeAluno);
            edCpfAluno =  (TextInputEditText)itemView.findViewById(R.id.edCpfAluno);
            edCursoAluno = (TextInputEditText)itemView.findViewById(R.id.edCursoAluno);
            edRegimeAluno = (TextInputEditText)itemView.findViewById(R.id.edRegimeAluno);
            edPeriodoAluno = (TextInputEditText)itemView.findViewById(R.id.edPeriodoAluno);
            edDtMatriculaAluno = (TextInputEditText)itemView.findViewById(R.id.edDtMatriculaAluno);
            edDtNascAluno = (TextInputEditText)itemView.findViewById(R.id.edDtNascAluno);

        }
    }

    @Override
    public AlunoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_aluno, parent, false);

        AlunoAdapter.AlunoViewHolder viewHolder = new AlunoViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlunoViewHolder holder, int position) {
        Aluno aluno = listaAlunos.get(position);

        holder.edRaAluno.setText(String.valueOf(aluno.getRa()));
        holder.edCpfAluno.setText(aluno.getCpf());
        holder.edNomeAluno.setText(aluno.getNome());
        holder.edRegimeAluno.setText(aluno.getRegime());
        holder.edCursoAluno.setText(aluno.getCurso());
        holder.edPeriodoAluno.setText(aluno.getPeriodo());
        holder.edDtMatriculaAluno.setText(aluno.getDtMatricula());
        holder.edDtNascAluno.setText(aluno.getDtNasc());

    }

    @Override
    public int getItemCount() {
        return listaAlunos.size();
    }


}

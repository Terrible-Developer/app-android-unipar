package com.example.cadastroalunos.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cadastroalunos.R;
import com.example.cadastroalunos.activities.SituacaoAlunoActivity;
import com.example.cadastroalunos.activities.cadastros.CadastroFrequenciaActivity;
import com.example.cadastroalunos.activities.cadastros.CadastroNotaActivity;
import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.dao.ProfessorDAO;
import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.Professor;
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
        LinearLayout lnAlunos;
        TextView tvTurma;

        public TurmaViewHolder(@NonNull View itemView) {
            super(itemView);

            edTurmaID = itemView.findViewById(R.id.edTurmaID);
            edTurmaCurso = itemView.findViewById(R.id.edTurmaCurso);
            edTurmaProfessor = itemView.findViewById(R.id.edTurmaProfessor);
            edTurmaRegime = itemView.findViewById(R.id.edTurmaRegime);
            edTurmaPeriodo = itemView.findViewById(R.id.edTurmaPeriodo);
            lnAlunos = itemView.findViewById(R.id.lnAlunos);
            tvTurma = itemView.findViewById(R.id.tvTurma);
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

        Professor professor = ProfessorDAO.getProfessor(turma.getIdProfessor());

        holder.edTurmaProfessor.setText(String.valueOf(professor.getNome()));
        holder.edTurmaRegime.setText(String.valueOf(turma.getRegime()));
        holder.edTurmaCurso.setText(String.valueOf(turma.getCurso()));
        holder.edTurmaPeriodo.setText(String.valueOf(turma.getPeriodo()));

        Log.e("Alunos na turma", "Tamanho da lista: "+turma);

        // Mostra lista de alunos
        if (turma.getAlunos().equals(""))
            return;

        holder.tvTurma.setVisibility(View.VISIBLE);

        // Separar o string em ids
        String[] idsAlunos = turma.getAlunos().split(",");
        for (String idsAluno : idsAlunos) {
            int id = Integer.parseInt(idsAluno);
            Aluno aluno = AlunoDAO.getAluno(id);

            if (aluno != null) {
                // Cria coluna
                LinearLayout column = new LinearLayout(context);
                column.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams paramsColumn = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                column.setLayoutParams(paramsColumn);

                // Cria linha
                LinearLayout line = new LinearLayout(context);
                column.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams paramsLine = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                paramsLine.setMargins(20, 8, 0, 8);
                line.setLayoutParams(paramsLine);

                // Cria o nome do aluno
                /*LinearLayout lineHeaders = new LinearLayout(context);
                lineHeaders.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams headersParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                headersParams.setMargins(5, 0, 0,5);
                lineHeaders.setLayoutParams(headersParams);*/

                // Cria headers da tabela
                // Nome
                /*TextView nomeHeader = new TextView(context);
                LinearLayout.LayoutParams nomeHeaderParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                nomeHeaderParams.weight = 1;
                nomeHeader.setLayoutParams(nomeHeaderParams);
                nomeHeader.setText("Nome");
                nomeHeader.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                lineHeaders.addView(nomeHeader);*/

                // Nota 1
                /*TextView notasHeader = new TextView(context);
                LinearLayout.LayoutParams notasHeaderParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                notasHeaderParams.weight = 1;
                notasHeader.setLayoutParams(nomeHeaderParams);
                notasHeader.setText("Nota 1");
                notasHeader.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                lineHeaders.addView(notasHeader);

                column.addView(lineHeaders);*/

                // Cria o nome
                TextView nome = new TextView(context);
                LinearLayout.LayoutParams nomeParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                nomeParams.weight = 1;
                nome.setLayoutParams(nomeParams);
                nome.setText(aluno.getRa()+" - "+aluno.getNome());
                nome.setTextColor(Color.BLACK);
                line.addView(nome);

                // Add evento de visualizar notas do aluno
                nome.setOnClickListener(view -> {
                    Log.e("sas", "aluno:"+aluno);

                    // Abrir tela de cadastro da nota do aluno
                    Intent intent = new Intent(context, SituacaoAlunoActivity.class);
                    intent.putExtra("alunoID", ""+aluno.getId());
                    intent.putExtra("turmaID", ""+aluno.getIdTurma());
                    Turma turm = TurmaDAO.getTurma(aluno.getIdTurma());
                    if (turm != null) {
                        Professor prof = ProfessorDAO.getProfessor(turm.getIdProfessor());
                        if (prof != null) {
                            intent.putExtra("disciplina", "" + prof.getDisciplina());
                        }
                    }
                    context.startActivity(intent);
                });

                // Bot??o de cadastrar nota
                ImageView cadastrarNota = new ImageView(context);
                LinearLayout.LayoutParams cadastrarNotaParams = new LinearLayout.LayoutParams(60, 50);
                cadastrarNotaParams.setMargins(0, 0, 0, 0);
                cadastrarNota.setLayoutParams(cadastrarNotaParams);
                cadastrarNota.setPadding(0, 0, 0, 0);
                cadastrarNota.setClickable(true);
                cadastrarNota.setImageResource(R.drawable.ic_grade);
                cadastrarNota.setMinimumWidth(16);
                line.addView(cadastrarNota);

                // Bot??o de cadastrar frequencia
                ImageView cadastrarFrequencia = new ImageView(context);
                LinearLayout.LayoutParams cadastrarFrequenciaParams = new LinearLayout.LayoutParams(60, 50);
                cadastrarNotaParams.setMargins(0, 0, 0, 0);
                cadastrarFrequencia.setLayoutParams(cadastrarFrequenciaParams);
                cadastrarFrequencia.setPadding(0, 0, 0, 0);
                cadastrarFrequencia.setClickable(true);
                cadastrarFrequencia.setImageResource(R.drawable.attend);
                cadastrarFrequencia.setMinimumWidth(20);
                line.addView(cadastrarFrequencia);

                // Adiciona evento ao clicar ao ImageView cadastrar nota
                cadastrarNota.setOnClickListener(view -> {
                    Log.e("sas", "aluno:"+aluno);

                    // Abrir tela de cadastro da nota do aluno
                    Intent intent = new Intent(context, CadastroNotaActivity.class);
                    intent.putExtra("alunoID", ""+aluno.getId());
                    intent.putExtra("turmaID", ""+aluno.getIdTurma());
                    context.startActivity(intent);
                });

                // Adiciona evento ao clicar ao ImageView cadastrar frequencia
                cadastrarFrequencia.setOnClickListener(view -> {
                    Log.e("sas", "aluno:"+aluno);

                    // Abrir tela de cadastro da nota do aluno
                    Intent intent = new Intent(context, CadastroFrequenciaActivity.class);
                    intent.putExtra("alunoID", ""+aluno.getId());
                    intent.putExtra("turmaID", ""+aluno.getIdTurma());
                    Turma turmaObj = TurmaDAO.getTurma(aluno.getIdTurma());
                    if (turmaObj != null) {
                        Professor professorObj = ProfessorDAO.getProfessor(turmaObj.getIdProfessor());
                        if (professorObj != null) {
                            intent.putExtra("disciplina", ""+professorObj.getDisciplina());
                        }
                    }

                    context.startActivity(intent);
                });

                // Adiciona a coluna ?? linha
                column.addView(line);

                // Adiciona a coluna ?? lista de alunos
                holder.lnAlunos.addView(column);
            }
        }
    }

    @Override
    public int getItemCount() {
        return listaTurmas.size();
    }


}

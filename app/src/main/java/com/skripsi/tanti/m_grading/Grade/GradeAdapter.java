package com.skripsi.tanti.m_grading.Grade;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skripsi.tanti.m_grading.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GradeAdapter extends RecyclerView.Adapter {

    private ArrayList<GradeModel> gradeList;

    public GradeAdapter(ArrayList<GradeModel> gradeList) {
        this.gradeList = gradeList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.grade_item, parent, false);

        return new RowViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;

            rowViewHolder.txtMapel.setText(gradeList.get(position).getNama_pelajaran() + "");
            rowViewHolder.txtAngkaPengetahuan.setText(gradeList.get(position).getAngka_pengetahuan());
            rowViewHolder.txtPredikatPengetahuan.setText(gradeList.get(position).getPredikat_pengetahuan() + "");
            rowViewHolder.txtAngkaKeterampilan.setText(gradeList.get(position).getAngka_keterampilan() + "");
            rowViewHolder.txtPredikatKeterampilan.setText(gradeList.get(position).getPredikat_keterampilan() + "");
    }

    @Override
    public int getItemCount() {
        return gradeList.size();
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {
        TextView txtMapel;
        TextView txtAngkaPengetahuan;
        TextView txtPredikatPengetahuan;
        TextView txtAngkaKeterampilan;
        TextView txtPredikatKeterampilan;

        RowViewHolder(View itemView) {
            super(itemView);
            txtMapel = itemView.findViewById(R.id.txtMapel);
            txtAngkaPengetahuan = itemView.findViewById(R.id.txtAngkaPengetahuan);
            txtPredikatPengetahuan = itemView.findViewById(R.id.txtPredikatPengetahuan);
            txtAngkaKeterampilan = itemView.findViewById(R.id.txtAngkaKeterampilan);
            txtPredikatKeterampilan = itemView.findViewById(R.id.txtPredikatKeterampilan);
        }
    }
}

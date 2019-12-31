package com.skripsi.tanti.m_grading.Schedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skripsi.tanti.m_grading.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.MahasiswaViewHolder> {


    private ArrayList<ScheduleModel> dataList;

    public ScheduleAdapter(ArrayList<ScheduleModel> dataList) {
        this.dataList = dataList;
    }

    @Override
    public MahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.schedule_item, parent, false);
        return new MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MahasiswaViewHolder holder, int position) {
        holder.txtJam.setText(dataList.get(position).getJam());
        holder.txtMataPelajaran.setText(dataList.get(position).getMata_pelajaran());
        holder.txtGuru.setText(dataList.get(position).getGuru());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class MahasiswaViewHolder extends RecyclerView.ViewHolder{
        private TextView txtJam, txtMataPelajaran, txtGuru;

        public MahasiswaViewHolder(View itemView) {
            super(itemView);
            txtJam = (TextView) itemView.findViewById(R.id.txt_jam);
            txtMataPelajaran = (TextView) itemView.findViewById(R.id.txt_mata_pelajaran);
            txtGuru = (TextView) itemView.findViewById(R.id.txt_guru);
        }
    }

}

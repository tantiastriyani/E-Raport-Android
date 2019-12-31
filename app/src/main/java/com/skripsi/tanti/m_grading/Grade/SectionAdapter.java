package com.skripsi.tanti.m_grading.Grade;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skripsi.tanti.m_grading.R;
import com.skripsi.tanti.m_grading.Schedule.ScheduleAdapter;
import com.skripsi.tanti.m_grading.Schedule.ScheduleDayModel;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ScheduleDayViewHolder> {


    private ArrayList<SectionModel> dataList;

    private Context mContext;

    public SectionAdapter(ArrayList<SectionModel> dataList) {
        this.dataList = dataList;
    }

    @Override
    public ScheduleDayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.grade_section, parent, false);
        return new ScheduleDayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScheduleDayViewHolder holder, int position) {
        holder.txtMuatan.setText(dataList.get(position).getMuatan());

        ArrayList gradeItems = dataList.get(position).getAllItemsInSection();


        GradeAdapter itemListDataAdapter = new GradeAdapter(gradeItems);

        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        holder.recyclerView.setAdapter(itemListDataAdapter);

    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ScheduleDayViewHolder extends RecyclerView.ViewHolder{
        private TextView txtMuatan;
        private RecyclerView recyclerView;

        public ScheduleDayViewHolder(View itemView) {
            super(itemView);
            txtMuatan = (TextView) itemView.findViewById(R.id.muatan);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_detail_grade);
        }
    }

}

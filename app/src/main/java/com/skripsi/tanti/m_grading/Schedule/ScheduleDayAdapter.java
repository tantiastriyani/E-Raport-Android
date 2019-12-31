package com.skripsi.tanti.m_grading.Schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skripsi.tanti.m_grading.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ScheduleDayAdapter extends RecyclerView.Adapter<ScheduleDayAdapter.ScheduleDayViewHolder> {


    private ArrayList<ScheduleDayModel> dataList;

    private Context mContext;

    public ScheduleDayAdapter(ArrayList<ScheduleDayModel> dataList) {
        this.dataList = dataList;
    }

    @Override
    public ScheduleDayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.schedule_section, parent, false);
        return new ScheduleDayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScheduleDayViewHolder holder, int position) {
        holder.txtDay.setText(dataList.get(position).getDay());

        ArrayList scheduleItems = dataList.get(position).getAllItemsInSection();


        ScheduleAdapter itemListDataAdapter = new ScheduleAdapter(scheduleItems);

        holder.recyclerViewSchedule.setHasFixedSize(true);
        holder.recyclerViewSchedule.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        holder.recyclerViewSchedule.setAdapter(itemListDataAdapter);

    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ScheduleDayViewHolder extends RecyclerView.ViewHolder{
        private TextView txtDay;
        private RecyclerView recyclerViewSchedule;

        public ScheduleDayViewHolder(View itemView) {
            super(itemView);
            txtDay = (TextView) itemView.findViewById(R.id.day);
            recyclerViewSchedule = (RecyclerView) itemView.findViewById(R.id.recycler_schedule);
        }
    }

}

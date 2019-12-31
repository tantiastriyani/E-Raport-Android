package com.skripsi.tanti.m_grading.Schedule;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ScheduleDayModel {
    @SerializedName("day")
    String day;

    ArrayList<ScheduleModel> allItemsInSection;

    public ScheduleDayModel(){
    }

    public ScheduleDayModel(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public ArrayList<ScheduleModel> getAllItemsInSection() {
        return allItemsInSection;
    }

    public void setAllItemsInSection(ArrayList<ScheduleModel> allItemsInSection) {
        this.allItemsInSection = allItemsInSection;
    }
}

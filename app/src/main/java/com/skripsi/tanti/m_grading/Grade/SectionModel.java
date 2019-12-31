package com.skripsi.tanti.m_grading.Grade;

import com.google.gson.annotations.SerializedName;
import com.skripsi.tanti.m_grading.Schedule.ScheduleModel;

import java.util.ArrayList;

public class SectionModel {
    @SerializedName("muatan")
    String muatan;

    ArrayList<GradeModel> allItemsInSection;

    public SectionModel(){
    }

    public SectionModel(String muatan) {
        this.muatan = muatan;
    }

    public String getMuatan() {
        return muatan;
    }

    public void setMuatan(String muatan) {
        this.muatan = muatan;
    }

    public ArrayList<GradeModel> getAllItemsInSection() {
        return allItemsInSection;
    }

    public void setAllItemsInSection(ArrayList<GradeModel> allItemsInSection) {
        this.allItemsInSection = allItemsInSection;
    }
}

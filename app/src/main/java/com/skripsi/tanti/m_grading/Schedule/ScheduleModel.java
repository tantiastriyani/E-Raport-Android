package com.skripsi.tanti.m_grading.Schedule;

import com.google.gson.annotations.SerializedName;

public class ScheduleModel {
    @SerializedName("jam")
    String jam;
    @SerializedName("mata_pelajaran")
    String mata_pelajaran;
    @SerializedName("guru")
    String guru;

    public ScheduleModel() {
    }

    public ScheduleModel(String jam, String mata_pelajaran, String guru) {
        this.jam = jam;
        this.mata_pelajaran = mata_pelajaran;
        this.guru = guru;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }



    public String getMata_pelajaran() {
        return mata_pelajaran;
    }

    public void setMata_pelajaran(String mata_pelajaran) {
        this.mata_pelajaran = mata_pelajaran;
    }

    public String getGuru() {
        return guru;
    }

    public void setGuru(String guru) {
        this.guru = guru;
    }
}

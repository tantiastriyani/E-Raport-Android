package com.skripsi.tanti.m_grading.Grade;

import com.google.gson.annotations.SerializedName;

public class GradeModel {
    @SerializedName("nama_pelajaran")
    String nama_pelajaran;
    @SerializedName("angka_pengetahuan")
    String angka_pengetahuan;
    @SerializedName("predikat_pengetahuan")
    String predikat_pengetahuan;
    @SerializedName("angka_keterampilan")
    String angka_keterampilan;
    @SerializedName("predikat_keterampilan")
    String predikat_keterampilan;

    public GradeModel() {
    }

    public GradeModel(String nama_pelajaran, String angka_pengetahuan, String predikat_pengetahuan,String angka_keterampilan,String predikat_keterampilan) {
        this.nama_pelajaran = nama_pelajaran;
        this.angka_pengetahuan = angka_pengetahuan;
        this.predikat_pengetahuan = predikat_pengetahuan;
        this.angka_keterampilan = angka_keterampilan;
        this.predikat_keterampilan = predikat_keterampilan;
    }

    public String getNama_pelajaran() {
        return nama_pelajaran;
    }

    public void setNama_pelajaran(String nama_pelajaran) {
        this.nama_pelajaran = nama_pelajaran;
    }

    public String getAngka_pengetahuan() {
        return angka_pengetahuan;
    }

    public void setAngka_pengetahuan(String angka_pengetahuan) {
        this.angka_pengetahuan = angka_pengetahuan;
    }

    public String getPredikat_pengetahuan() {
        return predikat_pengetahuan;
    }

    public void setPredikat_pengetahuan(String predikat_pengetahuan) {
        this.predikat_pengetahuan = predikat_pengetahuan;
    }

    public String getAngka_keterampilan() {
        return angka_keterampilan;
    }

    public void setAngka_keterampilan(String angka_keterampilan) {
        this.angka_keterampilan = angka_keterampilan;
    }

    public String getPredikat_keterampilan() {
        return predikat_keterampilan;
    }

    public void setPredikat_keterampilan(String predikat_keterampilan) {
        this.predikat_keterampilan = predikat_keterampilan;
    }
}

package com.skripsi.tanti.m_grading.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

/**
 * Created by user on 18-Aug-17.
 */

public class UserSession {

//    private final static UserSession instance;

    static {
//        instance = new UserSession();
    }

//    public static UserSession getInstance() {
//        return instance;
//    }

    private final SharedPreferences appPreference;

    public UserSession(Context context) {
        appPreference = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setSession(String key, String value) {
        SharedPreferences.Editor editor = appPreference.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void clearSession() {
        SharedPreferences.Editor editor = appPreference.edit();
        editor.clear();
        editor.apply();
        editor.commit();
    }

    public String getSession(String key) {
        String value = appPreference.getString(key, "");
        if (TextUtils.isEmpty(value)) {
            return "";
        }
        return value;
    }

    public void setIsLogin(boolean stat) {
        SharedPreferences.Editor editor = appPreference.edit();
        editor.putBoolean("IS_LOGIN", stat);
        editor.apply();
    }

    public boolean getIsLogin() {
        boolean isLogin = appPreference.getBoolean("IS_LOGIN", false);
        return isLogin;
    }

    public void setKodeSiswa(String kodeSiswa) {
        setSession("kode_siswa", kodeSiswa);
    }

    public String getKodeSiswa(){
        return getSession("kode_siswa");
    }

    public void setKodeKelas(String kodeKelas) {
        setSession("kode_kelas", kodeKelas);
    }

    public String getKodeKelas(){
        return getSession("kode_kelas");
    }

    public void setIdUsers(String idUsers) {
        setSession("id_users", idUsers);
    }

    public String getIdUsers(){
        return getSession("id_users");
    }

    public void setNIS(String NIS) {
        setSession("nis", NIS);
    }

    public String getNIS(){
        return getSession("nis");
    }

    public void setNamaSiswa(String namaSiswa) {
        setSession("nama_siswa", namaSiswa);
    }

    public String getNamaSiswa(){
        return getSession("nama_siswa");
    }

    public void setKelasSiswa(String kelasSiswa) {
        setSession("kelas_siswa", kelasSiswa);
    }

    public String getKelasSiswa(){
        return getSession("kelas_siswa");
    }

    public void setSemester(String semester) {
        setSession("semester", semester);
    }

    public String getSemester(){
        return getSession("semester");
    }

    public void setAlamat(String alamat) {
        setSession("alamat", alamat);
    }

    public String getAlamat(){
        return getSession("alamat");
    }

    public void setNoHp(String noHp) {
        setSession("noHp", noHp);
    }

    public String getNoHp(){
        return getSession("noHp");
    }

}



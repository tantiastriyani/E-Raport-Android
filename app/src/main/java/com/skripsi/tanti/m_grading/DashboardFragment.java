package com.skripsi.tanti.m_grading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skripsi.tanti.m_grading.app.UserSession;

public class DashboardFragment extends Fragment {

    private TextView txtUserName, txtUserClass;
    private UserSession userSession;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard_fragment, container, false);

        txtUserClass = view.findViewById(R.id.userClass);
        txtUserName = view.findViewById(R.id.userName);
        userSession = new UserSession(getContext());

        txtUserName.setText("Nama : "+userSession.getNamaSiswa());
        txtUserClass.setText("Kelas : "+userSession.getKelasSiswa());

        return view;
    }
}

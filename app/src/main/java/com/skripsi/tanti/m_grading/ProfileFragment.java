package com.skripsi.tanti.m_grading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skripsi.tanti.m_grading.app.UserSession;

public class ProfileFragment extends Fragment {

    private TextView txtName, txtNIS, txtAddress, txtPhoneNumber;
    private UserSession userSession;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        txtName = view.findViewById(R.id.txdNama);
        txtNIS = view.findViewById(R.id.txdNIS);
        txtAddress = view.findViewById(R.id.txdAlamat);
        txtPhoneNumber = view.findViewById(R.id.txdNoHp);

        userSession = new UserSession(getContext());

        txtName.setText(userSession.getNamaSiswa());
        txtNIS.setText(userSession.getNIS());
        txtAddress.setText(userSession.getAlamat());
        txtPhoneNumber.setText(userSession.getNoHp());

        return view;
    }
}

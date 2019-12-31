package com.skripsi.tanti.m_grading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.skripsi.tanti.m_grading.app.AppController;
import com.skripsi.tanti.m_grading.app.Server;
import com.skripsi.tanti.m_grading.app.UserSession;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AttendanceFragment extends Fragment {
    int success;

    private String url = Server.URL + "getAttendance.php";


    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";


    private TextView txtUserName, txtUserSemester, txtPresence, txtSick, txtAbsent, txtPermit;
    private UserSession userSession;

    String tag_json_obj = "json_obj_req";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.attendance_fragment, container, false);
        txtUserSemester = view.findViewById(R.id.semester);
        txtUserName = view.findViewById(R.id.userName);
        txtPresence = view.findViewById(R.id.dataPresence);
        txtSick = view.findViewById(R.id.dataSick);
        txtAbsent = view.findViewById(R.id.dataAbsent);
        txtPermit = view.findViewById(R.id.dataPermit);
        userSession = new UserSession(getContext());

        txtUserName.setText("Nama : "+userSession.getNamaSiswa());
        txtUserSemester.setText("Semester : "+userSession.getSemester());

        getAttendance(userSession.getKodeSiswa(), userSession.getSemester());

        return view;
    }

    private void getAttendance(final String kode_siswa, final String semester) {

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e("Attendance", " Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);
                    // Check for error node in json
                    if (success == 1) {
                        Log.e("Successfully Login!", jObj.toString());
                        txtPresence.setText(jObj.getString("presence")+" times");
                        txtAbsent.setText(jObj.getString("absent")+" times");
                        txtSick.setText(jObj.getString("sick")+" times");
                        txtPermit.setText(jObj.getString("permit")+" times");
                    } else {
                        Toast.makeText(getContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Attendance", " Error: " + error.getMessage());
                Toast.makeText(getContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();


            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("kode_siswa", kode_siswa);
                params.put("semester", semester);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }
}

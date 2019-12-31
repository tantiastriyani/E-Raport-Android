package com.skripsi.tanti.m_grading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.skripsi.tanti.m_grading.Grade.GradeAdapter;
import com.skripsi.tanti.m_grading.Grade.GradeModel;
import com.skripsi.tanti.m_grading.Grade.SectionAdapter;
import com.skripsi.tanti.m_grading.Grade.SectionModel;
import com.skripsi.tanti.m_grading.Schedule.ScheduleDayAdapter;
import com.skripsi.tanti.m_grading.Schedule.ScheduleDayModel;
import com.skripsi.tanti.m_grading.Schedule.ScheduleModel;
import com.skripsi.tanti.m_grading.app.AppController;
import com.skripsi.tanti.m_grading.app.Server;
import com.skripsi.tanti.m_grading.app.UserSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GradeFragment extends Fragment {
    int success;

    private String url = Server.URL + "getRaport.php";

    private String url_grading = Server.URL + "getGrading.php";


    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";
    private RecyclerView recyclerView;
    private SectionAdapter adapter;
    private ArrayList<SectionModel> sectionArrayList;

    private TextView txtSpiritual, txtSosial, txtCatatan, txtName, txtClass, txtSemester;

    private UserSession userSession;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.grade_fragment, container, false);

        txtSpiritual = (TextView) view.findViewById(R.id.txtSpiritual);
        txtSosial = (TextView) view.findViewById(R.id.txtSosial);
        txtCatatan = (TextView) view.findViewById(R.id.txtCatatan);

        txtName = (TextView) view.findViewById(R.id.name);
        txtClass = (TextView) view.findViewById(R.id.Class);
        txtSemester = (TextView) view.findViewById(R.id.semester);

        userSession = new UserSession(getContext());

        txtName.setText("Nama : "+userSession.getNamaSiswa());
        txtClass.setText("Kelas : "+userSession.getKelasSiswa());
        txtSemester.setText("Semester : "+userSession.getSemester());

        getRaport(userSession.getKodeSiswa(), userSession.getSemester());

        sectionArrayList = new ArrayList<>();

        setSection(userSession.getKodeSiswa(), userSession.getSemester());

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_grade);

        adapter = new SectionAdapter(sectionArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        return view;
    }

    private void getRaport(final String kode_siswa, final String semester) {

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e("Raport", " Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);
                    // Check for error node in json
                    if (success == 1) {
                        Log.e("Successfully Read!", jObj.toString());
                        JSONArray data = jObj.getJSONArray("raport");

                        for (int j = 0; j<data.length(); j++){
                            JSONObject objectData = data.getJSONObject(j);

                            txtSpiritual.setText(objectData.getString("spiritual"));
                            txtSosial.setText(objectData.getString("sosial"));
                            txtCatatan.setText(objectData.getString("catatan"));

                        }
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

    void setSection(final String kode_siswa,final String semester){
        String[] muatan = {"Muatan Nasional","Muatan Kewilayahan", "Muatan Perminatan Kejuruan"};

        for (int i = 0; i<3; i++){
            SectionModel sm = new SectionModel();

            sm.setMuatan(muatan[i]);

            final ArrayList<GradeModel> singleItem = new ArrayList<GradeModel>();
            final String mtn = muatan[i];

            StringRequest strReq = new StringRequest(Request.Method.POST, url_grading, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Log.e("Grading", " Response: " + response.toString());

                    try {
                        JSONObject jObj = new JSONObject(response);
                        success = jObj.getInt(TAG_SUCCESS);
                        // Check for error node in json
                        if (success == 1) {
                            Log.e("Successfully Login!", jObj.toString());
                            JSONArray data = jObj.getJSONArray("raport");

                            for (int j = 0; j<data.length(); j++){
                                JSONObject objectData = data.getJSONObject(j);
                                if (objectData.getString("keterangan").equals(mtn)){
                                    singleItem.add(new GradeModel(objectData.getString("nama_pelajaran"), objectData.getString("angka_pengetahuan"), objectData.getString("predikat_pengetahuan"), objectData.getString("angka_keterampilan"), objectData.getString("predikat_keterampilan")));
                                }

                            }
                            adapter.notifyDataSetChanged();
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
                    Log.e("Grade", " Error: " + error.getMessage());
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

            sm.setAllItemsInSection(singleItem);

            sectionArrayList.add(sm);
        }

    }
}

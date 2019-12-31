package com.skripsi.tanti.m_grading;

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

public class ScheduleFragment extends Fragment {

    int success;

    private String url = Server.URL + "getSchedule.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";
    private RecyclerView recyclerView;
    private ScheduleDayAdapter adapter;
    private ArrayList<ScheduleDayModel> scheduleArrayList;
    private UserSession userSession;
    private TextView txtClass, txtSemester;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.schedule_fragment, container, false);

        userSession = new UserSession(getContext());

        scheduleArrayList = new ArrayList<>();

        addData(userSession.getKodeKelas());

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_day);

        adapter = new ScheduleDayAdapter(scheduleArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        txtClass = view.findViewById(R.id.Class);

        txtSemester = view.findViewById(R.id.semester);

        txtClass.setText("Kelas : "+userSession.getKelasSiswa());

        txtSemester.setText("Semester : "+userSession.getSemester());

        return view;

    }

    void addData(final String kode_kelas){
        String[] day = {"Senin","Selasa", "Rabu", "Kamis", "Jumat", "Sabtu"};

        for (int i = 0; i<6; i++){
            ScheduleDayModel sm = new ScheduleDayModel();

            sm.setDay(day[i]);

            final ArrayList<ScheduleModel> singleItem = new ArrayList<ScheduleModel>();
            final String hari = day[i];

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
                            JSONArray data = jObj.getJSONArray("jadwal");

                            for (int j = 0; j<data.length(); j++){
                                JSONObject objectData = data.getJSONObject(j);
                                if (objectData.getString("nama_hari").equals(hari)){
                                    singleItem.add(new ScheduleModel(objectData.getString("jam_mulai")+" - "+objectData.getString("jam_selesai"), objectData.getString("nama_pelajaran"), objectData.getString("nama_guru")));
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
                    Log.e("Attendance", " Error: " + error.getMessage());
                    Toast.makeText(getContext(),
                            error.getMessage(), Toast.LENGTH_LONG).show();


                }
            }) {

                @Override
                protected Map<String, String> getParams() {
                    // Posting parameters to login url
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("kode_kelas", kode_kelas);

                    return params;
                }

            };

            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);

            sm.setAllItemsInSection(singleItem);

            scheduleArrayList.add(sm);
        }

    }
}

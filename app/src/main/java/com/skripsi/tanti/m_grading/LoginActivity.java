package com.skripsi.tanti.m_grading;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.skripsi.tanti.m_grading.app.AppController;
import com.skripsi.tanti.m_grading.app.Server;
import com.skripsi.tanti.m_grading.app.UserSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    ProgressDialog pDialog;
    Button btn_login;
    EditText txt_username, txt_password;


    int success;
    ConnectivityManager conMgr;

    private String url = Server.URL + "login.php";

    private static final String TAG = LoginActivity.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";

    UserSession userSession;
    Boolean session = false;
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }

        btn_login = (Button) findViewById(R.id.btn_login);
        txt_username = (EditText) findViewById(R.id.txt_username);
        txt_password = (EditText) findViewById(R.id.txt_password);

        // Cek session login jika TRUE maka langsung buka MainActivity
        userSession = new UserSession(getApplicationContext());
        session = userSession.getIsLogin();

        if (session) {
            Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
            finish();
            startActivity(intent);
        }


        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String username = txt_username.getText().toString();
                String password = txt_password.getText().toString();

                // mengecek kolom yang kosong
                if (username.trim().length() > 0 && password.trim().length() > 0) {
                    if (conMgr.getActiveNetworkInfo() != null
                            && conMgr.getActiveNetworkInfo().isAvailable()
                            && conMgr.getActiveNetworkInfo().isConnected()) {
                        checkLogin(username, password);
                    } else {
                        Toast.makeText(getApplicationContext() ,"No Internet Connection", Toast.LENGTH_LONG).show();
                    }
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext() ,"Kolom tidak boleh kosong", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private void checkLogin(final String username, final String password) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Logging in ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {

                        Log.e("Successfully Login!", jObj.toString());

                        Toast.makeText(getApplicationContext(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        JSONArray data = jObj.getJSONArray("data");
                        JSONObject objectData = data.getJSONObject(0);

                        // menyimpan login ke session
                        userSession = new UserSession(getApplicationContext());
                        userSession.setIsLogin(true);
                        userSession.setKodeSiswa(objectData.getString("kode_siswa"));
                        userSession.setKodeKelas(objectData.getString("kode_kelas"));
                        userSession.setSemester(objectData.getString("semester"));
                        userSession.setIdUsers(objectData.getString("id_user"));
                        userSession.setNamaSiswa(objectData.getString("nama_siswa"));
                        userSession.setNIS(objectData.getString("nis"));
                        userSession.setAlamat(objectData.getString("alamat"));
                        userSession.setNoHp(objectData.getString("no_tlp"));
                        userSession.setKelasSiswa(objectData.getString("kelas")+" "+objectData.getString("nama_kelas"));

                        // Memanggil main activity
                        Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
                        finish();
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(),
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
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}

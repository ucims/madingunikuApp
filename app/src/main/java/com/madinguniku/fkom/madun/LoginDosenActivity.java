package com.madinguniku.fkom.madun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginDosenActivity extends AppCompatActivity {

    private EditText username_dosen, password_dosen;
    private Button btn_goto_register, btn_login_mhs, btn_login_dosen;
    private static String URL_LOGIN_DOSEN = "http://192.168.56.1/madinguniku2/android/dosen_staf/masuk.php";
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_dosen);

        sessionManager = new SessionManager(this);

        username_dosen = findViewById(R.id.username_dosen);
        password_dosen = findViewById(R.id.password_dosen);

        btn_login_dosen = findViewById(R.id.btn_Login_Dosen); //for login
        btn_login_mhs = findViewById(R.id.btnLoginMhs); // go to login mhs
        btn_goto_register = findViewById(R.id.btn_Daftar); // go to register
        // go to login mhs
        btn_login_mhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginDosenActivity.this, LoginActivity.class ));
            }
        });
        // go to register
        btn_goto_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginDosenActivity.this,RegisterActivity.class ));
            }
        });
        // login to home for dosen
        btn_login_dosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dUser = username_dosen.getText().toString().trim();
                String dPass = password_dosen.getText().toString().trim();

                if (!dUser.isEmpty() || !dPass.isEmpty())
                {
                    Login(dUser, dPass);
                } else {
                    username_dosen.setError("Please insert username");
                    password_dosen.setError("Please insert password");
                }
            }
        });

    }

    private void Login(final String dUser, final String dPass)
    {
        btn_login_dosen.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN_DOSEN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");
                            if (success.equals("1"))
                            {
                                for (int i = 0; i < jsonArray.length(); i++)
                                {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String id_user = object.getString("id_user").trim();
                                    String nama = object.getString("nama").trim();
                                    String level = object.getString("level").trim();
                                    String id_prodi = object.getString("id_prodi").trim();

                                    sessionManager.createSession(id_user, nama, level, id_prodi);
                                    Intent intent = new Intent(LoginDosenActivity.this, HomeDosenActivity.class );
                                    intent.putExtra("id_user", id_user);
                                    intent.putExtra("nama", nama);
                                    intent.putExtra("level", level);
                                    intent.putExtra("id_prodi", id_prodi);

                                    startActivity(intent);
                                }
                            }
                        } catch (JSONException e){
                            e.printStackTrace();
                            btn_login_dosen.setVisibility(View.VISIBLE);
                            Toast.makeText(LoginDosenActivity.this, "Error " + e.toString() , Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        btn_login_dosen.setVisibility(View.VISIBLE);
                        Toast.makeText(LoginDosenActivity.this, "Error " + error.toString() , Toast.LENGTH_LONG).show();
                    }
                })
        {
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> params = new HashMap<>();
                params.put("username", dUser);
                params.put("password", dPass);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

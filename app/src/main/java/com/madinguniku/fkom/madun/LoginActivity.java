package com.madinguniku.fkom.madun;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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

public class LoginActivity extends AppCompatActivity {

    private EditText username, password;
    private Button btn_register, btn_login, btnLoginDosen;
    private ProgressDialog progressDialog;
//    private static String URL_LOGIN = "http://192.168.56.1/madinguniku2/android/login.php";
    private static String URL_LOGIN = "http://uci3026.000webhostapp.com/android/login.php";

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        btn_login = findViewById(R.id.btnLogin);
//        btn_register = findViewById(R.id.btnDaftar);
//        btnLoginDosen = findViewById(R.id.btnLoginDosen);

//        btnLoginDosen.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(LoginActivity.this,LoginDosenActivity.class ));
//            }
//        });



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mUser = username.getText().toString().trim();
                String mPass = password.getText().toString().trim();

                if (!mUser.isEmpty() || !mPass.isEmpty())
                {
                    Login(mUser, mPass);
                } else {
                    username.setError("Please insert username");
                    password.setError("Please insert password");
                }

                //textView7.setText("Hasil " + mUser + " Pass " + mPass);
            }
        });
//        btn_register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(LoginActivity.this,RegisterActivity.class ));
//            }
//        });
    }

    private void Login(final String mUser, final String mPass)
    {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Loading ...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {
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
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.putExtra("nama", nama);
                                    intent.putExtra("id_user", id_user);
                                    intent.putExtra("level", level);
                                    intent.putExtra("id_prodi", id_prodi);

                                    startActivity(intent);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            btn_login.setVisibility(View.VISIBLE);
                            Toast.makeText(LoginActivity.this, "Perhatian " + e.toString() , Toast.LENGTH_LONG).show();
//                            Toast.makeText(LoginActivity.this, "username dan password sala " , Toast.LENGTH_LONG).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        btn_login.setVisibility(View.VISIBLE);
                        Toast.makeText(LoginActivity.this, "Error " + error.toString(), Toast.LENGTH_LONG).show();
//                            Toast.makeText(LoginActivity.this, "username dan password salah!" , Toast.LENGTH_LONG).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", mUser);
                params.put("password", mPass);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}

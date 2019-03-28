package com.madinguniku.fkom.madun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText id_user, username, nama, password, c_password;
    private Spinner id_fakultas, id_prodi, level;
    private Button btn_regist, btn_login;
    private ProgressBar loading;
    private static String URL_REGIST = "http://192.168.56.1/madinguniku2/android/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loading = findViewById(R.id.loading);
        username = findViewById(R.id.etUsername);
        id_user = findViewById(R.id.etIdUser);
        nama = findViewById(R.id.etNama);
        password =  findViewById(R.id.etPassword);
        c_password = findViewById(R.id.c_password);
        id_fakultas = findViewById(R.id.spinnerFakultas);
        id_prodi = findViewById(R.id.spinnerProdi);
        level = findViewById(R.id.spinnerLevel);
        btn_regist = findViewById(R.id.btn_register);
        btn_login = findViewById(R.id.btn_login);

        loading.setVisibility(View.GONE);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class ));
            }
        });

        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Regist();
                //hasil.setText("Hasilnya adalah " + id_fakultas.getSelectedItem().toString() + id_prodi.getSelectedItem().toString());
            }
        });
    }

    private void Regist()
    {
        loading.setVisibility(View.VISIBLE);
        btn_regist.setVisibility(View.GONE);

        final String id_user = this.id_user.getText().toString().trim();
        final String username = this.username.getText().toString().trim();
        final String nama = this.nama.getText().toString().trim();
        final String password = this.password.getText().toString().trim();
        final String c_password = this.c_password.getText().toString().trim();

        final String id_fakultas = this.id_fakultas.getSelectedItem().toString();
        final String id_prodi = this.id_prodi.getSelectedItem().toString();
        final String level = this.level.getSelectedItem().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try
                {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                    if (success.equals("1"))
                    {
                        Toast.makeText(RegisterActivity.this,"Register Succes",Toast.LENGTH_LONG ).show();
                    }

                } catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(RegisterActivity.this,"Register error" + e.toString(),Toast.LENGTH_LONG ).show();
                    loading.setVisibility(View.GONE);
                    btn_regist.setVisibility(View.VISIBLE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this,"Salah euy",Toast.LENGTH_LONG ).show();
                loading.setVisibility(View.GONE);
                btn_regist.setVisibility(View.VISIBLE);
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_user", id_user);
                params.put("id_fakultas",id_fakultas);
                params.put("id_prodi", id_prodi);
                params.put("nama", nama);
                params.put("username", username);
                params.put("password", password);
                params.put("level", level);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

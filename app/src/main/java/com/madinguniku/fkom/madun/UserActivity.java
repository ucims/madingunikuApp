package com.madinguniku.fkom.madun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;

public class UserActivity extends AppCompatActivity {

    private TextView nama, id_user, level;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        nama = findViewById(R.id.tvNamaSession);
        id_user = findViewById(R.id.tv_id_user);
        level = findViewById(R.id.tv_level);


        getSupportActionBar().setTitle("User");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        String uId_User = user.get(sessionManager.ID_USER);
        String uNama = user.get(sessionManager.NAMA);
        String uLevel = user.get(sessionManager.LEVEL);


        id_user.setText(uId_User);
        nama.setText(uNama);
        level.setText(uLevel);

    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(UserActivity.this, HomeActivity.class);
        startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UserActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}

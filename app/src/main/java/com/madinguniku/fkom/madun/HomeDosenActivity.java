package com.madinguniku.fkom.madun;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class HomeDosenActivity extends AppCompatActivity {

    SessionManager sessionManager;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_dosen);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        String dId_User = user.get(sessionManager.ID_USER);
        String dNama = user.get(sessionManager.NAMA);

        String url = "http://192.168.56.1/madinguniku2/view_post_dosen.php";
        webView = findViewById(R.id.webViewDsn);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.keluar:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Peringatan");
                alertDialogBuilder
                        .setMessage("Apakah Anda yakin ingin keluar?")
                        .setCancelable(false)
                        .setPositiveButton("Keluar",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //session.logoutUser();
                                sessionManager.logout_dosen();

                            }
                        })
                        .setNegativeButton("Batal",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                break;
            case R.id.tentang :
                Intent intent = new Intent(HomeDosenActivity.this, TentangActivity.class);
                startActivity(intent);
                break;
            case R.id.user :
                Intent intent_1 = new Intent(HomeDosenActivity.this, UserActivity.class);
                startActivity(intent_1);
                finish();
                break;
        }
        return true;
    }
}

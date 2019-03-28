package com.madinguniku.fkom.madun;

import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebBackForwardList;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

    private WebView webViewMhs;
    private SwipeRefreshLayout swipeRefreshLayout;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        swipeRefreshLayout = findViewById(R.id.swipeLayout);

        webViewMhs = findViewById(R.id.webViewMhs);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        String mId_user = user.get(sessionManager.ID_USER);
        String mNama = user.get(sessionManager.NAMA);
        String mLevel = user.get(sessionManager.LEVEL);

//        if (mLevel.equals("Mahasiswa")) {
//            //webViewMhs = findViewById(R.id.webViewMhs);
//            String url = "http://uci3026.000webhostapp.com/view_post_mhs.php";
//            webViewMhs.getSettings().setJavaScriptEnabled(true);
//            webViewMhs.loadUrl(url);
//            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//                @Override
//                public void onRefresh() {
//                    webViewMhs.reload();
//                }
//            });
//
//            webViewMhs.setDownloadListener(new DownloadListener() {
//                @Override
//                public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
//                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
//                    request.allowScanningByMediaScanner();
//                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//
//                    DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
//                    downloadManager.enqueue(request);
//                    Toast.makeText(HomeActivity.this, "Keur ngadownload .." , Toast.LENGTH_LONG).show();
//                }
//            });
//
//            webViewMhs.setWebViewClient(new WebViewClient(){
//                @Override
//                public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                    super.onPageStarted(view, url, favicon);
//                }
//
//                @Override
//                public void onPageFinished(WebView view, String url) {
//                    swipeRefreshLayout.setRefreshing(false);
//                    super.onPageFinished(view, url);
//                }
//            });
//
//        } else if(mLevel.equals("Dosen / Staf")){
//            //webViewMhs = findViewById(R.id.webViewMhs);
//            String url = "http://http://uci3026.000webhostapp.com/view_post_dosen.php";
//            webViewMhs.getSettings().setJavaScriptEnabled(true);
//            webViewMhs.loadUrl(url);
//
//            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//                @Override
//                public void onRefresh() {
//                    webViewMhs.reload();
//                }
//            });
//
//            webViewMhs.setDownloadListener(new DownloadListener() {
//                @Override
//                public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
//                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
//                    request.allowScanningByMediaScanner();
//                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//
//                    DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
//
//
//                }
//            });
//
//            webViewMhs.setWebViewClient(new WebViewClient(){
//                @Override
//                public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                    super.onPageStarted(view, url, favicon);
//                }
//
//                @Override
//                public void onPageFinished(WebView view, String url) {
//                    swipeRefreshLayout.setRefreshing(false);
//                    super.onPageFinished(view, url);
//                }
//            });
//
//        } else {
//            Toast.makeText(HomeActivity.this, "Error 404", Toast.LENGTH_LONG).show();
//            sessionManager.logout();
//        }



    }

    @Override
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
                                sessionManager.logout();

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
                Intent intent = new Intent(HomeActivity.this, TentangActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.user :
                Intent intent_ = new Intent(HomeActivity.this, UserActivity.class);
                startActivity(intent_);
                finish();
                break;
        }
        return true;
    }
}

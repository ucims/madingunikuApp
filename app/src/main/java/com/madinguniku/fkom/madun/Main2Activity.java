package com.madinguniku.fkom.madun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class Main2Activity extends AppCompatActivity {

    WebView webView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent i = getIntent();
        String file_url = i.getStringExtra("file_url");

        webView = findViewById(R.id.webview);
        progressBar = findViewById(R.id.progressbar);
        webView.getSettings().setJavaScriptEnabled(true);
        String filename = "http://uci3026.000webhostapp.com/" + file_url;
        webView.loadUrl("http://docs.google.com/gview?embedded=true&url=" + filename);

        webView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                // do your stuff here
                progressBar.setVisibility(View.GONE);
            }
        });

    }
}

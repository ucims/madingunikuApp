package com.madinguniku.fkom.madun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Main3Activity extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent i = getIntent();
        String file_url = i.getStringExtra("file_url");

        imageView = findViewById(R.id.imageView);
        Picasso.with(this).load("http://uci3026.000webhostapp.com/" + file_url).fit().into(imageView);
//        http://192.168.12.1/madinguniku2/
    }
}

package com.madinguniku.fkom.madun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.downloader.PRDownloader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataPostActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<SetGetPost> postList;
    PostAdapter adapter;
    SessionManager sessionManager;


//    private static final String URL_POST_MHS = "http://192.168.12.1/madinguniku2/api_mhs.php";
//    private static final String URL_POST_DSN = "http://192.168.12.1/madinguniku2/api_dsn.php";
    private static final String URL_POST_MHS = "http://uci3026.000webhostapp.com/api.php";
    private static final String URL_POST_DSN = "http://uci3026.000webhostapp.com/api_dsn.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_post);

        PRDownloader.initialize(this);

        recyclerView = findViewById(R.id.recyclerViewPost);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        postList = new ArrayList<>();

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.getUserDetail();
        String id_prodi = user.get(sessionManager.ID_PRODI);
        String level = user.get(sessionManager.LEVEL);

        loadPost();
        adapter = new PostAdapter(this, postList);
        recyclerView.setAdapter(adapter);

    }

    private void loadPost() {
        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getUserDetail();
        String id_prodi = user.get(sessionManager.ID_PRODI);
        String level = user.get(sessionManager.LEVEL);

        if (level.equals("Mahasiswa")){
            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_POST_MHS,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                Log.wtf("Response", response + "");
                                JSONArray array = new JSONArray(response);
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject object = array.getJSONObject(i);
                                    int id_post = object.getInt("id_prodi");
                                    String judul = object.getString("judul");
                                    String tanggal = object.getString("tanggal");
                                    int id_prodi = object.getInt("id_prodi");
                                    String level = object.getString("level");
                                    String file_url = object.getString("file_url");
                                    String keterangan = object.getString("keterangan");

                                    SetGetPost setGetPost = new SetGetPost(id_post, judul, tanggal, id_prodi, level, file_url, keterangan);
                                    postList.add(setGetPost);
                                }
                                adapter = new PostAdapter(DataPostActivity.this, postList);
                                recyclerView.setAdapter(adapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(DataPostActivity.this, error.getMessage(), Toast.LENGTH_SHORT ).show();
                        }
                    });
            Volley.newRequestQueue(this).add(stringRequest);
        } else if (level.equals("Dosen") || level.equals("Dosen / Staf") ){
            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_POST_DSN,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                Log.wtf("Response", response + "");
                                JSONArray array = new JSONArray(response);
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject object = array.getJSONObject(i);
                                    int id_post = object.getInt("id_prodi");
                                    String judul = object.getString("judul");
                                    String tanggal = object.getString("tanggal");
                                    int id_prodi = object.getInt("id_prodi");
                                    String level = object.getString("level");
                                    String file_url = object.getString("file_url");
                                    String keterangan = object.getString("keterangan");

                                    SetGetPost setGetPost = new SetGetPost(id_post, judul, tanggal, id_prodi, level, file_url, keterangan);
                                    postList.add(setGetPost);
                                }
                                adapter = new PostAdapter(DataPostActivity.this, postList);
                                recyclerView.setAdapter(adapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(DataPostActivity.this, error.getMessage(), Toast.LENGTH_SHORT ).show();
                        }
                    });
            Volley.newRequestQueue(this).add(stringRequest);
        }

    }
}

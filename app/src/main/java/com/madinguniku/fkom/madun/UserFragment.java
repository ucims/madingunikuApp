package com.madinguniku.fkom.madun;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    SessionManager sessionManager;
    TextView nama, id_user, level, id_prodi;

    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        nama = view.findViewById(R.id.tvNamaSession);
        id_user = view.findViewById(R.id.tv_id_user);
        level = view.findViewById(R.id.tv_level);
        id_prodi = view.findViewById(R.id.tv_prodi);


//        getSupportActionBar().setTitle("User");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sessionManager = new SessionManager(getActivity());
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        String uId_User = user.get(sessionManager.ID_USER);
        String uNama = user.get(sessionManager.NAMA);
        String uLevel = user.get(sessionManager.LEVEL);
        String uProdi = user.get(sessionManager.ID_PRODI);

        id_user.setText(uId_User);
        nama.setText(uNama);
        level.setText(uLevel);

        switch (uProdi){
            case "8" :
                id_prodi.setText("TI");
                break;
            case "9" :
                id_prodi.setText("SI");
                break;
            case "10" :
                id_prodi.setText("MI");
                break;
        }

//        id_prodi.setText(uProdi);

        return view;

    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        Intent intent = new Intent(UserActivity.this, HomeActivity.class);
//        startActivity(intent);
//        return true;
//    }
//
//    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent(UserActivity.this, HomeActivity.class);
//        startActivity(intent);
//    }


}

package com.madinguniku.fkom.madun;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends Fragment {

    SessionManager sessionManager;
    TextView textView;

    RecyclerView recyclerView;
    List<SetGetPost> postList;
    PostAdapter adapter;

    private static final String URL_POST = "http://uci3026.000webhostapp.com/android/api.php";


    public PostFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post, container, false);

        sessionManager = new SessionManager(getActivity());
        sessionManager.checkLogin();

        HashMap<String, String> data_post = sessionManager.getUserDetail();
        String mId_user = data_post.get(sessionManager.ID_USER);
        String mNama = data_post.get(sessionManager.NAMA);
        String mLevel = data_post.get(sessionManager.LEVEL);
        String mId_Prodi = data_post.get(sessionManager.ID_PRODI);

        textView = view.findViewById(R.id.text123);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(PostFragment.this, DetailPostFragment.class);
//                startActivity(intent);
            }
        });
        return view;
    }

}

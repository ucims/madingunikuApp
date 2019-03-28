package com.madinguniku.fkom.madun;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.barteksc.pdfviewer.PDFView;


/**
 * A simple {@link Fragment} subclass.
 */
public class KalenderAkademikFragment extends Fragment {


    public KalenderAkademikFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kalender_akademik, container, false);
        PDFView pdfView = (PDFView) view.findViewById(R.id.fragmentKalenderAkademik);
        pdfView.fromAsset("kalender.pdf").load();

        return view;    }

}

package com.madinguniku.fkom.madun;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.github.barteksc.pdfviewer.PDFView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DenahFragment extends Fragment {

    WebView webView;
    ProgressBar progressBar;

    public DenahFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_denah, container, false);
        PDFView pdfView = (PDFView) view.findViewById(R.id.denahViewer);
        pdfView.fromAsset("denah.pdf").load();

        return view;
    }

}

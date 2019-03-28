package com.madinguniku.fkom.madun;


import android.app.DownloadManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import static android.content.Context.DOWNLOAD_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    WebView webViewMhs;
//    SwipeRefreshLayout swipeRefreshLayout;
//    TextView prodi;

    SessionManager sessionManager;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
//        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);
        webViewMhs = (WebView) view.findViewById(R.id.webViewMhs);
//        prodi = view.findViewById(R.id.homeProdi);

        sessionManager = new SessionManager(getActivity());
        sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.getUserDetail();
        String mId_user = user.get(sessionManager.ID_USER);
        String mNama = user.get(sessionManager.NAMA);
        String mLevel = user.get(sessionManager.LEVEL);
        String mId_Prodi = user.get(sessionManager.ID_PRODI);

//        Toast.makeText(getActivity(), "Selamat Datang " + mLevel, Toast.LENGTH_LONG).show(getContext);

//        if (mLevel == null){
//            sessionManager.logout();
//        } else if (mLevel.equals("Mahasiswa")) {
//                //webViewMhs = findViewById(R.id.webViewMhs);
////            String url = "http://uci3026.000webhostapp.com/view_post_mhs.php";
//
//                if (mId_Prodi.equals("8")) // prodi TI
//                {
//                    Toast.makeText(getActivity(), "Selamat Datang", Toast.LENGTH_LONG).show();
//                    String url = "http://192.168.12.1/madinguniku2/view_post_mhs_ti.php";
////                    String url = "http://uci3026.000webhostapp.com/view_post_mhs_ti.php";
//                    webViewMhs.getSettings().setJavaScriptEnabled(true);
//                    webViewMhs.loadUrl(url);
//                    webViewMhs.setWebViewClient(new WebViewClient(){
//                        @Override
//                        public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                            super.onPageStarted(view, url, favicon);
//                        }
//
//
//                        @Override
//                        public void onPageFinished(WebView view, String url) {
////                            swipeRefreshLayout.setRefreshing(false);
//                            super.onPageFinished(view, url);
//                        }
//                    });
//
////                  prodi.setText("TI");
//
//                } else if (mId_Prodi.equals("9")){ // prodi SI
//
//                    Toast.makeText(getActivity(), "Selamat Datang", Toast.LENGTH_LONG).show();
//                    String url = "http://192.168.12.1/madinguniku2/android/view_post_mhs_si.php";
//                    webViewMhs.getSettings().setJavaScriptEnabled(true);
//                    webViewMhs.loadUrl(url);
////                    prodi.setText("SI");
//
//                } else if (mId_Prodi.equals("10")){ // prodi MI
//
//                    Toast.makeText(getActivity(), "Selamat Datang", Toast.LENGTH_LONG).show();
//                    String url = "http://192.168.12.1/madinguniku2/view_post_mhs_mi.php";
////                    String url = "http://uci3026.000webhostapp.com/android/view_post_mhs_si.php";
//                    webViewMhs.getSettings().setJavaScriptEnabled(true);
//                    webViewMhs.loadUrl(url);
////                    prodi.setText("MI");
//
//                } else if (mId_Prodi.equals("17")){ // prodi DKV
//                    Toast.makeText(getActivity(), "Selamat Datang", Toast.LENGTH_LONG).show();
//                    String url = "http://192.168.12.1/madinguniku2/view_post_mhs_mi.php";
////                    String url = "http://uci3026.000webhostapp.com/android/view_post_mhs_mi.php";
//                    webViewMhs.getSettings().setJavaScriptEnabled(true);
//                    webViewMhs.loadUrl(url);
////                    prodi.setText("DKV");
//                } else {
//                    Toast.makeText(getActivity(), "Akses salah !", Toast.LENGTH_LONG).show();
//                }
//
//
//
////
////                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
////                    @Override
////                    public void onRefresh() {
////                        webViewMhs.reload();
////                    }
////                });
////
////                webViewMhs.setDownloadListener(new DownloadListener() {
////                    @Override
////                    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
////                        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
////                        request.allowScanningByMediaScanner();
////                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
////
////                        DownloadManager downloadManager = (DownloadManager)getActivity().getSystemService(DOWNLOAD_SERVICE);
////                        downloadManager.enqueue(request);
////                        Toast.makeText(getActivity(), "Proses Download  .." , Toast.LENGTH_LONG).show();
////                    }
////                });
//
//            } else if(mLevel.equals("Dosen / Staf")){
//                //webViewMhs = findViewById(R.id.webViewMhs);
//            String url = "http://192.168.12.1/madinguniku2/view_post_dosen.php";
////                String url = "http://uci3026.000webhostapp.com/android/view_post_dosen.php";
//
//                webViewMhs.getSettings().setJavaScriptEnabled(true);
//                webViewMhs.loadUrl(url);
//
////                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
////                    @Override
////                    public void onRefresh() {
////                        webViewMhs.reload();
////                    }
////                });
//
//                webViewMhs.setDownloadListener(new DownloadListener() {
//                    @Override
//                    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
//                        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
//                        request.allowScanningByMediaScanner();
//                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//
//                        //DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
//                        //downloadManager.enqueue(request);
//                        //Toast.makeText(HomeFragment.this, "Keur ngadownload .." , Toast.LENGTH_LONG).show();
//
//
//                    }
//                });
//
//                webViewMhs.setWebViewClient(new WebViewClient(){
//                    @Override
//                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                        super.onPageStarted(view, url, favicon);
//                    }
//
//                    @Override
//                    public void onPageFinished(WebView view, String url) {
////                        swipeRefreshLayout.setRefreshing(false);
//                        super.onPageFinished(view, url);
//                    }
//                });
//
//            } else {
//                //Toast.makeText(HomeFragment.this, "Error 404", Toast.LENGTH_LONG).show();
//                sessionManager.logout();
//            }

        return view;
        }


    }



package com.madinguniku.fkom.madun;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.Progress;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by heartfilia on 06/01/19.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private Context context;
    private List<SetGetPost> postList;
    DownloadManager downloadManager;
    SessionManager sessionManager;

//    private static String file_url = "https://raw.githubusercontent.com/tonyofrancis/Fetch/v2/screenshot.png";


    public PostAdapter(Context context, List<SetGetPost> postList) {
        this.context = context;
        this.postList = postList;
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        TextView post_id, post_judul, post_tanggal, post_id_prodi,post_keterangan;
        ImageView imageViewPost,imageViewPost2 ;
        Button btnDownload, btnLihat;
        String ekstensi, ekstensi1, ekstensi2;

        public PostViewHolder(View view)
        {
            super(view);

//            post_id = itemView.findViewById(R.id.textViewPostId);
            post_judul = itemView.findViewById(R.id.textViewPostJudul);
            post_tanggal = itemView.findViewById(R.id.textViewPostTanggal);
//            post_id_prodi = itemView.findViewById(R.id.textViewPostIdProdi);
            post_keterangan = itemView.findViewById(R.id.textViewPostKeterangan);

            imageViewPost = itemView.findViewById(R.id.imageViewPost);
            imageViewPost2 = itemView.findViewById(R.id.imageViewPost2);
            btnDownload = itemView.findViewById(R.id.btnDownload);
            btnLihat = itemView.findViewById(R.id.btnLihat);
//            sessionManager = new SessionManager(context);
//
//            HashMap<String, String> user = sessionManager.getUserDetail();
//            String id_prodi = user.get(sessionManager.ID_PRODI);
//            String level = user.get(sessionManager.LEVEL);

        }

    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.post_list, null);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {

        final SetGetPost setGetPost = postList.get(position);
//        String url = "http://192.168.56.1/madinguniku2/view_post_dosen.php";
//        Picasso.with(context).load("http://192.168.12.1/madinguniku2/" + setGetPost.getFile_url()).fit().into(holder.imageViewPost);
        Picasso.with(context).load("http://uci3026.000webhostapp.com/" + setGetPost
                .getFile_url())
                .fit()
                .error(R.drawable.noimage)
                .into(holder.imageViewPost);
        Picasso.with(context).load("http://uci3026.000webhostapp.com/" + setGetPost
                .getFile_url())
                .fit()
                .error(R.drawable.document)
                .into(holder.imageViewPost2);
        holder.post_judul.setText(setGetPost.getJudul());
        holder.post_tanggal.setText(setGetPost.getTanggal());
//        holder.post_id_prodi.setText(String.valueOf(setGetPost.getId_prodi()));
        holder.post_keterangan.setText(setGetPost.getKeterangan());

        holder.ekstensi2 = setGetPost.getFile_url();
        holder.ekstensi = setGetPost.getFile_url().substring(holder.ekstensi2.length() -5); // for docx
        holder.ekstensi1 = setGetPost.getFile_url().substring(holder.ekstensi2.length() -4); //for doc png pdf jpg
//        holder.ekstensi2 = setGetPost.getFile_url().substring(8,-5); //for docx

//        Toast.makeText(context, setGetPost.getFile_url(), Toast.LENGTH_LONG).show();

        holder.imageViewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, setGetPost.getFile_url(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, Main3Activity.class);
                i.putExtra("file_url", setGetPost.getFile_url());
                context.startActivity(i);
            }
        });
        holder.imageViewPost2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Main2Activity.class);
                i.putExtra("file_url", setGetPost.getFile_url());
                context.startActivity(i);
            }
        });

//        final String extensi = setGetPost.getFile_url().substring(0,-3);

        holder.btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String dirPath = context.getFilesDir().getAbsolutePath()+ File.separator+"downloads";
//                String dirPath = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"Download";ro
//                String dirPath = "/storage/emulated/0/Download/";
//                Toast.makeText(context, dirPath, Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(context, Main2Activity.class);
//                i.putExtra("file_url", setGetPost.getFile_url());
//                context.startActivity(i);

                String url = "http://uci3026.000webhostapp.com/"+setGetPost.getFile_url();
//                String url = "http://192.168.12.1/madinguniku2/"+setGetPost.getFile_url();
                DownloadTask downloadTask = new DownloadTask(context);
                downloadTask.execute(url);
//                "http://uci3026.000webhostapp.com/uploads/REGULARVERBS.doc"

            }
        });

        holder.btnLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "Lihat File ", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, Main2Activity.class);
                i.putExtra("file_url", setGetPost.getFile_url());
                context.startActivity(i);
            }
        });

//        holder.btnLihat.setVisibility(View.GONE);
//        holder.btnDownload.setVisibility(View.GONE);
        if (holder.ekstensi1.equals(".doc") || holder.ekstensi.equals(".docx")){
            holder.imageViewPost2.setVisibility(View.VISIBLE);
            holder.imageViewPost.setVisibility(View.INVISIBLE);
            holder.btnLihat.setVisibility(View.INVISIBLE);
            holder.btnDownload.setVisibility(View.INVISIBLE);
//            holder.btnLihat.setVisibility(View.INVISIBLE);
        } else if(holder.ekstensi1.equals(".pdf")){
            holder.imageViewPost2.setVisibility(View.VISIBLE);
            holder.imageViewPost.setVisibility(View.INVISIBLE);
            holder.btnLihat.setVisibility(View.INVISIBLE);
            holder.btnDownload.setVisibility(View.INVISIBLE);
//            holder.btnDownload.setVisibility(View.INVISIBLE);
        } else if (holder.ekstensi1.equals(".jpg") || holder.ekstensi.equals(".png")){
            holder.btnLihat.setVisibility(View.INVISIBLE);
            holder.btnDownload.setVisibility(View.INVISIBLE);
            holder.imageViewPost2.setVisibility(View.INVISIBLE);
            holder.imageViewPost.setVisibility(View.VISIBLE);
        } else if (holder.ekstensi1.equals(".gz")){
            holder.btnLihat.setVisibility(View.INVISIBLE);
            holder.btnDownload.setVisibility(View.INVISIBLE);
          //  holder.imageViewPost.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }


//    class DownloadTask extends AsyncTask<String, Integer, File> {
//        ProgressDialog progressDialog;
//
//        @Override
//        protected void onPreExecute() {
////            super.onPreExecute();
//            progressDialog = new ProgressDialog(context);
//            progressDialog.setTitle("Download in Progress...");
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//            progressDialog.setMax(100);
//            progressDialog.setProgress(0);
//            progressDialog.show();
//        }
//
//        @Override
//        protected File doInBackground(String... params) {
//            File newFolder = new File("sdcard/photoalbum");
//            if (!newFolder.exists()) {
//                newFolder.mkdir();
//            }
//            File inputFile = new File(newFolder, "downloaded.docx");
//            String path = params[0];
//            int fileLength = 0;
//            try {
//                URL url = new URL(path);
//                URLConnection urlConnection = url.openConnection();
//                urlConnection.connect();
//                fileLength = urlConnection.getContentLength();
////                inputFile = new File(newFolder, "downloaded.jpg");
//                InputStream inputStream = new BufferedInputStream(url.openStream(),8192);
//                byte[] data = new byte[1024];
//                int total = 0;
//                int count = 0;
//                OutputStream outputStream = new FileOutputStream(inputFile);
//                while ((count = inputStream.read(data)) != -1) {
//                    total += count;
//                    outputStream.write(data, 0, count);
//                    int progress =  (int) total * 100 / fileLength;
//                    publishProgress(progress);
//                }
//                inputStream.close();
//                outputStream.close();
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return inputFile;
//        }
//
//        @Override
//        protected void onProgressUpdate(Integer... values) {
////            super.onProgressUpdate(values);
//            progressDialog.setProgress(values[0]);
//        }
//
//        @Override
//        protected void onPostExecute(File file) {
////            super.onPostExecute(aVoid);
//            progressDialog.hide();
//            Toast.makeText(context, "Download Complete...", Toast.LENGTH_LONG).show();
//            String path = "sdcard/photoalbum/downloaded.docx";
////            imageView.setImageDrawable(Drawable.createFromPath(path));
//            Intent intent = new Intent();
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.setAction(Intent.ACTION_VIEW);
//            String type = "application/msword";
//            intent.setDataAndType(Uri.fromFile(file), type);
//            context.startActivity(intent);
//        }
//    }
    
}

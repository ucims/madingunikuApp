package com.madinguniku.fkom.madun;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by heartfilia on 09/01/19.
 */

public class DownloadTask extends AsyncTask<String, Integer, File> {

    ProgressDialog progressDialog;
    Context context;

    public DownloadTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
//            super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Download in Progress...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(100);
        progressDialog.setProgress(0);
        progressDialog.show();
    }

    @Override
    protected File doInBackground(String... params) {
        String dirPath = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"Download";
        File newFolder = new File(dirPath);
        if (!newFolder.exists()) {
            newFolder.mkdir();
        }
        File inputFile = new File(newFolder, "downloaded.doc");
        String path = params[0];
        int fileLength = 0;
        try {
            URL url = new URL(path);
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            fileLength = urlConnection.getContentLength();
//                inputFile = new File(newFolder, "downloaded.jpg");
            InputStream inputStream = new BufferedInputStream(url.openStream(),8192);
            byte[] data = new byte[1024];
            int total = 0;
            int count = 0;
            OutputStream outputStream = new FileOutputStream(inputFile);
            while ((count = inputStream.read(data)) != -1) {
                total += count;
                outputStream.write(data, 0, count);
                int progress =  (int) total * 100 / fileLength;
                publishProgress(progress);
            }
            inputStream.close();
            outputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputFile;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
        progressDialog.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(File file) {
//            super.onPostExecute(aVoid);
        progressDialog.hide();
//        String dirPath = "sdcard/Download/downloaded.docx";
        String dirPath = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"Download/downloaded.doc";
//                android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"documents";
        Toast.makeText(context, dirPath, Toast.LENGTH_LONG).show();
//        String path = "sdcard/photoalbum/downloaded.docx";
//            imageView.setImageDrawable(Drawable.createFromPath(path));
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setAction(Intent.ACTION_VIEW);
        String type = "application/msword";
//        intent.setDataAndType(Uri.fromFile(file), type);


        Uri apkURI = FileProvider.getUriForFile(
                context,
                context.getApplicationContext()
                        .getPackageName() + ".provider", file);
        intent.setDataAndType(apkURI, type);
        context.startActivity(intent);

    }

}

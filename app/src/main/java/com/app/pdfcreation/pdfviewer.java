package com.app.pdfcreation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.io.File;

public class pdfviewer extends AppCompatActivity {

    String url="";
    WebView webview;
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfviewer);

        url= viewPDF.getActivityInstance().returna();

        try {
            Intent intent = new Intent();

            intent.setPackage("com.adobe.reader");
            intent.setDataAndType(Uri.parse(url), "application/pdf");

            startActivity(intent);


        } catch (ActivityNotFoundException activityNotFoundException) {
            activityNotFoundException.printStackTrace();
            Toast.makeText(this, "hel", Toast.LENGTH_SHORT).show();

            throw activityNotFoundException;
        } catch (Exception otherException) {
            otherException.printStackTrace();
            Toast.makeText(this, "hel2", Toast.LENGTH_SHORT).show();

            throw otherException;
        }


    }


}

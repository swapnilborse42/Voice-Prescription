package com.app.pdfcreation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class home extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void Generate(View view) {

        Intent intent= new Intent(home.this,SpeechToText.class);
//                        startActivity(intent);
    }
}

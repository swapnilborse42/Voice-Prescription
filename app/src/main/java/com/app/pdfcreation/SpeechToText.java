package com.app.pdfcreation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.pdfcreation.ui.PdfCreationActivity;

import java.util.ArrayList;
import java.util.Locale;


public class SpeechToText extends AppCompatActivity {


    private TextView txvResult;

   private EditText nameview;
    private EditText presview;
    static SpeechToText INSTANCE;
    public String name ="",name1="",prescription="",prescription1="",symptoms="",symptoms1="",diagnosis="",diagnosis1="",advice="",advice1="";
    int count=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_to_text);
        txvResult = (TextView) findViewById(R.id.txvResult);
        nameview = (EditText) findViewById(R.id.nameview);
        presview = (EditText) findViewById(R.id.presview);
        INSTANCE=this;
    }

    public static SpeechToText getActivityInstance()
    {
        return INSTANCE;
    }
    public String getData() { return this.name1; }
    public String getData1() {return this.symptoms1; }
    public String getData2()
    {
        return this.diagnosis1;
    }
    public String getData3()
    {
        return this.prescription1;
    }
    public String getData4()
    {
        return this.advice1;
    }


    public void getSpeechInput(View view) {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txvResult.setText(result.get(0));


                    String sample=result.get(0);
                    //Toast.makeText(getApplicationContext(),sample,Toast.LENGTH_SHORT).show();
                    String[] items = sample.split(" ");
                    //List<String> itemList = Arrays.asList(items);
                    int i;

                try {
                    for (i = 0; i < items.length; i++) {
                        if (items[i].equals("name")) {
                            name = Integer.toString(i);
                            // System.out.print(name);
                            //Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
                        }
//                        else
//                        {
//                            Toast.makeText(this, "Invalid command", Toast.LENGTH_SHORT).show();
//                        }
                        if (items[i].equals("symptoms")||items[i].equals("symptom")) {
                            symptoms = Integer.toString(i);
                            // System.out.print(name);
                           // Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
                        }
                        if (items[i].equals("diagnosis")) {
                            diagnosis = Integer.toString(i);
                            // System.out.print(name);
                            //Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
                        }

                        if (items[i].equals("prescription")||items[i].equals("description")) {
                            prescription = Integer.toString(i);
                            // System.out.print(prescription);
                            //Toast.makeText(this, prescription, Toast.LENGTH_SHORT).show();
                        }
                        if (items[i].equals("advice")) {
                            advice = Integer.toString(i);
                            // System.out.print(name);
                            //Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
                        }
//                        else
//                        {
//                            Toast.makeText(this, "Invalid command", Toast.LENGTH_SHORT).show();
//                        }
                    }
                    /*int val=Integer.parseInt(prescription);
                    Toast.makeText(this,val, Toast.LENGTH_SHORT).show();*/

                    for (int j = 1; j < Integer.parseInt(symptoms); j++) {
                        name1 = name1.concat(" ").concat(items[j]);
                        //Toast.makeText(this,items[j], Toast.LENGTH_SHORT).show();
                    }
                    for (int j = (Integer.parseInt(symptoms)) + 1; j < Integer.parseInt(diagnosis); j++) {
                        symptoms1 = symptoms1.concat(" ").concat(items[j]);
                        //Toast.makeText(this,items[j], Toast.LENGTH_SHORT).show();
                    }//items.length
                    for (int j = (Integer.parseInt(diagnosis)) + 1; j < Integer.parseInt(prescription); j++) {
                        diagnosis1 = diagnosis1.concat(" ").concat(items[j]);
                        //Toast.makeText(this,items[j], Toast.LENGTH_SHORT).show();
                    }
                    for (int j = (Integer.parseInt(prescription)) + 1; j < Integer.parseInt(advice); j++) {
                        prescription1 = prescription1.concat(" ").concat(items[j]);
                        //Toast.makeText(this,items[j], Toast.LENGTH_SHORT).show();
                    }
                    for (int j = (Integer.parseInt(advice)) + 1; j < items.length; j++) {
                        advice1 = advice1.concat(" ").concat(items[j]);
                        //Toast.makeText(this,items[j], Toast.LENGTH_SHORT).show();
                    }

                }
                catch (Exception e)
                {
                    count=0;
                    Toast.makeText(this, "**SOMETHING WRONG**" , Toast.LENGTH_SHORT).show();
                }

                    //name2= Integer.toString(name1);
//                    Toast.makeText(this, name1, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(this, symptoms1, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(this, diagnosis1, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(this, prescription1, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(this, advice1, Toast.LENGTH_SHORT).show();


                        Intent i1 = new Intent(SpeechToText.this,PdfCreationActivity.class);
                        startActivity(i1);

//                    nameview.setText(name1);
//                    presview.setText(prescription1);


                }
                break;
        }
    }
}

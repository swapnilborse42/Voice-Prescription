package com.app.pdfcreation;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class viewPDF extends AppCompatActivity {

    ListView myPDFListView;
    DatabaseReference databaseReference;
    List<uploadPDF> uploadPDFs;
    String a="";
    static viewPDF INSTANCE;
    String[] uploads;
    EditText textBox;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);

        textBox=(EditText)findViewById(R.id.textBox);
        text=(TextView)findViewById(R.id.text);
        myPDFListView = (ListView) findViewById(R.id.myListVIew);
        INSTANCE=this;
        uploadPDFs = new ArrayList<>();

        viewAllFiles();

        myPDFListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //@SuppressLint("IntentReset")
            @SuppressLint("IntentReset")
            @Override
            public void onItemClick(AdapterView<?>parent, View view, int position, long id) {

                uploadPDF uploadPDF=uploadPDFs.get(position);
//                Toast.makeText(ViewPdf.this,position, Toast.LENGTH_SHORT).show();

//                Intent intent=new Intent();
                a=uploadPDF.getUrl();
                Toast.makeText(viewPDF.this,a, Toast.LENGTH_SHORT).show();

//                Intent i1 = new Intent(viewPDF.this,pdfviewer.class);
//                startActivity(i1);
                String url=a;

                try {
                    Intent intent = new Intent();

                    intent.setPackage("com.adobe.reader");
                    intent.setDataAndType(Uri.parse(url), "application/pdf");

                    startActivity(intent);


                } catch (ActivityNotFoundException activityNotFoundException) {
                    activityNotFoundException.printStackTrace();
                    //Toast.makeText(this, "hel", Toast.LENGTH_SHORT).show();

                    throw activityNotFoundException;
                } catch (Exception otherException) {
                    otherException.printStackTrace();
                    //Toast.makeText(this, "hel2", Toast.LENGTH_SHORT).show();

                    throw otherException;
                }




//                String url =a;
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));
//                startActivity(i);






//                intent.setType(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse(uploadPDF.getUrl()));
//                String a=uploadPDF.getUrl();
//                Toast.makeText(ViewPdf.this,a, Toast.LENGTH_SHORT).show();
//
//                startActivity(intent);
//                Toast.makeText(ViewPdf.this,Long.toString(id) , Toast.LENGTH_SHORT).show();

            }
        });
    }

    public String returna(){
        return a;
    }
    public static viewPDF getActivityInstance()
    {
        return INSTANCE;
    }

    private void viewAllFiles() {

        databaseReference= FirebaseDatabase.getInstance().getReference("uploads");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){


                    uploadPDF uploadPDF=postSnapshot.getValue(uploadPDF.class);
                    uploadPDFs.add(uploadPDF);



                }

                uploads=new String[uploadPDFs.size()];
                for(int i=0;i<uploads.length;i++)
                {
                    uploads[i]=uploadPDFs.get(i).getName();

                }


                final ArrayAdapter<String> adapter =new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,uploads){



                    @Override
                    public View getView(int position,  View convertView, ViewGroup parent) {
                        View view=super.getView(position,convertView,parent);
                        TextView myText= (TextView) view.findViewById(android.R.id.text1);
                        myText.setTextColor(Color.BLACK);


                        return view;

                    }
                };
                myPDFListView.setAdapter(adapter);

                textBox.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}

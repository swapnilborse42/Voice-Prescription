package com.app.pdfcreation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.pdfcreation.ui.PdfCreationActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Upload extends AppCompatActivity {

    String PATH_FILE = "",cmtview1="";
    EditText editPDFName;
    Button btn_upload;
    EditText cmtview;
    static Upload INSTANCE;

    StorageReference storageReference;
    DatabaseReference databaseReference;
    String mob="",date="",name="",Symptom="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        INSTANCE=this;

        mob= PdfCreationActivity.getActivityInstance().getData51();
        date= PdfCreationActivity.getActivityInstance().getData71();
        name=PdfCreationActivity.getActivityInstance().getData0();
        Symptom=PdfCreationActivity.getActivityInstance().getData11();
        editPDFName = (EditText) findViewById(R.id.editText1);
        btn_upload = (Button) findViewById(R.id.button);
        cmtview=(EditText)findViewById(R.id.cmtview);


        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cmtview1 = cmtview.getText().toString();

                selectPDFile();
//                Uri path = Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath()+ "/pdf_creation_by_xml/pdf_15_01_2020_03_41_08.pdf"));
//                uploadPDF(data.getData());
            }
        });
    }

    public static Upload getActivityInstance()
    {
        return INSTANCE;
    }
    public String getcmt() { return this.cmtview1; }

    private void selectPDFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        Uri path = Uri.parse(Environment.getExternalStorageDirectory().getPath()+ "/pdf_creation_by_xml/");
        intent.setDataAndType(path,"application/pdf");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
        Toast.makeText(this, path.toString(), Toast.LENGTH_SHORT).show();
        startActivityForResult(Intent.createChooser(intent, "select pdf file"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();

//            if (uri != null) {
//                PATH_FILE = uri.toString();
//                Toast.makeText(this, PATH_FILE, Toast.LENGTH_SHORT).show();
//                if (PATH_FILE.substring(0, 1).equals("c")) { // ES
//                    PATH_FILE = "file://" + PATH_FILE.substring(28,PATH_FILE.length());
//                    Toast.makeText(this, PATH_FILE, Toast.LENGTH_SHORT).show();
//                }
////                if (PATH_FILE.toLowerCase().startsWith("file://")) {
////                    PATH_FILE = (new File(URI.create(PATH_FILE))).getAbsolutePath();
////                    Toast.makeText(this, PATH_FILE, Toast.LENGTH_SHORT).show();
////                }
//            }
///storage/emulated/0/pdf_creation_by_xml/pdf_19_01_2020_02_00_34.pdf
            uploadPDF(data.getData());
//            Toast.makeText(this, data.getData().toString(), Toast.LENGTH_SHORT).show();

//            File filelocation=new File("/pdf_creation_by_xml/pdf_15_01_2020_03_41_20.pdf");
//            Uri path = Uri.fromFile(filelocation);
//            uploadPDF(path);
//            Toast.makeText(this, path.toString(), Toast.LENGTH_SHORT).show();
        }
    }


    private void uploadPDF(Uri data) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("loading");

        progressDialog.show();
        Toast.makeText(this, data.toString(), Toast.LENGTH_SHORT).show();

        StorageReference reference = storageReference.child("uploads/"+ + System.currentTimeMillis() + ".pdf");
        reference.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                while (!uri.isComplete()) ;
                Uri url = uri.getResult();

                uploadPDF uploadPDF = new uploadPDF(mob+"   "+ date+"\n\n"+"Symptom:"+Symptom+"\n\n"+cmtview1, url.toString(),editPDFName.getText().toString());
                databaseReference.child(databaseReference.push().getKey()).setValue(uploadPDF);
                Toast.makeText(Upload.this, "file uploaded", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                progressDialog.setMessage("uploaded" + (int) progress + "%");

            }
        });

        startActivity(new Intent(getApplicationContext(), register.class));

    }

//    public void btn_action(View view) {
//        startActivity(new Intent(getApplicationContext(), viewPDF.class));
//    }

}

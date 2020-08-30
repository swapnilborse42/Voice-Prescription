package com.app.pdfcreation;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.app.pdfcreation.R;
import com.app.pdfcreation.SpeechToText;
import com.app.pdfcreation.ui.PdfCreationActivity;

import java.io.File;

public class register extends AppCompatActivity {

    String email="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email=PdfCreationActivity.getActivityInstance().getData61();

//        Intent i = new Intent(Intent.ACTION_SEND);
//        i.setType("application/pdf");


//        File root = Environment.getExternalStorageDirectory();
//        String pathToMyAttachedFile ="Phone/pdf_creation_by_xml/pdf_17_01_2020_02_07_51.pdf";
//        File filePath = new File(root, pathToMyAttachedFile)
//        sendEmailAlert(filePath,subject,text);
//        Phone/pdf_creation_by_xml/pdf_17_01_2020_02_07_51.pdf


//        String filename="Cast.pdf";

//        Uri path = Uri.fromFile(filelocation);
//        Intent emailIntent = new Intent(Intent.ACTION_SEND);
//// set the type to 'email'
//        emailIntent .setType("vnd.android.cursor.dir/email");
//        String to[] = {"singhdevil494@abc.com"};
//        emailIntent .putExtra(Intent.EXTRA_EMAIL, to);
//// the attachment
//        emailIntent .putExtra(Intent.EXTRA_STREAM, path);
//// the mail subject
//        emailIntent .putExtra(Intent.EXTRA_SUBJECT, "Subject");
//        startActivity(Intent.createChooser(emailIntent , "Send email..."));




       // String fileLocation="/Report.pdf";
        //Uri uri = Uri.fromFile(new File(getFilesDir() + File.separator + fileLocation));

//        Intent i = new Intent(Intent.ACTION_SEND);
//        i.setType("application/pdf");
//        i.putExtra(Intent.EXTRA_STREAM, Uri.parse("/storage/emulated/0/Report.pdf"));
//               startActivity(Intent.createChooser(i, "Send Error Log"));

//com.android.externalstorage.documents/document/primary//pdf_creation_by_xml/pdf_15_01_2020_03_41_08.pdf
        String filename= PdfCreationActivity.getActivityInstance().getfilePath();

       // File filelocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+ "/Cast.pdf");

        File filelocation = new File(filename);

        Uri path = Uri.fromFile(filelocation);
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
// set the type to 'email'
        Toast.makeText(this, path.toString(), Toast.LENGTH_SHORT).show();
        emailIntent .setType("application/pdf");

        emailIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
// add email(s) here to whom you want to send email
        String to[] = {email};
        emailIntent .putExtra(Intent.EXTRA_EMAIL, to);
// add the attachment
        emailIntent .putExtra(Intent.EXTRA_STREAM, path);
// add mail subject
        emailIntent .putExtra(Intent.EXTRA_SUBJECT, "Mail Subject");
// create mail service chooser
        startActivity(Intent.createChooser(emailIntent , "Send email..."));
        Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
    }
}

package com.app.pdfcreation.ui;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.pdfcreation.BuildConfig;
import com.app.pdfcreation.R;
import com.app.pdfcreation.SpeechToText;
import com.app.pdfcreation.Upload;
import com.app.pdfcreation.adapter.PdfCreateAdapter;
import com.app.pdfcreation.model.PDFModel;
import com.app.pdfcreation.utils.PDFCreationUtils;
import com.app.pdfcreation.utils.PdfBitmapCache;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;



import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.io.File;
public class PdfCreationActivity extends AppCompatActivity {


    private boolean IS_MANY_PDF_FILE;

    /**
     * This is identify to number of pdf file. If pdf model list size > sector so we have create many file. After that we have merge all pdf file into one pdf file
     */
    private int SECTOR = 100; // Default value for one pdf file.
    private int START;
    private int END = SECTOR;
    private int NO_OF_PDF_FILE = 1;
    private int NO_OF_FILE;
    private int LIST_SIZE;
    private ProgressDialog progressDialog;


    /**
     * Store all dummy PDF models
     */
    private List<PDFModel> pdfModels;
    private TextView btnPdfPath;


    /**
     * Share PDF file
     */
    private Button btnSharePdfFile;
    private String name="",name1="",symptoms="",symptoms1="",diagnosis="",diagnosis1="",prescription="",prescription1="",advice="",advice1="",mobview1="",emailview1="",dateview1="";
    public String Filepath="";
    private EditText nameview;
    private EditText symview;
    private EditText diagview;
    private EditText presview;
    private EditText adview;
    private EditText mobview;
    private EditText emailview;
    private TextView dateview;
    static PdfCreationActivity INSTANCE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
      //  btnSharePdfFile = (Button) findViewById(R.id.btn_share_pdf);
        btnPdfPath = (TextView) findViewById(R.id.btn_pdf_path);
        INSTANCE=this;

        name= SpeechToText.getActivityInstance().getData();
        symptoms=SpeechToText.getActivityInstance().getData1();
        diagnosis=SpeechToText.getActivityInstance().getData2();
        prescription=SpeechToText.getActivityInstance().getData3();
        advice=SpeechToText.getActivityInstance().getData4();


        nameview = (EditText)findViewById(R.id.nameview);
        presview = (EditText)findViewById(R.id.presview);
        diagview = (EditText)findViewById(R.id.diagview);
        adview   = (EditText)findViewById(R.id.adview);
        symview  = (EditText)findViewById(R.id.symview);
        mobview  = (EditText)findViewById(R.id.mobview);
        emailview  =(EditText)findViewById(R.id.emailview);
        dateview  =(TextView) findViewById(R.id.dateview);
        String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());

// textView is the TextView view that should display it
        dateview.setText(currentDateTimeString);

        nameview.setText(name);
        presview.setText(prescription);
        diagview.setText(diagnosis);
        adview.setText(advice);
        symview.setText(symptoms);


        findViewById(R.id.btnRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name1 = nameview.getText().toString();
                symptoms1=symview.getText().toString();
                diagnosis1=diagview.getText().toString();
                prescription1=presview.getText().toString();
                advice1=adview.getText().toString();
                mobview1=mobview.getText().toString();
                emailview1=emailview.getText().toString();
                dateview1=dateview.getText().toString();
                Toast.makeText(PdfCreationActivity.this, mobview1, Toast.LENGTH_SHORT).show();
//                nameview.setText(name);
//                presview.setText(prescription);
//                diagview.setText(diagnosis);
//                adview.setText(advice);
//                symview.setText(symptoms);


                requestPermission();
            }
        });
        pdfModels = PDFModel.createDummyPdfModel();




    }

    public static PdfCreationActivity getActivityInstance()
    {
        return INSTANCE;
    }
    public String getData0() { return this.name1; }
    public String getData11() {return this.symptoms1; }
    public String getData21()
    {
        return this.diagnosis1;
    }
    public String getData31()
    {
        return this.prescription1;
    }
    public String getData41()
    {
        return this.advice1;
    }
    public String getData51()
    {
        return this.mobview1;
    }
    public String getData61()
    {
        return this.emailview1;
    }
    public String getData71()
    {
        return this.dateview1;
    }

    public String getfilePath()
    {
        return this.Filepath;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            generatePdfReport();
        }
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 111);
        } else {
            generatePdfReport();
        }
    }


    /**
     * This is manage to all model
     */
    private void generatePdfReport() {


        // NO_OF_FILE : This is identify to one file or many file have to created

        LIST_SIZE = pdfModels.size();
        NO_OF_FILE = LIST_SIZE / SECTOR;
        if (LIST_SIZE % SECTOR != 0) {
            NO_OF_FILE++;
        }
        if (LIST_SIZE > SECTOR) {
            IS_MANY_PDF_FILE = true;
        } else {
            END = LIST_SIZE;
        }
        createPDFFile();
    }

    private void createProgressBarForPDFCreation(int maxProgress) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(String.format(getString(R.string.msg_progress_pdf), String.valueOf(maxProgress)));
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(maxProgress);
        progressDialog.show();
    }

    private void createProgressBarForMergePDF() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.msg_progress_merger_pdf));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    /**
     * This function call with recursion
     * This recursion depend on number of file (NO_OF_PDF_FILE)
     */
    private void createPDFFile() {

        // Find sub list for per pdf file data
        List<PDFModel> pdfDataList = pdfModels.subList(START, END);
        PdfBitmapCache.clearMemory();
        PdfBitmapCache.initBitmapCache(getApplicationContext());
        final PDFCreationUtils pdfCreationUtils = new PDFCreationUtils(PdfCreationActivity.this, pdfDataList, LIST_SIZE, NO_OF_PDF_FILE);
        if (NO_OF_PDF_FILE == 1) {
            createProgressBarForPDFCreation(PDFCreationUtils.TOTAL_PROGRESS_BAR);
        }
        pdfCreationUtils.createPDF(new PDFCreationUtils.PDFCallback() {

            @Override
            public void onProgress(final int i) {
                progressDialog.setProgress(i);
            }

            @Override
            public void onCreateEveryPdfFile() {
                // Execute may pdf files and this is depend on NO_OF_FILE
                if (IS_MANY_PDF_FILE) {
                    NO_OF_PDF_FILE++;
                    if (NO_OF_FILE == NO_OF_PDF_FILE - 1) {

                        progressDialog.dismiss();
                        createProgressBarForMergePDF();
                        pdfCreationUtils.downloadAndCombinePDFs();
                    } else {

                        // This is identify to manage sub list of current pdf model list data with START and END

                        START = END;
                        if (LIST_SIZE % SECTOR != 0) {
                            if (NO_OF_FILE == NO_OF_PDF_FILE) {
                                END = (START - SECTOR) + LIST_SIZE % SECTOR;
                            }
                        }
                        END = SECTOR + END;
                        createPDFFile();
                    }

                } else {
                    // Merge one pdf file when all file is downloaded
                    progressDialog.dismiss();

                    createProgressBarForMergePDF();
                    pdfCreationUtils.downloadAndCombinePDFs();
                }

            }

            @Override
            public void onComplete(final String filePath) {
                progressDialog.dismiss();
                    Filepath=filePath;
                if (filePath != null) {
                    btnPdfPath.setVisibility(View.VISIBLE);
                    btnPdfPath.setText("PDF path : " + filePath);
                    Toast.makeText(PdfCreationActivity.this, "pdf file " + filePath, Toast.LENGTH_LONG).show();

//
                    String ENCRYPTED_PDF = filePath;
                    String USER_PASSWORD = "user";
                    String OWNER_PASSWORD = "owner";

                    try {
                        //load an existing PDF
                        PDDocument document = PDDocument.load(new File(ENCRYPTED_PDF));
                        AccessPermission ap = new AccessPermission();
                        /** Setting access permissions */

                     //   Printing not allowed
                        ap.setCanPrint(false);
                      //  Copying not allowed
                        ap.setCanExtractContent(false);
                        ap.setReadOnly();

                        StandardProtectionPolicy sPP = new StandardProtectionPolicy("user","1234", ap);
                        sPP.setEncryptionKeyLength(128);
                        sPP.setPermissions(ap);
                        System.out.println("Encrypted succesfully!!!");
                        document.protect(sPP);
                        System.out.println("Encrypted succesfully!!!");
                        document.save(ENCRYPTED_PDF);
                        document.close();

//                        Intent i1 = new Intent(PdfCreationActivity.this, register.class);
//                        startActivity(i1);
                        Intent i1 = new Intent(PdfCreationActivity.this, Upload.class);
                        startActivity(i1);





//
                    } catch (IOException e) {

                        System.out.println("Error");
                    }

                    /* btnSharePdfFile.setVisibility(View.VISIBLE);
                    btnSharePdfFile.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sharePdf(filePath);
                        }
                    });*/

                }
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(PdfCreationActivity.this, "Error  " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


  /*  private void sharePdf(String fileName) {
        final Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        emailIntent.setType("text/plain");
        emailIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        ArrayList<Uri> uris = new ArrayList<Uri>();
        File fileIn = new File(fileName);
        Uri u = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID, fileIn);

        uris.add(u);
        emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
        try {
            startActivity(Intent.createChooser(emailIntent, getString(R.string.send_to)));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, getString(R.string.error_file), Toast.LENGTH_SHORT).show();
        }
    }
*/
}

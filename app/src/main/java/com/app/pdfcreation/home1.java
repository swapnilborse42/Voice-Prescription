package com.app.pdfcreation;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;

public class home1 extends AppCompatActivity {


    GridLayout mainGrid;
    private Context context;

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home1);

        mainGrid = (GridLayout) findViewById(R.id.maingrid);

        setSingleEvent(mainGrid);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setSingleEvent(GridLayout mainGrid) {

        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            //final int finalI1 = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // makeText(MainActivity.this, "Clicked at index" + finalI, LENGTH_SHORT).show();
                    if(finalI == 0)
                    {
//                        Intent intent= new Intent(MainActivity.this, ActivityOne.class);
//                        startActivity(intent);
                        startActivity(new Intent(getApplicationContext(), viewPDF.class));
                        Toast.makeText(home1.this, "1", Toast.LENGTH_SHORT).show();
                    }
                    else if(finalI == 1)
                    {
                         Intent intent= new Intent(home1.this,SpeechToText.class);
                        startActivity(intent);
                        Toast.makeText(home1.this, "2", Toast.LENGTH_SHORT).show();
                    }
                    else if(finalI == 2)
                    {
//                        Intent intent= new Intent(MainActivity.this, ActivityThree.class);
//                        startActivity(intent);
                        Toast.makeText(home1.this, "3", Toast.LENGTH_SHORT).show();
                    }
                    else if(finalI == 3)
                    {
//                        Intent intent= new Intent(MainActivity.this, ActivityFour.class);
//                        startActivity(intent);
                        Intent intent= new Intent(home1.this,downloadPDF.class);
                        startActivity(intent);
                        Toast.makeText(home1.this, "4", Toast.LENGTH_SHORT).show();
                    }
                    else if(finalI == 4)
                    {
//                        Intent intent= new Intent(MainActivity.this, ActivityFive.class);
//                        startActivity(intent);
                        Toast.makeText(home1.this, "5", Toast.LENGTH_SHORT).show();
                    }


                    else if(finalI == 5)
                    {
//                        Intent intent= new Intent(MainActivity.this, ActivitySix.class);
//                        startActivity(intent);
                        Toast.makeText(home1.this, "6", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }
    }
}



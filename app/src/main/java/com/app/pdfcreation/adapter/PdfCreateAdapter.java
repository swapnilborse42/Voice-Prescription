package com.app.pdfcreation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.pdfcreation.R;
import com.app.pdfcreation.SpeechToText;
import com.app.pdfcreation.model.PDFModel;
import com.app.pdfcreation.ui.PdfCreationActivity;

import java.util.List;

public class PdfCreateAdapter extends RecyclerView.Adapter<PdfCreateAdapter.MyViewHolder> {

    private List<PDFModel> pdfModels;

     String name="",symptoms="",diagnosis="",prescription="",advice="",mobview1="",emailview1="",dateview1="";
    private EditText nameview;
    private EditText symview;
    private EditText diagview;
    private EditText presview;
    private EditText adview;

    private EditText mobview;
    private EditText emailview;
    private TextView dateview;



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.register, parent, false);

        name= PdfCreationActivity.getActivityInstance().getData0();
        symptoms=PdfCreationActivity.getActivityInstance().getData11();
        diagnosis=PdfCreationActivity.getActivityInstance().getData21();
        prescription=PdfCreationActivity.getActivityInstance().getData31();
        advice=PdfCreationActivity.getActivityInstance().getData41();
        mobview1=PdfCreationActivity.getActivityInstance().getData51();
        emailview1=PdfCreationActivity.getActivityInstance().getData61();
        dateview1=PdfCreationActivity.getActivityInstance().getData71();



//        nameview.setText(name);
//        presview.setText(prescription);
//        diagview.setText(diagnosis);
//        adview.setText(advice);
//        symview.setText(symptoms);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PDFModel model = pdfModels.get(position);
        if (model != null) {
            if (model.isReceived()) {
                holder.mReceivedTV.setVisibility(View.VISIBLE);
            } else {
                holder.mReceivedTV.setVisibility(View.GONE);
            }

           // holder.mPriceTV.setText(model.getemail());
           // holder.mNameTV.setText(model.getName());
          //  int ratingDrawable = getRatingImage(model.getRating());
       //     holder.mRateIM.setImageResource(ratingDrawable);
        }
    }

    @Override
    public int getItemCount() {
        return pdfModels.size();
    }

    /**
     * This is set from PDFCreateByXML class
     * This is my own model. This model have to set data from api
     *
     * @param pdfModels
     */
    public void setListData(List<PDFModel> pdfModels) {
        this.pdfModels = pdfModels;
        notifyDataSetChanged();
    }

    /**
     * Set rating image
     *
     * @return
     */
  /*  private int getRatingImage(float rating) {
        int image = 0;
        if (rating == 0f) {
            image = R.drawable.pdf_star_1;
        } else if (rating == 0.5f) {
            image = R.drawable.pdf_half_star_2;
        } else if (rating == 1f) {
            image = R.drawable.pdf_star_2;
        } else if (rating == 1.5f) {
            image = R.drawable.pdf_half_star_3;
        } else if (rating == 2f) {
            image = R.drawable.pdf_star_3;
        } else if (rating == 2.5f) {
            image = R.drawable.pdf_half_star_4;
        } else if (rating == 3f) {
            image = R.drawable.pdf_star_4;
        } else if (rating == 3.5f) {
            image = R.drawable.pdf_half_star_5;
        } else if (rating == 4f) {
            image = R.drawable.pdf_star_5;
        } else if (rating == 4.5f) {
            image = R.drawable.pdf_half_star_6;
        } else if (rating == 5f) {
            image = R.drawable.pdf_star_6;
        }
        return image;
    }*/

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView mReceivedTV;
//        private final TextView mNameTV;
      //  private final ImageView mRateIM;
//        private final TextView mPriceTV;

        public MyViewHolder(View view) {
            super(view);
//            mPriceTV = (TextView) view.findViewById(R.id.reg_fullname);
            mReceivedTV = (TextView) view.findViewById(R.id.reg_email);

            nameview = (EditText) view.findViewById(R.id.nameview);
            presview = (EditText) view.findViewById(R.id.presview);
            diagview = (EditText) view.findViewById(R.id.diagview);
            adview = (EditText) view.findViewById(R.id.adview);
            symview = (EditText) view.findViewById(R.id.symview);
            mobview  = (EditText)view.findViewById(R.id.mobview);
            emailview  = (EditText)view.findViewById(R.id.emailview);
            dateview  = (TextView) view.findViewById(R.id.dateview);

            nameview.setText(name);
            presview.setText(prescription);
            diagview.setText(diagnosis);
            adview.setText(advice);
            symview.setText(symptoms);
            mobview.setText(mobview1);
            emailview.setText(emailview1);
            dateview.setText(dateview1);
//            mNameTV = (TextView) view.findViewById(R.id.reg_password);
           // mRateIM = (ImageView) view.findViewById(R.id.);
        }
    }

}
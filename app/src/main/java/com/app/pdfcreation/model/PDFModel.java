package com.app.pdfcreation.model;

import com.app.pdfcreation.utils.PDFCreationUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PDFModel {

    private boolean isPending;
    private boolean isReceived;
    private String email;
    private String name;
   // private float rating;

    public boolean isPending() {
        return isPending;
    }

    public void setPending(boolean pending) {
        isPending = pending;
    }

    public boolean isReceived() {
        return isReceived;
    }

    public void setReceived(boolean received) {
        isReceived = received;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    /**
     * Create dummy PDF model
     *
     * @return PDF Models
     */

    public static List<PDFModel> createDummyPdfModel() {
        PDFCreationUtils.filePath.clear();
        PDFCreationUtils.progressCount = 1;

        boolean isFirstReceivedItem = false;
        List<PDFModel> pdfModels = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Random rand = new Random();
            int email = rand.nextInt((1000 - 200) + 1) + 200;

            PDFModel model = new PDFModel();
            if (!isFirstReceivedItem) {
                model.setReceived(true);
                isFirstReceivedItem = true;
            } else {
                model.setReceived(false);
            }

          //  model.setemail("Cold");

           
             //   model.setName("Diwakar");
            

            
           // model.setRating(i % 3);
            pdfModels.add(model);
        }

        return pdfModels;
    }
}
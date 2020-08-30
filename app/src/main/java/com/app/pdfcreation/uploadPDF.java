package com.app.pdfcreation;

public class uploadPDF {
    public String name;
    public String url,url1;

    public uploadPDF()
    {

    }

    public uploadPDF(String name, String url, String url1) {
        this.name = name;
        this.url = url;
        this.url1=url1;
    }

    public String getName() {
        return name;
    }


    public String getUrl() {
        return url;
    }

    public String getUrl1() {
        return url1;
    }


    }


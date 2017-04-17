package com.ioffer.gediminas.ioffer_android;

/**
 * Created by Gediminas on 17/04/2017.
 */

public class Filter {

    private String title;
    private int min;
    private int max;
    private String category;
    private String county;

    public Filter(String title,int min ,int max,String category,String county){
        this.title = title;
        this.min = min;
        this.max = max;
        this.category = category;
        this.county = county;
    }

    public String getTitle(){
        return  this.title;
    }

    public String getCategory(){
        return this.category;
    }

    public int getMin(){
        return this.min;
    }

    public int getMax(){
        return this.max;
    }

    public String getCounty(){
        return this.county;
    }
}

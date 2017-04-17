package com.ioffer.gediminas.ioffer_android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class FilterProducts extends Activity{

    public static Filter productFilter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_product);

    }

    public void filter(View view) throws IOException, JSONException {

        MainActivity.filter = "filter";
        EditText title = (EditText) findViewById(R.id.product_name);
        String search_txt = title.getText().toString();
        EditText min = (EditText) findViewById(R.id.min_txt);
        EditText max = (EditText) findViewById(R.id.max_txt);

        Spinner category = (Spinner)findViewById(R.id.category);
        String category_text = category.getSelectedItem().toString();

        Spinner county = (Spinner)findViewById(R.id.county);
        String county_text = county.getSelectedItem().toString();

        String mind = min.getText().toString();

        if(mind.equals(""))
            mind = "0";

        String maxd = max.getText().toString();

        if(maxd.equals(""))
            maxd = "999999999";

        if(search_txt.matches("")) {
            search_txt="undefined";
        }

        productFilter = new Filter(search_txt,Integer.parseInt(mind),Integer.parseInt(maxd),category_text,county_text);

        MainActivity.search = search_txt;
        Intent myIntent = new Intent(FilterProducts.this, LikedActivity.class);
        startActivity(myIntent);


    }

    public void cancel(View view) {
        clearView();
    }

    private void clearView(){
        EditText title = (EditText) findViewById(R.id.product_name);
        EditText min = (EditText) findViewById(R.id.min_txt);
        EditText max = (EditText) findViewById(R.id.max_txt);

        Spinner category = (Spinner)findViewById(R.id.category);
        Spinner county = (Spinner)findViewById(R.id.county);

        category.setSelection(0); county.setSelection(0);
        title.setText(""); min.setText(""); max.setText("");
    }

}

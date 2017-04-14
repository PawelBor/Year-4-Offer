package com.ioffer.gediminas.ioffer_android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class LikedActivity extends Activity{

    ListView list;
    String[] web = null;
    String[] description = null ;
    String[] county = null ;
    public static List<List<Bitmap>> imageId = new ArrayList<List<Bitmap>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked);

        if(MainActivity.filter != null)
        {
            try{

                RequestService rs = new RequestService();
                List<Product> products = rs.getProductsByCat(MainActivity.filter);

                Log.i("TTG", products.get(0).getName().toLowerCase().toString());
                // Setting up lists size of the returned product list for the scroll item data.
                web = new String[products.size()];
                description = new String[products.size()];
                county = new String[products.size()];

                // Iterating through all the products and adding their description & bitmap
                // to the relative arrays to be displayed on the main activity.
                for (int i = 0; i < products.size(); i++) {
                    web[i] = products.get(i).getName();
                    description[i] = Double.toString(products.get(i).getPrice());

                    // All images for the current product
                    List<String> image_list = products.get(i).getImage();

                    // Temp list to store all the iterated images for the current product.
                    List<Bitmap> product_images = new ArrayList<Bitmap>();

                    // Loop returned list of image strings, decode them and add them to the Bitmap list.
                    for (String image : image_list) {
                        Bitmap decoded_img = decodeBase64(image);
                        product_images.add(decoded_img);
                    }

                    imageId.add(product_images);
                    county[i] = products.get(i).getCounty();
                }

                MainActivity.filter = null; // Clear the filter after completion.
            }catch (JSONException jsx)
            {
                Log.i("EX_JSON", jsx.toString());
            }

        }



        CustomList adapter = new
                CustomList(LikedActivity.this, web,description, county, imageId);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(LikedActivity.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

                Intent myIntent = new Intent(LikedActivity.this, ItemActivity.class);
                startActivity(myIntent);
            }
        });
    }

    // Decode the returned Base64 encoded string back to a Bitmap
    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

}

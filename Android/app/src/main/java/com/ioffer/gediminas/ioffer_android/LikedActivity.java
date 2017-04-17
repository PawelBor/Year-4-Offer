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

    public static ListView list;
    public static List<List<Bitmap>> imageId = new ArrayList<List<Bitmap>>();
    public static String[] web = null;
    public static String[] contact = null;
    public static String[] description = null;
    public static String[] real_description = null;
    public static String[] county = null;
    public static String filter = null;
    public static int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        web= null;
        description = null;
        county= null;
        imageId.clear();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked);

        if(MainActivity.filter != null)
        {
            try{

                List<Product> products;

                RequestService rs = new RequestService();
                if(MainActivity.filter.equals("search")) {
                    products = rs.getProductsBySearch(MainActivity.search,0 , 999999999, "undefined","undefined");
                }else if(MainActivity.filter.equals("filter")){
                    Filter x = FilterProducts.productFilter;

                    String title = x.getTitle();
                    if(title.equals(""))
                        title = "undefined";

                    products = rs.getProductsBySearch(title,x.getMin() , x.getMax(), x.getCategory(),x.getCounty());
                }else {
                    products = rs.getProductsByCat(MainActivity.filter);
                }


                // Setting up lists size of the returned product list for the scroll item data.
                web = new String[products.size()];
                description = new String[products.size()];
                county = new String[products.size()];
                real_description = new String[products.size()];
                contact = new String[products.size()];

                // Iterating through all the products and adding their description & bitmap
                // to the relative arrays to be displayed on the main activity.
                for (int i = 0; i < products.size(); i++) {
                    web[i] = products.get(i).getName();
                    description[i] = Double.toString(products.get(i).getPrice());
                    real_description[i] =  products.get(i).getDescription();
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
                    contact[i] = products.get(i).getMobileNo();
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

                pos = position;

                Intent myIntent = new Intent(LikedActivity.this, ItemActivityFiltered.class);
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

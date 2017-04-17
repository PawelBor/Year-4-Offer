package com.ioffer.gediminas.ioffer_android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends Activity{


    public static ListView list;
    public static List<List<Bitmap>> imageId = new ArrayList<List<Bitmap>>();
    public static String[] web = null;
    public static String[] description = null;
    public static String[] county = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);

        String email2;

        if(pref.getLong("logged_in", 0) == 1){
            email2 = pref.getString("email", "");

            RequestService requestService = new RequestService();

            try {
                List<Product> products = requestService.getProductsByAuthor(email2);

                if(products != null){

                    // Setting up lists size of the returned product list for the scroll item data.
                    MainActivity.web = new String[products.size()];
                    MainActivity.description = new String[products.size()];
                    MainActivity.county = new String[products.size()];
                    MainActivity.real_description = new String[products.size()];

                    // Iterating through all the products and adding their description & bitmap
                    // to the relative arrays to be displayed on the main activity.
                    for (int i = 0; i < products.size(); i++) {
                        MainActivity.web[i] = products.get(i).getName();
                        MainActivity.description[i] = Double.toString(products.get(i).getPrice());

                        // All images for the current product
                        List<String> image_list = products.get(i).getImage();

                        // Temp list to store all the iterated images for the current product.
                        List<Bitmap> product_images = new ArrayList<Bitmap>();

                        // Loop returned list of image strings, decode them and add them to the Bitmap list.
                        for (String image : image_list) {
                            Bitmap decoded_img = decodeBase64(image);
                            product_images.add(decoded_img);
                        }

                        MainActivity.imageId.clear();
                        MainActivity.real_description[i]= products.get(i).getDescription();
                        MainActivity.imageId.add(product_images);
                        MainActivity.county[i] = products.get(i).getCounty();

                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            CustomList adapter = new
                    CustomList(ProfileActivity.this, MainActivity.web,MainActivity.description, MainActivity.county, MainActivity.imageId);
            list=(ListView)findViewById(R.id.lister);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Toast.makeText(ProfileActivity.this, "You Clicked at " +MainActivity.web[+ position], Toast.LENGTH_SHORT).show();

                    MainActivity.pos = position;
                    Intent myIntent = new Intent(ProfileActivity.this, ItemActivity.class);
                    startActivity(myIntent);
                }
            });

        }



        TextView name =(TextView)findViewById(R.id.name);
        name.setText("Name: "+LoginActivity.Name);

        TextView email =(TextView)findViewById(R.id.surname);
        email.setText("Email: "+LoginActivity.Email);

        TextView county =(TextView)findViewById(R.id.county);
        county.setText("Profile Status: Public");
    }

    // Decode the returned Base64 encoded string back to a Bitmap
    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    public void logout(View view) throws JSONException, NoSuchAlgorithmException, UnsupportedEncodingException {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        editor.remove("logged_in");
        editor.commit();


        Intent myIntent = new Intent(ProfileActivity.this, LoginActivity.class);
        startActivity(myIntent);
    }

    public void post(View view) throws JSONException, NoSuchAlgorithmException, UnsupportedEncodingException {
        Intent myIntent = new Intent(ProfileActivity.this, CreateProduct.class);
        startActivity(myIntent);
    }

}

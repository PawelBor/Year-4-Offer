package com.ioffer.gediminas.ioffer_android;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Debug;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static ListView list;
    public static List<List<Bitmap>> imageId = new ArrayList<List<Bitmap>>();
    public static String[] web = null;
    public static String[] contact = null;
    public static String[] description = null;
    public static String[] real_description = null;
    public static String[] county = null;
    public static String filter = null;
    public static int pos;
    public static String search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Allow for the use of the GUI thread in requests.
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        populate_product_lists();

        CustomList adapter = new CustomList(MainActivity.this, web, description, county, imageId);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                pos = position;

                Intent myIntent = new Intent(MainActivity.this, ItemActivity.class);
                startActivity(myIntent);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }


    private void populate_product_lists() {

        // Creating a service object for getting and parsing the api data.
        RequestService requestService = new RequestService();

        try {
            // Getting a list of all product objects from the web api.
            List<Product> products =  requestService.getAllProducts();

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
                real_description[i] = products.get(i).getDescription();
                contact[i] = products.get(i).getMobileNo();
            }
        }
        catch (JSONException e) {Log.i("error",e.toString());e.printStackTrace();}
    }

    // Decode the returned Base64 encoded string back to a Bitmap
    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_map) {
            Intent myIntent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(myIntent);
        } else if (id == R.id.nav_account) {
            Intent myIntent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(myIntent);
        } else if (id == R.id.nav_liked) {
            Intent myIntent = new Intent(MainActivity.this, LikedActivity.class);
            startActivity(myIntent);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }else if (id == R.id.nav_login) {
            Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(myIntent);
        }
        else if (id == R.id.nav_logout) {
            Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(myIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void electronics(View view) {
        filter = "electronics";
        Intent myIntent = new Intent(MainActivity.this, LikedActivity.class);
        startActivity(myIntent);
    }

    public void food(View view) {
        filter = "food";
        Intent myIntent = new Intent(MainActivity.this, LikedActivity.class);
        startActivity(myIntent);
    }

    public void automotive(View view) {
        filter = "automotive";
        Intent myIntent = new Intent(MainActivity.this, LikedActivity.class);
        startActivity(myIntent);
    }

    public void entertainment(View view) {
        filter = "entertainment";
        Intent myIntent = new Intent(MainActivity.this, LikedActivity.class);
        startActivity(myIntent);
    }

    public void leisure(View view) {
        filter = "leisure";
        Intent myIntent = new Intent(MainActivity.this, LikedActivity.class);
        startActivity(myIntent);
    }

    public void other(View view) {
        filter = "other";
        Intent myIntent = new Intent(MainActivity.this, LikedActivity.class);
        startActivity(myIntent);
    }

    public void search(View view) {
        filter = "search";
        EditText editText = (EditText)findViewById(R.id.editText1);
        String search_txt = editText.getText().toString();

        if(!search_txt.matches("")) {
            search = search_txt;
            Intent myIntent = new Intent(MainActivity.this, LikedActivity.class);
            startActivity(myIntent);
        }else
            Toast.makeText(MainActivity.this, "Enter a search word",
                    Toast.LENGTH_SHORT).show();
    }
}

package com.ioffer.gediminas.ioffer_android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ProfileActivity extends Activity{


    ListView list;
    String[] web = {
            "Clutch, in brand new condition Price: $$$.",
            "Renault Clio, low mileage, pure clean diesel. Must go! Price: $$$",
            "IPhone 4, decent condition. Price: $$$",
            "Clutch, in brand new condition Price: $$$.",
            "Renault Clio, low mileage, pure clean diesel. Must go! Price: $$$",
            "IPhone 4, decent condition. Price: $$$",
    } ;
    Bitmap[] imageId = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        CustomList adapter = new
                CustomList(ProfileActivity.this, web, imageId);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(ProfileActivity.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(ProfileActivity.this, ItemActivity.class);
                startActivity(myIntent);

            }
        });
    }

}

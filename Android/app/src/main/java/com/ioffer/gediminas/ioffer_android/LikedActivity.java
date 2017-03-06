package com.ioffer.gediminas.ioffer_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class LikedActivity extends Activity{

    ListView list;
    String[] web = {
            "Clutch, in brand new condition Price: $$$.",
            "Renault Clio, low mileage, pure clean diesel. Must go! Price: $$$",
            "IPhone 4, decent condition. Price: $$$",
            "Clutch, in brand new condition Price: $$$.",
            "Renault Clio, low mileage, pure clean diesel. Must go! Price: $$$",
            "IPhone 4, decent condition. Price: $$$",
    } ;

    Integer[] imageId = {
            R.drawable.clutch,
            R.drawable.clio,
            R.drawable.iphone,
            R.drawable.ic_menu_camera,
            R.drawable.ic_menu_camera,
            R.drawable.ic_menu_camera,
            R.drawable.ic_menu_camera

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked);

        CustomList adapter = new
                CustomList(LikedActivity.this, web, imageId);
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

}

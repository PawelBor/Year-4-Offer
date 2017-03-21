package com.ioffer.gediminas.ioffer_android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class LikedActivity extends Activity{

    ListView list;
    String[] web = {} ;
    String[] description = {} ;
    String[] county = {} ;
    public static List<Bitmap> imageId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked);

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

}

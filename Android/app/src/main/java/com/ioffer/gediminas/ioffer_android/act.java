package com.ioffer.gediminas.ioffer_android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class act extends Activity{


    ListView list;
    String[] web = {} ;
    String[] description = {} ;
    public static List<List<Bitmap>> imageId = null;
    String[] county = {} ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act);

        CustomList adapter = new
                CustomList(act.this, web, description, county, imageId);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(act.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(act.this, ItemActivity.class);
                startActivity(myIntent);

            }
        });


        TextView name =(TextView)findViewById(R.id.name);
        name.setText("Name: "+LoginActivity.Name);

        TextView email =(TextView)findViewById(R.id.surname);
        email.setText("Email: "+LoginActivity.Email);

        TextView county =(TextView)findViewById(R.id.county);
        county.setText("Profile Status: Public");
    }

    public void logout(View view) throws JSONException, NoSuchAlgorithmException, UnsupportedEncodingException {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        editor.remove("logged_in");
        editor.commit();

        Intent myIntent = new Intent(act.this, LoginActivity.class);
        startActivity(myIntent);
    }

}

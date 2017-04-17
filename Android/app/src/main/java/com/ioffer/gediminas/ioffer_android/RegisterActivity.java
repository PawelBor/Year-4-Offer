package com.ioffer.gediminas.ioffer_android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegisterActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void register(View view)throws NoSuchAlgorithmException,JSONException,java.io.IOException{
        TextView name_txt = (TextView)findViewById(R.id.name);
        TextView email_txt = (TextView)findViewById(R.id.email);
        TextView password_txt = (TextView)findViewById(R.id.password);

        String name = name_txt.getText().toString();
        String email = email_txt.getText().toString().replaceAll("\\s+","");
        String password = sha256(password_txt.getText().toString());

        RequestService rs = new RequestService();
        boolean isInserted = rs.postUser(name,email,password);

        if(!isInserted)
            Toast.makeText(RegisterActivity.this, "Client exists",
                    Toast.LENGTH_SHORT).show();
        else{
            Intent myIntent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(myIntent);
        }
    }


    public static String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}

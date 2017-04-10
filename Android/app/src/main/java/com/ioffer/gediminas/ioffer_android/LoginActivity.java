package com.ioffer.gediminas.ioffer_android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends Activity{

    public static String Name = "";
    public static String Email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);


        if(pref.getLong("logged_in", 0) == 1){
            Name = pref.getString("name", "");
            Email = pref.getString("email", "");

            Intent myIntent = new Intent(LoginActivity.this, ProfileActivity.class);
            startActivity(myIntent);
        }
    }


    public void register(View view) throws JSONException, NoSuchAlgorithmException, UnsupportedEncodingException {
        Intent myIntent = new Intent(LoginActivity.this, act.class);
        startActivity(myIntent);
    }


        public void login(View view) throws JSONException, NoSuchAlgorithmException, UnsupportedEncodingException {

        EditText editText = (EditText) findViewById(R.id.password_text);
        String password = editText.getText().toString();

        EditText editText_user = (EditText) findViewById(R.id.username_text);
        String username = editText_user.getText().toString();

        RequestService rs = new RequestService();



        String response = rs.loginClient(username, sha256(password));

        if(response != null && response != "")
        {
            String db_pass = json_parse_client(response);

            if(db_pass.equals(sha256(password)))
            {
                Toast.makeText(this, "LOGGED IN",
                        Toast.LENGTH_LONG).show();

                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();

                //on the login store the login
                editor.putLong("logged_in", 1);
                editor.putString("name", Name);
                editor.putString("email", Email);
                editor.commit();

                Intent myIntent = new Intent(LoginActivity.this, ProfileActivity.class);
                startActivity(myIntent);
            }else{
                Toast.makeText(this, "WRONG PASSWORD",
                        Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(this, "WRONG EMAIL OR PASSWORD",
                    Toast.LENGTH_LONG).show();
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

    private String json_parse_client(String response) throws JSONException {
        JSONObject x = new JSONObject(response);
        Name = x.getString("name");
        Email = x.getString("email");
        return x.getString("password");
    }

}

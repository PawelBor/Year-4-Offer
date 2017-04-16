package com.ioffer.gediminas.ioffer_android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class CreateProduct extends Activity{

    private static int RESULT_LOAD_IMAGE = 1;
    Bitmap product = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

    }

    public void select_image(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra("outputX", 256);
        intent.putExtra("outputY", 256);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 1);
    }

    public void create(View view) throws IOException, JSONException {
        EditText title = (EditText) findViewById(R.id.product_name);
        EditText price = (EditText) findViewById(R.id.price_txt);
        EditText description = (EditText) findViewById(R.id.desc_txt);
        EditText mobile = (EditText) findViewById(R.id.mobile);

        Spinner category = (Spinner)findViewById(R.id.category);
        String category_text = category.getSelectedItem().toString();

        Spinner county = (Spinner)findViewById(R.id.county);
        String county_text = county.getSelectedItem().toString();


        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);

        String email = "";
        if(pref.getLong("logged_in", 0) == 1){
            email = pref.getString("email", "");
        }

        RequestService rs = new RequestService();

        String isInserted =
                rs.postProduct(title.getText().toString(), price.getText().toString(), description.getText().toString(),
                        mobile.getText().toString(), category_text, county_text, encodeTobase64(product),email);

        if(isInserted == "")
            Toast.makeText(CreateProduct.this, "Failed",
                    Toast.LENGTH_SHORT).show();
        else{
            Toast.makeText(CreateProduct.this, "Success",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public static String encodeTobase64(Bitmap image)
    {
        Bitmap immagex=image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b,Base64.DEFAULT);
        return imageEncoded;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == 1) {
            final Bundle extras = data.getExtras();
            if (extras != null) {
                //Get image
                product = extras.getParcelable("data");
            }

            Button button_image = (Button) findViewById(R.id.button_image);
            button_image.setBackgroundColor(Color.LTGRAY);
            button_image.setText("Change Image");
        }
    }

    public void cancel(View view) {
        clearView();
    }

    private void clearView(){
        EditText title = (EditText) findViewById(R.id.product_name);
        EditText price = (EditText) findViewById(R.id.price_txt);
        EditText description = (EditText) findViewById(R.id.desc_txt);
        EditText mobile = (EditText) findViewById(R.id.mobile);

        title.setText(""); price.setText(""); description.setText(""); mobile.setText("");

        Button button_image = (Button) findViewById(R.id.button_image);
        button_image.setBackgroundColor(Color.RED);
        button_image.setText("SELECT IMAGE");

        product = null;
    }


}

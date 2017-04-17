package com.ioffer.gediminas.ioffer_android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemActivityFiltered extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        if(populate_page()){
            // Continue
        }

    }

    // Open dialer with the phone number from the button text.
    public void call_click(View view) {
        TextView call_btn = (TextView)findViewById(R.id.call_btn);

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+call_btn.getText()));
        startActivity(intent);
    }

    public void delete_click(View view) throws IOException, JSONException {
        RequestService rs = new RequestService();
        boolean success = rs.deleteProduct(LikedActivity.productId[LikedActivity.pos]);

        if(!success) {
            Toast.makeText(ItemActivityFiltered.this, "Error Please try again",
                    Toast.LENGTH_SHORT).show();
        }else{
            Intent myIntent = new Intent(ItemActivityFiltered.this, ProfileActivity.class);
            startActivity(myIntent);
        }
    }

    private boolean populate_page() {

        try{

            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);

            String email = "";

            if(pref.getLong("logged_in", 0) == 1){
                email = pref.getString("email", "");
            }
            Button delete_btn = (Button)findViewById(R.id.delete_button);

            if(email.equals(LikedActivity.author[LikedActivity.pos])){
                delete_btn.setVisibility(View.VISIBLE);
            }else{
                delete_btn.setVisibility(View.GONE);
            }

            // Get a handle on the description textView and set it to relevant description.
            TextView description = (TextView)findViewById(R.id.description);
            description.setText(LikedActivity.real_description[LikedActivity.pos]);

            TextView title = (TextView)findViewById(R.id.title);
            title.setText(LikedActivity.web[LikedActivity.pos]);

            // Can specify any decimal places.
            int decimalPlace = 0;
            BigDecimal bd = new BigDecimal((Double.parseDouble(LikedActivity.description[LikedActivity.pos])));
            bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);

            TextView price = (TextView)findViewById(R.id.price);
            price.setText("€"+ bd);

            TextView county = (TextView)findViewById(R.id.county);
            county.setText(LikedActivity.county[LikedActivity.pos]);

            TextView call_btn = (TextView)findViewById(R.id.call_btn);
            call_btn.setText(LikedActivity.contact[LikedActivity.pos]);

            ImageView imageView = (ImageView)findViewById(R.id.myImageView);
            // Setting the current image from the Bitmap array
            List<Bitmap> x = new ArrayList<>();
            x = LikedActivity.imageId.get(LikedActivity.pos);
            imageView.setImageBitmap(x.get(0));

        }catch(Exception x){return false;}

        return true;
    }
}

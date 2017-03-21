package com.ioffer.gediminas.ioffer_android;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;

// Altered from:
// https://www.learn2crack.com/2013/10/android-custom-listview-images-text-example.html

public class CustomList extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] web;
    private final String[] desc;
    private final String[] county;
    private final Bitmap[] list_image;

    public CustomList(Activity context,
                      String[] web,String[] desc,String[] county, Bitmap[] list_image) {
        super(context, R.layout.mylist, web);
        this.context = context;
        this.web = web;
        this.list_image = list_image;
        this.desc = desc;
        this.county = county;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.mylist, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        // Limit the size of the title
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);

        if(web[position].length() > 21)
            txtTitle.setText( web[position].substring(0,21));
        else
            txtTitle.setText(web[position]);

        // Get a handle on the description textView and set it to relevant description.
        TextView description = (TextView) rowView.findViewById(R.id.description);

        // Can specify any decimal places.
        int decimalPlace = 0;
        BigDecimal bd = new BigDecimal((Double.parseDouble(desc[position])));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);

        description.setText("€"+ bd);

        TextView county_view = (TextView) rowView.findViewById(R.id.county);
        county_view.setText(county[position]);

        // Setting the current image from the Bitmap array
        imageView.setImageBitmap(list_image[position]);

        return rowView;
    }
}
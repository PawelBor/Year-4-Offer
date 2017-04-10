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
import java.util.ArrayList;
import java.util.List;

// Altered from:
// https://www.learn2crack.com/2013/10/android-custom-listview-images-text-example.html

public class CustomList extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] web;
    private final String[] desc;
    private final String[] county;
    private final List<List<Bitmap>> list_image;

    public CustomList(Activity context,
                      String[] web,String[] desc,String[] county, List<List<Bitmap>> list_image) {
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


        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);


        // Setting the current image from the Bitmap array
        // Setting it as the first one in the list
        if(list_image.size() > 0)
        {
            List<Bitmap> current_product_images = list_image.get(position);
            Bitmap last_bitmap = current_product_images.get(0);
            imageView.setImageBitmap(last_bitmap);
        }

        return rowView;
    }
}
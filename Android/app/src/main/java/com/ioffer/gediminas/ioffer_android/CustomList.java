package com.ioffer.gediminas.ioffer_android;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;



// Referenced from https://www.learn2crack.com/2013/10/android-custom-listview-images-text-example.html

public class CustomList extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] web;
    private final Bitmap[] list_image;

    public CustomList(Activity context,
                      String[] web, Bitmap[] list_image) {
        super(context, R.layout.mylist, web);
        this.context = context;
        this.web = web;
        this.list_image = list_image;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.mylist, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(web[position]);

        // Setting the current image from the Bitmap array
        imageView.setImageBitmap(list_image[position]);

        return rowView;
    }
}
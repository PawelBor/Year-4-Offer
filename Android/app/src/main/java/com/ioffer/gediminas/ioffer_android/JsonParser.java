package com.ioffer.gediminas.ioffer_android;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Gediminas on 21/03/2017.
 */

public class JsonParser {

    // Exposing the parse json products method
    public List<Product> getParsedProducts(String json_products)
    {
        List<Product> product_list;

        try{
            product_list = parse_json_products(json_products);
            return product_list;
        }catch(JSONException y){
            Log.i("ERROR" ,y.toString()); return null;}
    }

    // Exposing the parse json products method
    public List<Product> getParsedProductsCat(String json_products)
    {
        List<Product> product_list;

        try{
            product_list = parse_json_productsCat(json_products);
            return product_list;
        }catch(JSONException y){
            Log.i("ERROR" ,y.toString()); return null;}
    }

    private List<Product> parse_json_productsCat(String json_products) throws JSONException {

        // Create a json object array from the json string.
        JSONArray product_array = new JSONArray(json_products);

        // Create a list for the returned product list after parsing the json.
        List<Product> product_list = new ArrayList<>();

        // Looping over every product in the product array
        for(int i = 0; i < product_array.length(); i++){

            // Creating a json object of the current object
            JSONObject json_product_object = product_array.getJSONObject(i);

            // Creating a new Product and calling it's constructor.
            Product product = new Product(json_product_object.getString("name"),
                    json_product_object.getString("description"));

            // Populating the product object details.
            product.setPrice(json_product_object.getDouble("price"));
            product.setCounty(json_product_object.getString("county"));
            product.setAuthor(json_product_object.getString("author"));
            product.setCategory(json_product_object.getString("category"));
            product.setProductId(json_product_object.getString("productId"));
            product.setMobileNo(json_product_object.getString("mobileNo"));

            product.setLocation(
                    (float)json_product_object.getJSONObject("location").getDouble("latitude"),
                    (float)json_product_object.getJSONObject("location").getDouble("longitude"));


            List<String> image_list = new ArrayList<String>();

            List<String> resultList =  parse_csv_string(json_product_object.getString("image"));
            product.setImage(resultList);

            // Populate the product list.
            product_list.add(product);
        }

        return product_list;
    }

    private List<String> parse_csv_string(String imagesCSV){
        return Arrays.asList(imagesCSV.split("\\s*,\\s*"));
    }

    private List<Product> parse_json_products(String json_products) throws JSONException {

        // Create a json object array from the json string.
        JSONArray product_array = new JSONArray(json_products);

        // Create a list for the returned product list after parsing the json.
        List<Product> product_list = new ArrayList<>();

        // Looping over every product in the product array
        for(int i = 0; i < product_array.length(); i++){

            // Creating a json object of the current object
            JSONObject json_product_object = product_array.getJSONObject(i);

            // Creating a new Product and calling it's constructor.
            Product product = new Product(json_product_object.getString("name"),
                    json_product_object.getString("description"));

            // Populating the product object details.
            product.setPrice(json_product_object.getDouble("price"));
            product.setCounty(json_product_object.getString("county"));
            product.setAuthor(json_product_object.getString("author"));
            product.setCategory(json_product_object.getString("category"));
            product.setProductId(json_product_object.getString("productId"));
            product.setMobileNo(json_product_object.getString("mobileNo"));

            product.setLocation(
                    (float)json_product_object.getJSONObject("location").getDouble("latitude"),
                    (float)json_product_object.getJSONObject("location").getDouble("longitude"));

            // Getting the image array
            JSONArray image_array = json_product_object.getJSONArray("images");
            List<String> image_list = new ArrayList<String>();

            // Looping over every image in the images JsonArrayObject
            for(int x = 0; x < image_array.length(); x++){
                image_list.add(image_array.optString(x));
            }

            product.setImage(image_list);

            // Populate the product list.
            product_list.add(product);
        }

        return product_list;
    }
}

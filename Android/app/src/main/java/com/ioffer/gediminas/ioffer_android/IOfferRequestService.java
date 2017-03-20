package com.ioffer.gediminas.ioffer_android;

import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.x;

/**
 * Created by Gediminas on 20/03/2017.
 */

public class IOfferRequestService {

    private static final String ALL_PRODUCTS_URL =
            "http://54.244.40.167:8080/service/webapi/products";

    public List<Product> getAllProducts() throws JSONException {

        // Request all the products as json.
        String json_products = request_content(ALL_PRODUCTS_URL);

        // List of product objects returned after parsing the json.
        List<Product> all_products = parse_json_products(json_products);

        return all_products;
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
            product.setImage(json_product_object.getString("image"));
            product.setPrice(json_product_object.getDouble("price"));
            product.setCounty(json_product_object.getString("county"));
            product.setAuthor(json_product_object.getString("author"));
            product.setCategory(json_product_object.getString("category"));
            product.setProductId(json_product_object.getString("productId"));
            product.setMobileNo(json_product_object.getString("mobileNo"));
            product.setLocation((float)json_product_object.getJSONObject("location").getDouble("latitude"),
                    (float)json_product_object.getJSONObject("location").getDouble("longitude"));

            // Populate the product list.
            product_list.add(product);
        }

        return product_list;
    }

    // Make a HTTP GET request to the ioffer Web Api and return all products
    // currently located in the mongodb in json.
    private String request_content(String url){

        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget= new HttpGet(url);

        String server_response = null;

        try {
            HttpResponse response = httpclient.execute(httpget);

            if(response.getStatusLine().getStatusCode() == 200){
                server_response = EntityUtils.toString(response.getEntity());
            } else {
                Log.i("Server response", "Failed to get server response" );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return server_response;
    }
}

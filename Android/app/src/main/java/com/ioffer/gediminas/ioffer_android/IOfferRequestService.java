package com.ioffer.gediminas.ioffer_android;

import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gediminas on 20/03/2017.
 */

public class IOfferRequestService {

    private static final String ALL_PRODUCTS_URL =
            "http://54.244.40.167:8080/service/webapi/products";

    public String getAllProducts(){

        // Request all the products as json.
        String json_products = request_content(ALL_PRODUCTS_URL);

        // Create a list for the returned product list after parsing the json.
        List<Product> all_products = new ArrayList<>();

        // Parse the json string abd return a list of products.
        all_products = parse_json_products(json_products);

        return null;
    }

    private List<Product> parse_json_products(String json_products) {


        return null;
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

package com.ioffer.gediminas.ioffer_android;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by Gediminas on 20/03/2017.
 */

public class RequestService {

    private static final String ALL_PRODUCTS_URL =
            "http://54.244.40.167:8080/service/webapi/products_android";

    public List<Product> getAllProducts() throws JSONException {

        // Request all the products as json.
        String json_products = request_content(ALL_PRODUCTS_URL);

        JsonParser json_parser = new JsonParser();

        // List of product objects returned after parsing the json.
        List<Product> all_products = json_parser.getParsedProducts(json_products);

        return all_products;
    }



    // HTTP GET request using string uri and producing a string response
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

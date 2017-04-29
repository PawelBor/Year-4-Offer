package com.ioffer.gediminas.ioffer_android;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.plus.model.people.Person;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Gediminas on 20/03/2017.
 */

public class RequestService implements LocationListener{

    private static final String ALL_PRODUCTS_URL =
            "http://52.31.25.47:8080/service/webapi/products_android";

    private static final String CLIENT_LOGIN_URL =
            "http://52.31.25.47:8080/service/webapi/user/";

    private static final String PRODUCT_BY_CATEGORY =
            "http://52.31.25.47:8080/service/webapi/product/category/";

    private static final String PRODUCT_BY_SEARCH =
                "http://52.31.25.47:8080/service/webapi/products/";

    private static final String PRODUCT_BY_AUTHOR =
            "http://52.31.25.47:8080/service/webapi/product/author/";

    private static final String DELETE_PRODUCT =
            "http://52.31.25.47:8080/service/webapi/product/";


    public List<Product> getAllProducts() throws JSONException {

        // Request all the products as json.
        String json_products = request_content(ALL_PRODUCTS_URL);

        JsonParser json_parser = new JsonParser();

        // List of product objects returned after parsing the json.
        List<Product> all_products = json_parser.getParsedProducts(json_products);

        return all_products;
    }

    public List<Product> getProductsByAuthor(String author) throws JSONException {

        // Request all the products as json.
        String json_products = request_content(PRODUCT_BY_AUTHOR+author);

        Log.i("dfsddsfssfsdfsd",PRODUCT_BY_AUTHOR+author);

        JsonParser json_parser = new JsonParser();

        // List of product objects returned after parsing the json.
        List<Product> all_products = json_parser.getParsedProductsCat(json_products);

        return all_products;
    }

    public boolean postUser(String name,String email,String password) throws IOException, JSONException {

        HttpClient httpclient = new DefaultHttpClient();
        String mUrl = "http://34.209.10.185:8080/service/webapi/user";
        HttpPost httppost = new HttpPost(mUrl);
        httppost.addHeader("Content-Type", "application/json");
        httppost.setHeader(HTTP.CONTENT_TYPE,
                "application/x-www-form-urlencoded;charset=UTF-8");

        StringEntity se = new StringEntity("name="+name+"&email="+email+"&password="+password,"UTF-8");
        httppost.setEntity(se);
        HttpResponse response = httpclient.execute(httppost);
        if (response != null) {
            String result = EntityUtils.toString(response.getEntity());
            Log.i("result",result.toString());
            return Boolean.parseBoolean(result);
        }
        return false;
    }


    public String postProduct(String title,String price,String description,String mobile,
        String category_text,String county_text,String base64Image, String email, Location x) throws IOException, JSONException {

        HttpClient httpclient = new DefaultHttpClient();
        String mUrl = "http://34.209.10.185:8080/service/webapi/post_product";
        HttpPost httppost = new HttpPost(mUrl);
        httppost.addHeader("Content-Type", "application/json");
        httppost.setHeader(HTTP.CONTENT_TYPE,
                "application/x-www-form-urlencoded;charset=UTF-8");



        String locationCSV = x.getLatitude()+" "+ x.getLongitude();

        StringEntity se = new StringEntity("name="+title+"&author="+email+"&category="+category_text+
                "&county="+county_text+"&description="+description+"&location="+locationCSV+"&price="+price+
                "&image="+ URLEncoder.encode(base64Image,"UTF-8")+"&mobileNo="+mobile,"UTF-8");

        httppost.setEntity(se);
        HttpResponse response = httpclient.execute(httppost);
        if (response != null) {
            String result = EntityUtils.toString(response.getEntity());
            Log.i("result",result.toString());
            return result;
        }

        return null;
    }

    public boolean deleteProduct(String id) throws IOException, JSONException {

        HttpClient httpclient = new DefaultHttpClient();
        String mUrl = DELETE_PRODUCT+id;
        HttpDelete httppost = new HttpDelete(mUrl);
        httppost.addHeader("Content-Type", "application/json");
        httppost.setHeader(HTTP.CONTENT_TYPE,
                "application/x-www-form-urlencoded;charset=UTF-8");

        HttpResponse response = httpclient.execute(httppost);
        if (response != null) {
            String result = EntityUtils.toString(response.getEntity());
            Log.i("result",result.toString());
            return Boolean.parseBoolean(result);
        }
        return false;
    }

    public List<Product> getProductsBySearch
            (String name, int minPrice, int maxPrice, String category, String county) throws JSONException {

        if(name.contains(" ")){
            int i = name.indexOf(' ');
            name = name.substring(0, i); // get the first word for the search
        }

        // Request all the produmcts as json.
        String json_products = request_content(PRODUCT_BY_SEARCH+ name+"/"+minPrice+"/"+maxPrice+"/"+
        category+"/"+county);

        JsonParser json_parser = new JsonParser();

        // List of product objects returned after parsing the json.
        List<Product> all_products = json_parser.getParsedProductsCat(json_products);

        return all_products;
    }

    public List<Product> getProductsByCat(String category) throws JSONException {

        // Request all the products as json.
        String json_products = request_content(PRODUCT_BY_CATEGORY+ category);

        JsonParser json_parser = new JsonParser();

        // List of product objects returned after parsing the json.
        List<Product> all_products = json_parser.getParsedProductsCat(json_products);

        return all_products;
    }

    public String loginClient(String username, String password) throws JSONException {

        // Request all the products as json.
        String json_login = request_content(CLIENT_LOGIN_URL+username);

        return json_login;
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

    @Override
    public void onLocationChanged(Location location) {

    }
}

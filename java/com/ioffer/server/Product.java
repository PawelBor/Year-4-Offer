/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ioffer.server;

import java.util.*;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

/**
 *
 * @author Niks
 */
public class Product {
    // MongoDB connection variables
    private MongoClient mongo = new MongoClient("localhost", 27017);
    private DB db = mongo.getDB("ioffer");
    private DBCollection table = db.getCollection("products");
    
    // Member variables for all products
    private String name;
    private double price;
    private String description;
    private String[] images;
    private Location location;
    private String county;
    private String author;
    private String category;
    
    public Product (){
        super();
    }
    
    public Product (String name, String price, String description, float lat, float lon, String county, String author, String category){
        this();
        this.name = name;
        this.price = Double.parseDouble(price);
        this.description = description;
        // Images here
        
        this.location = new Location(lat, lon);
        this.county = county;
        this.author = author;
        this.category = category;
    }
    
    //byte image[] = (byte[]) result;
    
    public String encodeImage(byte[] image){
        Base64Encoder enc = new Base64Encoder(image);
        enc.encode();
        
        return enc.getEncoded();
    }
    
    // Create a new product and add to database
    public boolean createProduct(){
        BasicDBObject document = new BasicDBObject();
        document.put("name", name);
        document.put("price", price);
        document.put("description", description);
        document.put("images", "blank");
        document.put("location", location.toString());
        document.put("county", county);
        document.put("author", author);
        document.put("category", category);
        
        try{
            table.insert(document);
        } catch(MongoException e){
            return false;
        }
        
        return true;
    }
    
    // readProducts by Category
    public List<Product> readProduct(String category){
        List<Product> products = new ArrayList();
        BasicDBObject document = new BasicDBObject();
        document.put("category", category);
        
        DBCursor cursor = table.find(document);
        
        while(cursor.hasNext()){
            DBObject product = cursor.next();
            
            String name = (String)product.get("name");
            String price = (String)product.get("price");
            String description = (String)product.get("description");
            // Image decoding here
            
            // Get latitude and longitude from composed String
            String location = (String)product.get("location");
            float lat = Float.parseFloat(location.split(",")[0]);
            float lon = Float.parseFloat(location.split(",")[1]);
            String county = (String)product.get("county");
            String author = (String)product.get("author");
            
            Product p = new Product(name, price, description, lat, lon, county, author, category);
            products.add(p);
        }
        
        return products;
    }
    
}

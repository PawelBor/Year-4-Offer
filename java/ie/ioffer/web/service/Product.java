/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.ioffer.web.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

@XmlRootElement
public class Product {
    // MongoDB connection variables
	protected MongoClient mongo = new MongoClient("localhost", 27017);
    @SuppressWarnings("deprecation")
    protected DB db = mongo.getDB("ioffer");
    protected DBCollection table = db.getCollection("products");
    
    // Member variables for all products
    public String name;
    public double price;
    public String description;
    public String image;
    public Location location;
    public String county;
    public String author;
    public String category;
    public String productId;
    public String mobileNo;
    public List<Comment> comment;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public Location getLocation() {
        return location;
    }

    public String getCounty() {
        return county;
    }

    public String getAuthor() {
        return author;
    }

    public List<Comment> getComment() {
        return comment;
    }
    
    public String getMobileNo() {
        return mobileNo;
    }
    
    public String getCategory() {
		return category;
	}

	public String getProductId() {
		return productId;
	}

	public Product (){
        
    }
    
    // Constructor for GET (additional comments displayed on item.jsp)
    public Product (String name, Double price, String description, String image, float lat, float lon, String county, String author, String category, String productId, String mobileNo, List<Comment> comment){
        this();
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.location = new Location(lat, lon);
        this.county = county;
        this.author = author;
        this.category = category;
        this.mobileNo = mobileNo;
        this.comment = comment;
    }
    
    // Constructor for POST (an empty comment field is added later when item is being added to db)
    public Product (String name, Double price, String description, String image, float lat, float lon, String county, String author, String category, String productId, String mobileNo){
        this();
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.location = new Location(lat, lon);
        this.county = county;
        this.author = author;
        this.category = category;
        this.mobileNo = mobileNo;
    }

    // Constructor for PUTting new data over the old for a product. (Can't update image/location/author/comments)
    public Product(String name, String description, String price, String id, String mobile){
    	this.name = name;
    	this.description = description;
    	this.price = Double.parseDouble(price);
    	this.productId = id;
    	this.mobileNo = mobile;
    }

    // Create a new product and add to database
    public boolean createProduct(){
        BasicDBObject document = new BasicDBObject();
        document.put("name", name);
        document.put("price", price);
        document.put("description", description);
        document.put("images", image);
        document.put("location", location.toString());
        document.put("county", county);
        document.put("author", author);
        document.put("category", category);
        document.put("mobileNo", mobileNo);
        document.put("comment", comment);
        
        try{
            table.insert(document);
        } catch(MongoException e){
            return false;
        }
        
        return true;
    }
    
    public List<Product> search(Query query){
        List<Product> products = new ArrayList<Product>();
        boolean empty = false;
        
        BasicDBObject document = new BasicDBObject();
        List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
        
        if(!(query.getName().equals(""))){
            empty = true;
            obj.add(new BasicDBObject("name", java.util.regex.Pattern.compile(query.getName())));
        }
        
        if(!(query.getCategory().equals(""))){
            empty = true;
            obj.add(new BasicDBObject("category", query.getCategory()));
        }
        
        if(!(query.getMinPrice().equals("")) && query.getMaxPrice().equals("")){
            empty = true;
            obj.add(new BasicDBObject("price", new BasicDBObject("$gte", Double.parseDouble(query.getMinPrice()))));
        }
        
        if(query.getMinPrice().equals("") && !(query.getMaxPrice().equals(""))){
            empty = true;
            obj.add(new BasicDBObject("price", new BasicDBObject("$lte", Double.parseDouble(query.getMaxPrice()))));
        }
        
        if(!(query.getMinPrice().equals("")) && !(query.getMaxPrice().equals(""))){
            empty = true;
            obj.add(new BasicDBObject("price", new BasicDBObject("$gte", Double.parseDouble(query.getMinPrice())).append("$lte", Double.parseDouble(query.getMaxPrice()))));
        }
        
        DBCursor cursor;
        
        if(empty == true){
            document.put("$and", obj);
            cursor = table.find(document);
        }
        else{
            cursor = table.find().limit(10);
        }
        
        while (cursor.hasNext()) {
            DBObject product = cursor.next();
            
            String name = (String)product.get("name");
            //String price = (String)product.get("price");
            double price = (Double) product.get("price");
            String description = (String)product.get("description");
            // Image decoding here
            String image = (String)product.get("image");
            // Get latitude and longitude from composed String
            String location = (String)product.get("location");
            float lat = Float.parseFloat(location.split(",")[0]);
            float lon = Float.parseFloat(location.split(",")[1]);
            String county = (String)product.get("county");
            String author = (String)product.get("author");
            String category = (String)product.get("category");
            String productId = (String)product.get("_id");
            String mobileNo = (String)product.get("mobileNo");
            //String comment = (String)product.get("comment");
            
            Product p = new Product(name, price, description, image, lat, lon, county, author, category, productId, mobileNo, comment);
            products.add(p);
        }
        
        return products;
    }
    
}

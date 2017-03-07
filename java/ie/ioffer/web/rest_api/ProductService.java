package ie.ioffer.web.rest_api;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.client.model.DBCollectionRemoveOptions;
import com.mongodb.util.JSON;

import ie.ioffer.web.service.Comment;
import ie.ioffer.web.service.Product;

@XmlRootElement
public class ProductService extends Product{
	
	// Get a product by ID
    public Product readProduct(String id){

        BasicDBObject document = new BasicDBObject();
        
        // Search by ObjectId
        document.put("_id", new ObjectId(id));
        DBCursor cursor = table.find(document); 
        
        if(cursor != null)
        {
            
        	// Get the first returned Product Object
        	DBObject product = cursor.next();
        
        	// Variable
            String name = (String)product.get("name");
            double price = (Double) product.get("price");
            String description = (String)product.get("description");
            
            // Image decoding here
            String image = (String)product.get("images");
            
            // Get latitude and longitude from composed String
            String location = (String)product.get("location");
            float lat = Float.parseFloat(location.split(",")[0]);
            float lon = Float.parseFloat(location.split(",")[1]);
            String county = (String)product.get("county");
            String categories = (String)product.get("category");
            String author = (String)product.get("author");
            String productId = product.get("_id").toString();
            String mobileNo = (String)product.get("mobileNo");
            BasicDBList list = (BasicDBList) product.get("comments");
            List<Comment> comments = new ArrayList<Comment>();
            
            if(list.size() > 0){
            	BasicDBObject[] arr = list.toArray(new BasicDBObject[0]);
                for(DBObject l : arr){
                	comments.add(new Comment(l.get("comment").toString(), l.get("date").toString()));
                }
            }
            
            
            // Construct a Product from MongoDb values
            Product p = new Product(name, price, description, image, lat, lon, county, author, categories, productId, mobileNo, comments);
            
            // Return the product
            return p;
    
        }
        
        return null;  // Return a null object if nothing is found in the database
    }
    
    public boolean deleteProduct(String id){

        BasicDBObject document = new BasicDBObject();
        System.out.println(id);
        document.put("_id", new ObjectId(id));
        if(table.remove(document) != null){
        	return true;
        }
        else{
        	return false;
        }
        
    }
    
    public boolean postComment(String id, String comment, String date){
    	System.out.println(id + " " + comment + " " + date);
        BasicDBObject document = new BasicDBObject();
        DBObject obj = (DBObject) JSON.parse("{'comment': '"+comment+"', 'date':'"+date+"'}");
        document.append("$push", new BasicDBObject().append("comments", obj));

        BasicDBObject searchQuery = new BasicDBObject().append("_id", new ObjectId(id));

        table.update(searchQuery, document);
        
        return true;
        
    }
    
    public List<Product> readProductsByCounty(String productcounty){
    	List<Product> products = new ArrayList<Product>();
        BasicDBObject document = new BasicDBObject();
        
        // Search by ObjectId
        System.out.println(productcounty);
        document.put("county", productcounty);
        DBCursor cursor = table.find(document); 
        
    	while(cursor.hasNext()){
    	
	        DBObject product = cursor.next();
	        
	        String name = (String)product.get("name");
	
	        double price = (Double) product.get("price");
	        String description = (String)product.get("description");
	        
	        String image = (String)product.get("images");
	        
	        // Get latitude and longitude from composed String
	        String location = (String)product.get("location");
	        float lat = Float.parseFloat(location.split(",")[0]);
	        float lon = Float.parseFloat(location.split(",")[1]);
	        
	        String county = (String)product.get("county");
	        String categories = (String)product.get("category");
	        String author = (String)product.get("author");
	        String productId = product.get("_id").toString();
	        String mobileNo = (String)product.get("mobileNo");
            
	        Product p = new Product(name, price, description, image, lat, lon, county, author, categories, productId, mobileNo);
	        products.add(p);
        }
		
		return products;
    }
    
    public List<Product> readProductsByAuthor(String productAuthor){
    	List<Product> products = new ArrayList<Product>();
        BasicDBObject document = new BasicDBObject();
        
        // Search by ObjectId
        System.out.println(productAuthor);
        document.put("author", productAuthor);
        DBCursor cursor = table.find(document); 
        
    	while(cursor.hasNext()){
    	
	        DBObject product = cursor.next();
	        
	        String name = (String)product.get("name");
	
	        double price = (Double) product.get("price");
	        String description = (String)product.get("description");
	        
	        String image = (String)product.get("images");
	        
	        // Get latitude and longitude from composed String
	        String location = (String)product.get("location");
	        float lat = Float.parseFloat(location.split(",")[0]);
	        float lon = Float.parseFloat(location.split(",")[1]);
	        
	        String county = (String)product.get("county");
	        String categories = (String)product.get("category");
	        String author = (String)product.get("author");
	        String productId = product.get("_id").toString();
	        String mobileNo = (String)product.get("mobileNo");
	        
	        Product p = new Product(name, price, description, image, lat, lon, county, author, categories, productId, mobileNo);
	        products.add(p);
        }
		
		return products;
    }
    
    public List<Product> readProductsByCategory(String productcategory){
    	List<Product> products = new ArrayList<Product>();
        BasicDBObject document = new BasicDBObject();
        
        // Search by ObjectId
        document.put("category", productcategory);
        DBCursor cursor = table.find(document); 
        
    	while(cursor.hasNext()){
    	
	        DBObject product = cursor.next();
	        
	        String name = (String)product.get("name");
	
	        double price = (Double) product.get("price");
	        String description = (String)product.get("description");
	        
	        String image = (String)product.get("images");
	        
	        // Get latitude and longitude from composed String
	        String location = (String)product.get("location");
	        float lat = Float.parseFloat(location.split(",")[0]);
	        float lon = Float.parseFloat(location.split(",")[1]);
	        
	        String county = (String)product.get("county");
	        String categories = (String)product.get("category");
	        String author = (String)product.get("author");
	        String productId = product.get("_id").toString();
	        String mobileNo = (String)product.get("mobileNo");
	        
	        Product p = new Product(name, price, description, image, lat, lon, county, author, categories, productId, mobileNo);
	        products.add(p);
        }
		
		return products;
    }
    
    // Get all products from MongoDb
    public List<Product> readAllProducts(){
        List<Product> products = new ArrayList<Product>();
        
        DBCursor cursor = table.find();
        
        while(cursor.hasNext()){
        	
            DBObject product = cursor.next();
            
            String name = (String)product.get("name");

            double price = (Double) product.get("price");
            String description = (String)product.get("description");
            
            String image = (String)product.get("images");
            
            // Get latitude and longitude from composed String
            String location = (String)product.get("location");
            float lat = Float.parseFloat(location.split(",")[0]);
            float lon = Float.parseFloat(location.split(",")[1]);
            
            String county = (String)product.get("county");
            String categories = (String)product.get("category");
            String author = (String)product.get("author");
            String productId = product.get("_id").toString();
            String mobileNo = (String)product.get("mobileNo");
            
            Product p = new Product(name, price, description, image, lat, lon, county, author, categories, productId, mobileNo);
            products.add(p); 
            
        }
        
        return products;
    }
    
    public String insertProduct(Product x)
    {
    	BasicDBObject document = new BasicDBObject();
        document.put("name", x.name);
        document.put("price", x.price);
        document.put("description", x.description);
        document.put("images", x.image);
        document.put("location", x.location.toString());
        document.put("county", x.county);
        document.put("author", x.author);
        document.put("category", x.category);
        document.put("comments", new BasicDBList());
        
        try{
            table.insert(document);
            
            return (String)document.get("_id").toString();
        } catch(MongoException e){
        	return null;
        }
    }
   
}

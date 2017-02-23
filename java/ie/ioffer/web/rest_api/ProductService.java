package ie.ioffer.web.rest_api;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

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
        	DBObject Dbproduct = cursor.next();
        
        	// Variable
            String name = (String)Dbproduct.get("name");
            double price = (Double) Dbproduct.get("price");
            String description = (String)Dbproduct.get("description");
            
            // Image decoding here
            String image = (String)Dbproduct.get("images");
            
            // Get latitude and longitude from composed String
            String location = (String)Dbproduct.get("location");
            float lat = Float.parseFloat(location.split(",")[0]);
            float lon = Float.parseFloat(location.split(",")[1]);
            String county = (String)Dbproduct.get("county");
            String categories = (String)Dbproduct.get("category");
            String author = (String)Dbproduct.get("author");
            String productId = Dbproduct.get("_id").toString();
            
            // Construct a Product from MongoDb values
            Product product = new Product(name, price, description, image, lat, lon, county, author, categories, productId);
            
            // Return the product
            return product;
    
        }
        
        return null;  // Return a null object if nothing is found in the database
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
            
            Product p = new Product(name, price, description, image, lat, lon, county, author, categories, productId);
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
        
        try{
            table.insert(document);
            
            return (String)document.get("_id");
        } catch(MongoException e){
        	return null;
        }
    }
   
}

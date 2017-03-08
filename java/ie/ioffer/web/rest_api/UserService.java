package ie.ioffer.web.rest_api;

import javax.xml.bind.annotation.XmlRootElement;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

import ie.ioffer.web.service.User;

@XmlRootElement
public class UserService extends User{

	// Get a user by email
    public User readUser(String email){

        BasicDBObject document = new BasicDBObject();
        
        // Search by email
        document.put("email", email);
        DBCursor cursor = table.find(document); 
        
        if(cursor != null)
        {
        	// Get the first returned user object
        	DBObject Dbproduct = cursor.next();
        
        	// Variables
        	String password = (String)Dbproduct.get("password");
            String name = (String)Dbproduct.get("name");
            // Construct a user from MongoDb values
            User user = new User(email, password, name);
            
            // Return the user object
            return user;
        }else{
        	return null;  // Return a null object if nothing is found in the database
        }
    }
    
    public boolean insertUser(String email, String password, String name){
    	 BasicDBObject document = new BasicDBObject();
         
         long count =  mongo.getDatabase("ioffer")               
                 .getCollection("users")
                 .count(new Document("email", email));
         
         if(count > 0)
         {
        	 return false;
         }
         else{
        	 document.put("email", email);
             document.put("password", password);
             document.put("name", name);
             
             try{
                 table.insert(document);
                 return true;
             } catch(MongoException e){
                 return false;
             }
         }
    }
}

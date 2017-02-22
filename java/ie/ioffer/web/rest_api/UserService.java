package ie.ioffer.web.rest_api;

import javax.xml.bind.annotation.XmlRootElement;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

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
            String county = (String)Dbproduct.get("county");
            String id = Dbproduct.get("_id").toString();

            // Construct a user from MongoDb values
            User user = new User(email, password, name, county, id);
            
            // Return the user object
            return user;
    
        }
        
        return null;  // Return a null object if nothing is found in the database
    }

}
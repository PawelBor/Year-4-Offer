/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.ioffer.web.service;

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
public class User {
    MongoClient mongo = new MongoClient("localhost", 27017);
    @SuppressWarnings("deprecation")
	DB db = mongo.getDB("ioffer");
    DBCollection table = db.getCollection("users");
    
    public boolean create(String email, String password, String name, String county){
        BasicDBObject document = new BasicDBObject();
        document.put("email", email);
        document.put("password", password);
        document.put("name", name);
        document.put("county", county);
        
        try{
            table.insert(document);
            
            UserInstance user = UserInstance.getInstance();
            user.setEmail(email);
            user.setCounty(county);
            user.setName(name);
        } catch(MongoException e){
            return false;
        }
        
        return true;
    }
    
    
    public boolean login(String email, String password){
        BasicDBObject document = new BasicDBObject();
        document.put("email", email);
        
        DBCursor cursor = table.find(document);
        
        while(cursor.hasNext()){
            DBObject user = cursor.next();
            
            String userpass = (String)user.get("password");
            if(userpass.equals(password)){
                // Login successful - update user instance bean
                UserInstance userInformation = UserInstance.getInstance();
                userInformation.setEmail(email);
                userInformation.setName((String)user.get("name"));
                userInformation.setCounty((String)user.get("county"));
                
                // Return validation that login was successful
                return true;
            }
        }
        
        return false;
    }
}

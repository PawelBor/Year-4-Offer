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

public class User {
	
	protected MongoClient mongo = new MongoClient("localhost", 27017);
    @SuppressWarnings("deprecation")
    protected DB db = mongo.getDB("ioffer");
    protected DBCollection table = db.getCollection("users");
    
 // Member variables for all users
    private String email;
    private String password;
    private String name;
    private String userId;
   
    public User(){
    	
    }
    
    public User(String email, String password, String name){
        this.email = email;
        this.password = password;
        this.name = name;
    }
    
    public User(String email, String password, String name, String id){
        this.email = email;
        this.password = password;
        this.name = name;
        this.userId = id;
    }
    
    public boolean create(){
        BasicDBObject document = new BasicDBObject();
        document.put("email", email);
        document.put("password", password);
        document.put("name", name);
        
        try{
            table.insert(document);
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
                // Return validation that login was successful
                return true;
            }
        }
        return false;
    }
}

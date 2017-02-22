/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.ioffer.web.rest_api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ie.ioffer.web.service.User;


/*
 * This class uses the GET HTTP method
 * to retrieve a particular user
 */
@Path("user/{email}")
public class getUser {

	UserService userService = new UserService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User getJSON(@PathParam("email") String email) {
		
		User ObjIdProduct = userService.readUser(email);
		
		if(ObjIdProduct != null)
			return ObjIdProduct;
		else
			return null; // Redirect to user not found page.
		
	}
	
}// Class end

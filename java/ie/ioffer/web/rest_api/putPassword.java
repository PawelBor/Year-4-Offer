package ie.ioffer.web.rest_api;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ie.ioffer.web.service.Password;

@Path("user")
public class putPassword {

	UserService userService = new UserService();
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean getJSON(Password password) {
		System.out.println(password.getEmail() + " " + password.getOldPass());
		boolean putPass = userService.putPassword(password.getEmail(), password.getOldPass(), password.getNewPass());
		
		return putPass;
	}
	
}// Class end
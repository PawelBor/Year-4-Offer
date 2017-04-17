package ie.ioffer.web.rest_api;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("user")
public class postUser {
	UserService userService = new UserService();

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean insert(@FormParam("email") String email, @FormParam("password") String password,
			@FormParam("name") String name) {
		
		boolean response = userService.insertUser(email, password, name);
		return response;
	}
}// Class end

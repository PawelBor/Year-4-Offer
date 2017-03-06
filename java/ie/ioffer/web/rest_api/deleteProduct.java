package ie.ioffer.web.rest_api;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ie.ioffer.web.service.Product;

/*
 * This class uses the GET HTTP method
 * to retrieve a particular product
 */
@Path("product/{id}")
public class deleteProduct {

	ProductService productService = new ProductService();
	
	@DELETE
	//@Produces(MediaType.APPLICATION_JSON)
	public boolean getJSON(@PathParam("id") String id) {
		boolean delete = productService.deleteProduct(id);
		
		return delete;
	}
	
}// Class end

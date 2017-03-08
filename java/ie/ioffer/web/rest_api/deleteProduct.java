package ie.ioffer.web.rest_api;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

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

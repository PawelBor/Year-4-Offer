package ie.ioffer.web.rest_api;

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
public class getProduct {

	ProductService productService = new ProductService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Product getJSON(@PathParam("id") String id) {
		
		Product ObjIdProduct = productService.readProduct(id);
		
		if(ObjIdProduct != null)
			return ObjIdProduct;
		else
			return null; // Redirect to product not found page.
		
	}
	
}// Class end

package ie.ioffer.web.rest_api;

import ie.ioffer.web.service.Product;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("product")
public class putProduct {
ProductService productService = new ProductService();
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean putJSON(Product product) {
		boolean putProduct = productService.putProduct(product);
		return putProduct;
	}
}

package ie.ioffer.web.rest_api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ie.ioffer.web.service.Product;

@Path("product/{county}")
public class getProductCounty {
ProductService productService = new ProductService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getJSON(@PathParam("county") String county) {	
		return productService.readProductsByCounty(county);
	}
}

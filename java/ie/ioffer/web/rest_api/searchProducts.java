package ie.ioffer.web.rest_api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ie.ioffer.web.service.Product;
import ie.ioffer.web.service.Query;


@Path("products/{name}/{minPrice}/{maxPrice}/{category}")
public class searchProducts {
	ProductService productSerice = new ProductService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getJSON(@PathParam("name") String name, @PathParam("minPrice") String minPrice, @PathParam("maxPrice") String maxPrice, @PathParam("category") String category) {
		Query q = new Query(name, category, minPrice, maxPrice);
		
		return productSerice.search(q);
	}

}

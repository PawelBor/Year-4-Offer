package ie.ioffer.web.rest_api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ie.ioffer.web.service.Location;
import ie.ioffer.web.service.Product;

/*
 * This class uses the GET HTTP method
 * to retrieve a particular product
 */
@Path("create/product/{name}/{description}/{price}/{lat}/{lng}/{image}/{county}/{author}/{category}")
public class postProduct {

	ProductService productService = new ProductService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Product getJSON(@PathParam("name") String name,@PathParam("description") String description,
			@PathParam("lat") String lat,@PathParam("lng") String lng, @PathParam("image") String image,
			@PathParam("county") String county, @PathParam("author") String author, 
			@PathParam("category") String category,@PathParam("price") String price) {
		
		Product x = new Product();
		x.author = author;
		x.category = category;
		x.county = county;
		x.name = name;
		x.description = description;
		x.location =  new Location(Float.parseFloat(lat), Float.parseFloat(lng));
		x.image = image;
		x.price = Double.parseDouble(price);
		 
		Product y = productService.insertProduct(x);
		
		if(y != null)
			return y;		
		
		return null; // Redirect to product not found page.
		
	}
	
}// Class end

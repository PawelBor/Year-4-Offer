package ie.ioffer.web.rest_api;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ie.ioffer.web.service.Location;
import ie.ioffer.web.service.Product;

@Path("post_product")
public class postProductAndroid {
    ProductService productService = new ProductService();

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String getProduct(@FormParam("name") String name, @FormParam("author") String author,
            @FormParam("category") String category, @FormParam("county") String county,
            @FormParam("description") String description, @FormParam("location") String location,
            @FormParam("price") String price, @FormParam("image") String image,
            @FormParam("mobileNo") String mobileNo) throws Exception {
        
        
      
        Product x = new Product();
        x.author = author;
        x.category = category;
        x.county = county;
        x.name = name;
        x.description = description;
        String[] loc = location.split(" ");
        x.location = new Location(Float.parseFloat(loc[0]), Float.parseFloat(loc[1]));
        x.image = image;
        x.price = Double.parseDouble(price);
        x.mobileNo = mobileNo;
         
        String id = productService.insertProduct(x);
        
        return id;
    }
}// Class end

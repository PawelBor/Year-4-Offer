package ie.ioffer.web.rest_api;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.BodyPartEntity;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import ie.ioffer.web.service.Base64Encoder;
import ie.ioffer.web.service.Location;
import ie.ioffer.web.service.Product;

@Path("product")
public class postProduct {

	ProductService productService = new ProductService();
	
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public String getProduct(@FormDataParam("name") String name,@FormDataParam("description") String description,
			@FormDataParam("location") String location, @FormDataParam("images") File imgBodyParts,
			@FormDataParam("county") String county, @FormDataParam("author") String author, 
			@FormDataParam("category") String category,@FormDataParam("price") String price) {
		
		String images = "";
        Base64Encoder enc = new Base64Encoder();
        //String[] imgs = imgBodyParts.getValue().split(",");
        
        //for (FormDataBodyPart part : imgBodyParts){
        	//for(int i = 0; i < imgs.length; i++){
            try {
            	//String x = "C:\\" + imgs[i];
        		
            	//BodyPartEntity bodyPartEntity = (BodyPartEntity) part.getEntity();
            	//File img = new File(x);
            	//InputStream filecontent = part.getValueAs(InputStream.class);
            	InputStream filecontent = new FileInputStream(imgBodyParts);
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int nRead;
                byte[] data = new byte[16384];

                while ((nRead = filecontent.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }
                
                byte[] message = buffer.toByteArray();
                String encryption = enc.encode(message);
                
                if(images.equals("")){
                    images = encryption;
                }else{
                    images += "," + encryption;
                }
                
                buffer.close();
                filecontent.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
         // }
        //}
		
		Product x = new Product();
		x.author = author;
		x.category = category;
		x.county = county;
		x.name = name;
		x.description = description;
		String[] loc = location.split(" ");
		x.location = new Location(Float.parseFloat(loc[0]), Float.parseFloat(loc[1]));
		x.image = images;
		x.price = Double.parseDouble(price);
		 
		String id = productService.insertProduct(x);
		
		return id;
	}
}// Class end

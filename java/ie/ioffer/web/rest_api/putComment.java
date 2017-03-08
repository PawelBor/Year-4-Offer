package ie.ioffer.web.rest_api;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ie.ioffer.web.service.Comment;

/*
 * This class uses the PUT HTTP method
 * to add a comment to a particular product
 */
@Path("product/comment")
public class putComment {

	ProductService productService = new ProductService();
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean getJSON(Comment comment) {
		boolean postComment = productService.putComment(comment.getId(), comment.getComment(), comment.getDate(), comment.getAuthor());
		
		return postComment;
	}
	
}// Class end

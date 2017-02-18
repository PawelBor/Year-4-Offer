package ie.ioffer.web.service;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "createProduct", urlPatterns = {"/createProduct"})
@MultipartConfig
public class CreateProduct extends HttpServlet{

	private static final long serialVersionUID = 777L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Create path components to save the file
        String images = "";
        
        Base64Encoder enc = new Base64Encoder();

        for (Part part : request.getParts()){
            if(part.getName().equals("image")){
                try {
                    InputStream filecontent = part.getInputStream();
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
                } catch (FileNotFoundException fne) {
                    fne.printStackTrace();
                }
            }
        }

        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        // Location should be achieved by adding a marker on the Google Maps API
        //String location = request.getParameter("location");
        
        //float lat = Float.parseFloat(request.getParameter("location"));
        //float lon = Float.parseFloat(request.getParameter("location"));
        String county = "Galway";
        String author = "Baldy";
        String category = request.getParameter("category");
        //response.getWriter().write("Images: " + image + ", \nCounty: " + county + ", Author: " + author);
        
        Product product = new Product(name, price, description, "", 12,13, county, author, category , "");
        product.createProduct();
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        
        request.setAttribute("images", images); // set your String value in the attribute
        dispatcher.forward( request, response );
        
        response.sendRedirect("index.jsp");
    }


    public String getServletInfo() {
        return "Servlet resource used to create products";
    }
}
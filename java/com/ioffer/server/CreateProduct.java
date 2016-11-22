package com.ioffer.server;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "createProduct", urlPatterns = {"/createProduct"})
//@MultipartConfig
public class CreateProduct extends HttpServlet {
    //private final static Logger LOGGER = Logger.getLogger(CreateProduct.class.getCanonicalName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String description = request.getParameter("description");
        // Location should be achieved by adding a marker on the Google Maps API
        String location = request.getParameter("location");
        
        //float lat = Float.parseFloat(request.getParameter("location"));
        //float lon = Float.parseFloat(request.getParameter("location"));
        
        // Get the singleton bean for user instance (Current logged in user)
        UserInstance user = UserInstance.getInstance();
        String county = user.getCounty();
        String author = user.getName();
        String category = request.getParameter("category");
        
        String images = request.getParameter("images");
        
        response.getWriter().write("Images: " + images + ", County: " + county + ", Author: " + author);
        /*
        Product product = new Product(name, price, description, lat, lon, county, author, category);
        product.createProduct();
        
        response.sendRedirect("sidebarPage.html");*/
    }


    public String getServletInfo() {
        return "Servlet resource used to create products";
    }
    
    /*
    // https://docs.oracle.com/javaee/6/tutorial/doc/glraq.html
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Create path components to save the file
        final String path = request.getParameter("destination");
        final Part filePart = request.getPart("file");
        final String fileName = getFileName(filePart);

        OutputStream out = null;
        InputStream filecontent = null;
        final PrintWriter writer = response.getWriter();

        try {
            out = new FileOutputStream(new File(path + File.separator
                    + fileName));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            writer.println("New file " + fileName + " created at " + path);
            LOGGER.log(Level.INFO, "File{0}being uploaded to {1}", 
                    new Object[]{fileName, path});
        } catch (FileNotFoundException fne) {
            writer.println("You either did not specify a file to upload or are "
                    + "trying to upload a file to a protected or nonexistent "
                    + "location.");
            writer.println("<br/> ERROR: " + fne.getMessage());

            LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}", 
                    new Object[]{fne.getMessage()});
        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
    }
    
    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    } */
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.ioffer.web.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Niks
 */
@WebServlet(name = "searchProduct", urlPatterns = {"/searchProduct"})
public class Results extends HttpServlet {

	private static final long serialVersionUID = 222L;


	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");

        if(category == null){
            category = "";
        }
        
        System.out.println("name: " + name + " category: " + category + " min: " + minPrice + " max: " + maxPrice);
        Query q = new Query(name, category, minPrice, maxPrice);
        Product controller = new Product();
        @SuppressWarnings("unused")
		List<Product> results = controller.search(q);
        /*Product[] prodArray = new Product[results.size()];
        prodArray = results.toArray(prodArray);

        RequestDispatcher dispatcher = request.getRequestDispatcher("results.jsp");
        request.setAttribute("results", prodArray); // set your String value in the attribute
        dispatcher.forward(request, response);*/
    }
	
	public List<Product> getAllProducts(){
		
	        Query q = new Query("", "", "", "");
	        Product controller = new Product();
			List<Product> results = controller.search(q);
	        return results;
	        
	}
	
	public List<Product> getProduct(int id){
		
        Query q = new Query("", "", "", "");
        Product controller = new Product();
		List<Product> results = controller.search(q);
        return results;
        
	}
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

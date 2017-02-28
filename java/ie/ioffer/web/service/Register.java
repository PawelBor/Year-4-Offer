/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.ioffer.web.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "register", urlPatterns = {"/register"})
public class Register extends HttpServlet {

	private static final long serialVersionUID = 111L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String county = request.getParameter("county");
        
        //response.getWriter().println(email + " " + password + " " + name + " " + county);
        User user = new User(email, password, name, county);
        user.create();
        
        response.sendRedirect("sidebarPage.html");
    }
}

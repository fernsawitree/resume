/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.towson.controllers;

import edu.towson.beans.UserBean;
import edu.towson.dao.DaoFactory;
import edu.towson.dao.DaoPattern;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author korea_fern
 */
@WebServlet(urlPatterns = {"/CreateAccount"})
public class CreateAccount extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
//display jsp file
        response.sendRedirect("CreateAccount.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//if username doesn't exist in User Table, then insert values 
//else user already exists. Show error message 

        try {

            HttpSession session = request.getSession();
            String submit = request.getParameter("submit");

            String Driver = "com.mysql.jdbc.Driver";
            String URL = "jdbc:mysql://localhost:3306/Resume";
            Class.forName(Driver);
            Connection Conn = DriverManager.getConnection(URL, "root", "");
            Statement S = Conn.createStatement();
            ResultSet RS = S.executeQuery(
                    "SELECT * FROM User");

            while (RS.next()) {
                String Username = RS.getString("Username");
                String Password = RS.getString("Password");
               
                if ("Create Account".equals(submit)) {

                    String[] requiredFormParams = {"ifirst_name", "ilast_name", "iaddress1", "iaddress2", "icity", "istate", "izipcode", "iemail", "iuser_name", "ipassword"};
                    // run through the list and thrown an exception if a required field is missing
                    for (String param : requiredFormParams) {
                        if (request.getParameter(param) == null || request.getParameter(param).isEmpty()) {
                            Exception ex = new Exception("Error! All fields are required. param:" + param);
                            throw ex;
                        }
                    }
                    // if we get here then all the required fields were found
                    UserBean user = new UserBean();
                    //not sure about this
                    int userId = this.UserBeanDao.findLast().getUserId() + 1; // this should make all entries have a unique incrementing id
                    user.setUser_id(user_id);

                    user.setUsername(request.getParameter("username"));
                    user.setPassword(request.getParameter("password"));
                    user.setFirstname(request.getParameter("firstname"));
                    user.setLastname(request.getParameter("lastname"));
                    user.setEmail(request.getParameter("email"));




                    int res = this.UserBeanDao.store(user);
                    if (res != 0) // check that the item was successfully added.
                    {
                        // add the successfully added item to the session so that the view item page will load it.
                        session.setAttribute("user", user);
                    } else {
                        target = "/CreateaccountOrlogin.jsp"; // database error try again
                        request.setAttribute("error", "Error! Info couldn't be saved.");// errors are specific to the request so use the request object
                    }

                }
            }
         catch (Exception ex) {
            System.err.println("Exception caught." + ex.getMessage());
            target = "/CreateaccountOrlogin.jsp"; // try again
            request.setAttribute("error", "Error! All fields are required.");
        } finally {
            ServletContext context = this.getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(target);
            dispatcher.forward(request, response);
        }

    }
}

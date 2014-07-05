/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.towson.controllers;

import edu.towson.beans.UserBean;
import edu.towson.dao.UserDao;
import edu.towson.dao.DaoFactory;
import edu.towson.dao.DaoPattern;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
private static final Logger log = Logger.getLogger(CreateAccount.class.getName());
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
        String param = request.getParameter("username");
       if (request.getParameter(param) == null || request.getParameter(param).isEmpty()){
           String message = "null user";

                request.setAttribute("message", message);
                request.getRequestDispatcher("/CreateAccount.jsp").forward(request, response);
                log.log(Level.INFO, "statement null username" );
                return ;
       }

        HttpSession session = request.getSession();
        String submit = request.getParameter("submit");
        
       

        String Driver = "com.mysql.jdbc.Driver";
        String URL = "jdbc:mysql://localhost:3306/Resume_Pro";
        ResultSet RS = null;
        try {
            Class.forName(Driver);
            Connection Conn = DriverManager.getConnection(URL, "root", "");
            Statement S = Conn.createStatement();
            String username = request.getParameter("username");
            RS = S.executeQuery(
                    "SELECT * FROM User where username = ' " + username + "'");




            while (RS.next()) {

                String message = "User already exists.";
                log.log(Level.INFO, "statement user exists" );
                request.setAttribute("message", message);
                request.getRequestDispatcher("/CreateAccount.jsp").forward(request, response);
                return;

            }


            String[] requiredFormParams = {"fname", "lname", "email", "username", "password"};
            // run through the list and thrown an exception if a required field is missing
            String message = "Error! All fields are required. param:";
            boolean haserror = false;
            for ( String parameter : requiredFormParams) {
                if (request.getParameter(parameter) == null || request.getParameter(parameter).isEmpty()) {
                    message += parameter + ',';
                    haserror = true;
                }

            }
            if (haserror) {
                request.setAttribute("message", message);
                request.getRequestDispatcher("/CreateAccount.jsp").forward(request, response);
                log.log(Level.INFO, "errors" );
                return;
            }

            // if we get here then all the required fields were found
            UserBean user = new UserBean();
            //not sure about this
            UserDao userdao = new UserDao(Conn);
            int userId = userdao.findLast().getUser_id() + 1; // this should make all entries have a unique incrementing id
            user.setUser_id(userId);
            user.setUsername(request.getParameter("username"));
            user.setPassword(request.getParameter("password"));
            user.setFirstname(request.getParameter("fname"));
            user.setLastname(request.getParameter("lname"));
            user.setEmail(request.getParameter("email"));
            user.setPhoneNumber(request.getParameter("phonenumber"));

            int res = userdao.store(user);
            if (res != 0) // check that the item was successfully added.
            {
                // add the successfully added item to the session so that the view item page will load it.
                session.setAttribute("user", user);
            } else {
                request.setAttribute("message", "Data cannot be saved into the database.");
                request.getRequestDispatcher("/CreateAccount.jsp").forward(request, response);
                log.log(Level.INFO, "statement store data" );
                return;

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateAccount.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CreateAccount.class.getName()).log(Level.SEVERE, null, ex);


        }
        
    }
}

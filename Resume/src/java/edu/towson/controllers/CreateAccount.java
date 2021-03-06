/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.towson.controllers;

import edu.towson.beans.UserBean;
import edu.towson.dao.UserDao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        log.log(Level.INFO, "{0}", username );
        log.log(Level.INFO, "{0}", request.getParameter("fname"));
        request.getSession().removeAttribute("message");
       if (username == null || username.isEmpty()){
           String message = "null user";

                session.setAttribute("message", message);
                request.getRequestDispatcher("/CreateAccount.jsp").forward(request, response);
                log.log(Level.INFO, "statement null username" );
                log.log(Level.INFO, "{0}", username );
                return ;
       }

        
        String submit = request.getParameter("submit");
        
       

        String Driver = "com.mysql.jdbc.Driver";
        String URL = "jdbc:mysql://localhost:3306/resume1";
        ResultSet RS = null;
        try {
            Class.forName(Driver);
            Connection Conn = DriverManager.getConnection(URL, "root", "");
            Statement S = Conn.createStatement();
             username = request.getParameter("username");
             String userquery = "SELECT * FROM User where username = '" + username + "'";
            RS = S.executeQuery(                   
                    userquery);
            log.log(Level.INFO, userquery);




            while (RS.next()) {

                String message = "User already exists.";
                log.log(Level.INFO, "statement user exists" );
                request.setAttribute("message", message);
                request.getRequestDispatcher("/CreateAccount.jsp").forward(request, response);
                return;

            }


            String[] requiredFormParams = {"fname", "lname", "email", "address", "city", "state", "zipcode"  ,"username", "phonenumber","password"};
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
          //  int userId = userdao.findLast().getUser_id() + 1; // this should make all entries have a unique incrementing id
            
         //   user.setUser_id(userId);
            user.setUsername(request.getParameter("username"));
            user.setPassword(request.getParameter("password"));
            user.setFirstname(request.getParameter("fname"));
            user.setLastname(request.getParameter("lname"));
            user.setAddress(request.getParameter("address"));
            user.setCity(request.getParameter("city"));
            user.setState(request.getParameter("state"));
            user.setZipcode(request.getParameter("zipcode"));
            user.setEmail(request.getParameter("email"));
            user.setPhoneNumber(request.getParameter("phonenumber"));

            int res = userdao.store(user);
            if (res != 0) // check that the item was successfully added.
            {
                // add the successfully added item to the session so that the view item page will load it.
                session.setAttribute("user_id", user.getUser_id());
                 //store everything in user object
                session.setAttribute("user", user);
                
                //redirect the page to the next step in order to fil our all information 
                request.getRequestDispatcher("/AddSkills.jsp").forward(request, response);
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

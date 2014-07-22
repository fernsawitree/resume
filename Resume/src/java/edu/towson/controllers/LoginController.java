/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.towson.controllers;

import edu.towson.beans.UserBean;
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
import org.apache.commons.dbutils.BeanProcessor;


@WebServlet(urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {
private static final Logger log = Logger.getLogger(LoginController.class.getName());
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
//display jsp file
        response.sendRedirect("login.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        request.getSession().removeAttribute("message");
     
        String[] requiredFormParams = {"username", "password"};
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
                
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                log.log(Level.INFO, "errors" );
                return;
            }

       

        String Driver = "com.mysql.jdbc.Driver";
        String URL = "jdbc:mysql://localhost:3306/resume1";
        ResultSet RS = null;
        try {
            Class.forName(Driver);
            Connection Conn = DriverManager.getConnection(URL, "root", "");
            Statement S = Conn.createStatement();
             //username = request.getParameter("username");
             String userquery = "SELECT * FROM User where username = '" + username + "'" + " and password ='"+ password + "'";
            RS = S.executeQuery(                   
                    userquery);
            log.log(Level.INFO, userquery);




            while (RS.next()) {
                BeanProcessor bp = new BeanProcessor();
                UserBean user = (UserBean) bp.toBean(RS, UserBean.class);
                session.setAttribute("user_id", user.getUser_id());
                //store everything in user object
                session.setAttribute("user", user);
                request.getRequestDispatcher("/summary.jsp").forward(request, response);
                return;

            }
                message = "User already exists.";
                log.log(Level.INFO, "statement user exists" );
                request.setAttribute("message", message);
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;

            
           
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateAccount.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CreateAccount.class.getName()).log(Level.SEVERE, null, ex);


        }
        
    }
}

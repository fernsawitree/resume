package edu.towson.controllers;

/*
 * Fern Sawitree Euamethiyangkool
 */
import edu.towson.beans.AddressBean;
import edu.towson.dao.AddressBeanDao;
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
@WebServlet(urlPatterns = {"/AddressController"})
public class AddressController extends HttpServlet {
private static final Logger log = Logger.getLogger(AddressController.class.getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //if Add Experience button is clicked, pop-up window will appear
        String addAddress = request.getParameter("addaddress");
        response.sendRedirect("AddAddress.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String submit = request.getParameter("submit");
        //start database connection
        String Driver = "com.mysql.jdbc.Driver";
        String URL = "jdbc:mysql://localhost:3306/resume1";
        ResultSet RS = null;
        try {
            Class.forName(Driver);
            Connection Conn = DriverManager.getConnection(URL, "root", "");
            Statement S = Conn.createStatement();
          
            String[] requiredFormParams = {"Address1", "city", "state", "zipcode"};
            // run through the list and thrown an exception if a required field is missing
            String message = "Error! Please fill out the required field. param:";
            boolean haserror = false;
            for (String parameter : requiredFormParams) {
                if (request.getParameter(parameter) == null || request.getParameter(parameter).isEmpty()) {
                    message += parameter + ',';
                    haserror = true; //Note that I have to change null value in the database
                }

            }
            if (haserror) {
                request.setAttribute("message", message);
                request.getRequestDispatcher("/EnterInfo.jsp").forward(request, response);
                log.log(Level.INFO, "errors");
                return;
            }

            // if we get here then all the required fields were found
            AddressBean address = new AddressBean();
            int userid = (Integer)(session.getAttribute("user_id"));
            AddressBeanDao addressdao = new AddressBeanDao(Conn);
             AddressBean findlast = addressdao.findLast();
            int addressId = 0;
            if (findlast != null){
            addressId = addressdao.findLast().getAddress_id() + 1; // this should make all entries have a unique incrementing id
            }
            address.setAddress_id(addressId);
            address.setAddress1(request.getParameter("address1"));
            address.setCity(request.getParameter("city"));
            address.setState(request.getParameter("state"));
            address.setZipcode(request.getParameter("zipcode"));
           
          

            int res = addressdao.store(address);
            if (res != 0) // check that the item was successfully added.
            {
                // add the successfully added item to the session so that the view item page will load it.
                session.setAttribute("address", address);
                //redirect the page to the next step in order to fil our all information 
                request.getRequestDispatcher("/EnterInfo.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "Data cannot be saved into the database.");
                request.getRequestDispatcher("/EnterInfo.jsp").forward(request, response);
                log.log(Level.INFO, "statement store data");
                return;

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateAccount.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CreateAccount.class.getName()).log(Level.SEVERE, null, ex);

        
        }

    }
}
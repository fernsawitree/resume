package edu.towson.controllers;

/*
 * Fern Sawitree Euamethiyangkool
 */
import edu.towson.beans.ExperienceBean;
import edu.towson.dao.ExperienceBeanDao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(urlPatterns = {"/ExperienceController"})
public class ExperienceController extends HttpServlet {
private static final Logger log = Logger.getLogger(ExperienceController.class.getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //if Add Experience button is clicked, pop-up window will appear
        String addEducation = request.getParameter("addexperience");
        response.sendRedirect("AddExperience.jsp");
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
          
            String[] requiredFormParams = {"companyname", "designation"};
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
            List<ExperienceBean> experiences = new ArrayList<ExperienceBean>();
       
             int userid = (Integer)(session.getAttribute("user_id"));
             String userquery = "SELECT * FROM Experience where id = '" + userid + "'";
            RS = S.executeQuery(                   
                    userquery);
            log.log(Level.INFO, userquery);
            while(RS.next()){
                //convert each row to experience bean object
                //add experience object to the list
                
            }
            // if we get here then all the required fields were found
            ExperienceBean experience = new ExperienceBean();
            ExperienceBeanDao experiencedao = new ExperienceBeanDao(Conn);
            ExperienceBean findlast = experiencedao.findLast();
            int experienceId = 0;
            if (findlast != null){
             experienceId = experiencedao.findLast().getExperience_Id() + 1; // this should make all entries have a unique incrementing id
            }
            experience.setUser_id(userid);
            experience.setExperience_Id(experienceId);
            experience.setCompanyName(request.getParameter("companyname"));
            experience.setDesignation(request.getParameter("designation"));
            experience.setStartDate(request.getParameter("startdate"));
            experience.setEndDate(request.getParameter("enddate"));
            experience.setDescription(request.getParameter("description"));
          

            int res = experiencedao.store(experience);
            if (res != 0) // check that the item was successfully added.
            {
                //add this experience object to list
                
                // add the successfully added item to the session so that the view item page will load it.
                session.setAttribute("experiences", experiences);
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